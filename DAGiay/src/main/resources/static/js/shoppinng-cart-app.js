const app = angular.module("shopping-cart-app", []);

app.controller("shopping-cart-ctrl", function($scope, $http) {
	// Shopping cart object
	$scope.cart = {
		items: [],

		favorites: [],

		add(id) {
			let selectedSize = document.getElementById("size").value;
			let selectedQuantity = parseInt(document.getElementById("quantity").value);

			if (isNaN(selectedQuantity) || selectedQuantity < 1) {
				alert("Vui lòng chọn số lượng hợp lệ");
				document.getElementById("quantity").value = "";
				return;
			}

			var item = this.items.find(item => item.id === id && item.size === selectedSize); // Tìm item theo id và size
			if (item) {
				item.qty += selectedQuantity;
				this.saveToLocalStorage();
			} else {
				var cart = this;
				$http.get(`/rest/products/${id}`).then(resp => {
					resp.data.qty = selectedQuantity;
					//resp.data.qty = 1;
					resp.data.size = selectedSize;
					cart.items.push(resp.data);
					cart.saveToLocalStorage();
				});
			}
		},

		// Save cart to local storage
		saveToLocalStorage() {
			var json = JSON.stringify(angular.copy(this.items));
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
		},

		addToFavorites(id) {
			let selectedSize = document.getElementById("size").value;
			let selectedQuantity = parseInt(document.getElementById("quantity").value);

			// Kiểm tra xem số lượng có hợp lệ không
			if (isNaN(selectedQuantity) || selectedQuantity < 1) {
				alert("Vui lòng chọn số lượng hợp lệ");
				document.getElementById("quantity").value = "";
				return; // Dừng quá trình thêm vào yêu thích
			}

			// Kiểm tra xem sản phẩm đã có trong danh sách yêu thích chưa
			var favorite = this.favorites.find(item => item.id === id && item.size === selectedSize);

			if (favorite) {
				// Nếu sản phẩm đã có trong yêu thích, hiển thị thông báo
				alert("Sản phẩm này đã có trong danh sách yêu thích.");
			} else {
				// Nếu sản phẩm chưa có trong danh sách yêu thích, tải thông tin sản phẩm từ API
				var cart = this;
				$http.get(`/rest/products/${id}`).then(resp => {
					resp.data.qty = selectedQuantity; // Gán số lượng vào sản phẩm
					resp.data.size = selectedSize; // Gán size vào sản phẩm
					cart.favorites.push(resp.data);
					cart.saveFavoritesToLocalStorage();
				});
			}
		},

		removeFavorite(id) {
			let index = this.favorites.findIndex(item => item.id === id);
			if (index > -1) {
				this.favorites.splice(index, 1);
				this.saveFavoritesToLocalStorage();
			}
		},

		saveFavoritesToLocalStorage() {
			var json = JSON.stringify(angular.copy(this.favorites));
			localStorage.setItem("favorites", json);
		},

		loadFavoritesFromLocalStorage() {
			let json = localStorage.getItem("favorites");
			this.favorites = json ? JSON.parse(json) : [];
		},

		saveToLocalStorage() {
			var json = JSON.stringify(angular.copy(this.items));
			localStorage.setItem("cart", json);
		},

		get totalFavoritesQty() {
			return this.favorites
				.map(favorite => favorite.qty)
				.reduce((total, qty) => total + qty, 0);
		},

		addToCartFromFavorites(item) {
			// Kiểm tra xem sản phẩm đã có trong giỏ hàng chưa
			let existingItem = this.items.find(cartItem => cartItem.id === item.id && cartItem.size === item.size);

			if (existingItem) {
				// Nếu sản phẩm đã có trong giỏ hàng, thông báo
				alert("Sản phẩm này đã có trong giỏ hàng.");
			} else {
				// Nếu chưa có, thêm sản phẩm vào giỏ hàng
				let cartItem = angular.copy(item);
				this.items.push(cartItem);
				this.saveToLocalStorage();
			}
		},
		addToFavoritesFromCart(item) {
		        // Kiểm tra xem sản phẩm đã có trong yêu thích chưa
		        let existingFavorite = this.favorites.find(favoriteItem => favoriteItem.id === item.id && favoriteItem.size === item.size);

		        if (existingFavorite) {
		            // Nếu sản phẩm đã có trong yêu thích, thông báo
		            alert("Sản phẩm này đã có trong yêu thích.");
		        } else {
		            // Nếu chưa có, thêm sản phẩm vào yêu thích
		            let favoriteItem = angular.copy(item);
		            this.favorites.push(favoriteItem);
		            this.saveFavoritesToLocalStorage();
		        }
		    },
	};

	// Load cart when controller is initialized
	$scope.cart.loadFromLocalStorage();
	$scope.cart.loadFavoritesFromLocalStorage();

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


