const app = angular.module("shopping-cart-app", []);

app.controller("shopping-cart-ctrl", function($scope, $http) {

    // Shopping cart object
    $scope.cart = {
        items: [],

        // Add item to cart
        add(id) {
            let item = this.items.find(item => item.id === id);
            if (item) {
                item.qty++;
                this.saveToLocalStorage();
            } else {
                $http.get(`/rest/products/${id}`).then(resp => {
                    resp.data.qty = 1;
                    this.items.push(resp.data);
                    this.saveToLocalStorage();
                }).catch(error => {
                    console.error("Failed to add item:", error);
                });
            }
        },

        // Save cart to local storage
        saveToLocalStorage() {
            let json = JSON.stringify(angular.copy(this.items));
            localStorage.setItem("cart", json);
        },

        // Get total item count
        get count() {
            return this.items
                .map(item => item.qty)
                .reduce((total, qty) => total + qty, 0);
        },

        // Get total amount
        get amount() {
            return this.items
                .map(item => item.qty * item.price)
                .reduce((total, amount) => total + amount, 0);
        },

        // Load cart from local storage
        loadFromLocalStorage() { // Corrected method name
            let json = localStorage.getItem("cart");
            this.items = json ? JSON.parse(json) : [];
        },

        // Remove item from cart
        remove(id) {
            let index = this.items.findIndex(item => item.id === id);
            if (index > -1) {
                this.items.splice(index, 1);
                this.saveToLocalStorage();
            }
        },

        // Clear all items from cart
        clear() {
            this.items = [];
            this.saveToLocalStorage();
        }
    };

    // Load cart when controller is initialized
    $scope.cart.loadFromLocalStorage();

    // Order object
    $scope.order = {
        createDate: new Date(),
        address: "",
        account: { username: $("#username").text() }, // Assumes username is available in the DOM

        // Generate order details from cart items
        get orderDetails() {
            return $scope.cart.items.map(item => {
                return {
                    product: { id: item.id },
                    price: item.price,
                    quantity: item.qty
                };
            });
        },

        // Handle purchase action
        purchase() {
            let order = angular.copy(this);
            $http.post("/rest/orders", order).then(resp => {
                alert("Đặt hàng thành công");
                $scope.cart.clear();
                location.href = "/order/detail/" + resp.data.id;
            }).catch(error => {
                alert("Đặt hàng lỗi!");
                console.error("Order failed:", error);
            });
        }
    };
});
