document.addEventListener("DOMContentLoaded", function () {
    // Fetch cities (tỉnh/thành phố)
    fetch('https://vapi.vnappmob.com/api/province/')
        .then(response => response.json())
        .then(data => {
            let citySelect = document.getElementById("city");
            citySelect.innerHTML = '<option value="0">Chọn Tỉnh/Thành phố</option>'; // Giá trị mặc định
            data.results.forEach(province => {
                let option = document.createElement("option");
                option.value = province.province_id;
                option.text = province.province_name;
                citySelect.add(option);
            });
        });

    document.getElementById("city").addEventListener("change", function () {
        let cityId = this.value;
        fetch(`https://vapi.vnappmob.com/api/province/district/${cityId}`)
            .then(response => response.json())
            .then(data => {
                let districtSelect = document.getElementById("district").querySelector("select");
                districtSelect.innerHTML = '<option value="0">Chọn Quận/Huyện</option>'; // Giá trị mặc định
                data.results.forEach(district => {
                    let option = document.createElement("option");
                    option.value = district.district_id;
                    option.text = district.district_name;
                    districtSelect.add(option);
                });
            })
            .catch(error => console.error('Error fetching districts:', error));
    });

    document.getElementById("district").querySelector("select").addEventListener("change", function () {
        let districtId = this.value;
        fetch(`https://vapi.vnappmob.com/api/province/ward/${districtId}`)
            .then(response => response.json())
            .then(data => {
                let wardSelect = document.getElementById("ward");
                wardSelect.innerHTML = '<option value="0">Chọn Phường/Xã</option>'; // Giá trị mặc định
                data.results.forEach(ward => {
                    let option = document.createElement("option");
                    option.value = ward.ward_id;
                    option.text = ward.ward_name;
                    wardSelect.add(option);
                });
            })
            .catch(error => console.error('Error fetching wards:', error));
    });
});


		
		function validateForm() {
		            let name = document.getElementById("name").value.trim();
		            let phone = document.getElementById("phone").value.trim();
		            let email = document.getElementById("email").value.trim();
		            let address = document.getElementById("address").value.trim();
					let city = document.getElementById("city").value;
					    let district = document.getElementById("district").querySelector("select").value;
					    let ward = document.getElementById("ward").value;
		            
		            if (name === "" || phone === "" || email === "" || address === "" || city === "" || city === "0" || district === "" ||  district === "0" || ward === "" || ward === "0") {
		                alert("Vui lòng điền đầy đủ thông tin.");
		                return false;
		            }
		            
		            let phoneRegex = /^[0-9]{10}$/;
		            if (!phoneRegex.test(phone)) {
		                alert("Số điện thoại không hợp lệ. Vui lòng nhập đúng định dạng.");
		                return false;
		            }
		            
		            let emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
		            if (!emailRegex.test(email)) {
		                alert("Email không hợp lệ. Vui lòng nhập đúng định dạng.");
		                return false;
		            }
		            
					
		            return true;
		        }

		        document.querySelector("button[type='submit']").addEventListener("click", function (event) {
		            if (!validateForm()) {
		                event.preventDefault();
		            }
		        });