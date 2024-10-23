const app = angular.module("shopping-cart-app", []);

app.controller("shopping-cart-ctrl", function($scope, $http) {
		// Shopping cart object
	$scope.cart = {
		items: [],

		add(id) {
		    console.log("Adding product with ID:", id); // Kiểm tra ID
		    alert(id);
		    var item = this.items.find(item => item.id == id);
		    if (item) {
		        item.qty++;
		        this.saveToLocalStorage();
		    } else {
		        $http.get(`/rest/products/${id}`).then(resp => {
		            resp.data.qty = 1;
		            this.items.push(resp.data);
		            this.saveToLocalStorage(); 
		        }).catch(error => {
		            console.error("Error fetching product:", error);
		        });
		    }
		},



        // Clear the cart
        clear() {
			this.items = [];
			localStorage.removeItem("cart"); // Remove cart from localStorage
			this.saveToLocalStorage(); // Ensure localStorage is updated after clearing
		},

		// Get total number of items in the cart
		get count() {
			return this.items
				.map(item => item.qty)
				.reduce((total, qty) => total += qty, 0);
		},

		// Save cart items to localStorage
		saveToLocalStorage() {
			var json = JSON.stringify(angular.copy(this.items));
			localStorage.setItem("cart", json);
		},

		// Load cart items from localStorage
		loadFromLocalStorage() {
			var json = localStorage.getItem("cart");
			this.items = json ? JSON.parse(json) : [];
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
