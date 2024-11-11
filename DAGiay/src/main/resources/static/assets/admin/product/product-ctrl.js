app.controller("product-ctrl", function($scope, $http) {
    $scope.items = [];
    $scope.cates = [];
    $scope.sizes = [];
    $scope.sizeProducts = [];
    $scope.form = {};

    // Khởi tạo dữ liệu ban đầu
    $scope.initialize = function() {
        // Load danh sách sản phẩm
        $http.get("/rest/products").then(resp => {
            $scope.items = resp.data;
            $scope.items.forEach(item => {
                item.create_at = new Date(item.create_at);
            });
        });

        // Load danh mục
        $http.get("/rest/categories").then(resp => {
            $scope.cates = resp.data;
        });
        $http.get("/rest/sizes").then(resp => {
    		$scope.sizes = resp.data;
});
    }

    $scope.initialize();

    // Reset form
    $scope.reset = function() {
        $scope.form = {
            images: 'Black.jpg',
           sizeProducts: [],
        	category: {}
        };
    }

    // Chỉnh sửa sản phẩm
    $scope.edit = function(item) {
        $scope.form = angular.copy(item);
        $(".nav-tabs a:eq(0)").tab('show');
    }

    // Tạo sản phẩm mới
  $scope.create = function() {
    // Kiểm tra thông tin bắt buộc
    if (!$scope.form.name || !$scope.form.price || !$scope.form.category || !$scope.form.describe) {
        alert("Vui lòng nhập đầy đủ thông tin cần thiết.");
        return;
    }

    // Kiểm tra giá sản phẩm phải là số dương
    if (isNaN($scope.form.price) || $scope.form.price <= 0) {
        alert("Giá sản phẩm phải là số dương.");
        return;
    }

    // Tạo bản sao của form và thiết lập ngày tạo mới
    var item = angular.copy($scope.form);
    item.create_at = new Date(); // Cập nhật thời gian tạo

    // Gửi yêu cầu POST để thêm sản phẩm mới
    $http.post('/rest/products', item).then(resp => {
        resp.data.create_at = new Date(resp.data.create_at);
        $scope.items.push(resp.data);
        $scope.reset();
        alert("Thêm mới sản phẩm thành công");
    }).catch(error => {
        alert("Lỗi thêm mới sản phẩm: " + (error.data.message || "Lỗi không xác định"));
        console.log("Error", error);
    });
};


    // Cập nhật sản phẩm
   $scope.update = function() {
    var item = angular.copy($scope.form);
    $http.put(`/rest/products/${item.id}`, item).then(resp => {
        var index = $scope.items.findIndex(p => p.id === item.id);
        $scope.items[index] = angular.copy(resp.data); // Đảm bảo dữ liệu đồng bộ
        alert("Cập nhật sản phẩm thành công");
    }).catch(error => {
        alert("Lỗi cập nhật sản phẩm");
        console.log("Error", error);
    });
}

    // Xóa sản phẩm
    $scope.delete = function(item) {
        $http.delete(`/rest/products/${item.id}`).then(resp => {
            var index = $scope.items.findIndex(p => p.id === item.id);
            $scope.items.splice(index, 1);
            $scope.reset();
            alert("Xóa sản phẩm thành công");
        }).catch(error => {
            alert("Lỗi xóa sản phẩm");-
            console.log("Error", error);
        });
    }

    // Xử lý khi thay đổi ảnh
    $scope.imageChanged = function(files) {
        var data = new FormData();
        data.append('file', files[0]);
        $http.post('/rest/upload/images', data, {
            transformRequest: angular.identity,
            headers: { 'Content-Type': undefined }
        }).then(resp => {
            $scope.form.images = resp.data.name;  // Đảm bảo trường 'images' được cập nhật đúng
             console.log('Ảnh đã được cập nhật:', $scope.form.images);
        }).catch(error => {
            alert("Lỗi upload hình ảnh");
            console.log("Error", error);
        });
    }

    // Thêm size và số lượng
    $scope.addSizeProduct = function(size, count) {
		
		 const product = $scope.items.find(item => item.name === $scope.form.name);
    if (!product) {
        alert("Tên sản phẩm không tồn tại. Vui lòng chọn sản phẩm đúng.");
        return;
    }
		
        const existing = $scope.form.sizeProducts.find(sp => sp.size.id === size.id);
        if (existing) {
            existing.count = count; // Cập nhật nếu size đã tồn tại
        } else {
            $scope.form.sizeProducts.push({ size: size, count: count });
        }
    };

    // Xóa size sản phẩm
    $scope.removeSizeProduct = function(size) {
        const index = $scope.form.sizeProducts.findIndex(sp => sp.size.id === size.id);
        if (index >= 0) {
            $scope.form.sizeProducts.splice(index, 1);
        }
    };

    // Phân trang
    $scope.pager = {
        page: 0,
        size: 10,
        get items() {
            var start = this.page * this.size;
            return $scope.items.slice(start, start + this.size);
        },
        get count() {
            return Math.ceil(1.0 * $scope.items.length / this.size);
        },
        first() {
            this.page = 0;
        },
        prev() {
            this.page = Math.max(this.page - 1, 0);
        },
        next() {
            this.page = Math.min(this.page + 1, this.count - 1);
        },
        last() {
            this.page = this.count - 1;
        }
    }
});
