/**
 * 
 */

$(document).ready(function() {

	var contextPath = window.location.pathname.split('/')[1];
	var roleName = sessionStorage.getItem("roleName");
	$.ajax({
		url: "/" + contextPath + "/register/getAllRoles",
		method: "GET",
		success: function(response) {
			let roleList = response;
			let roleSelect = $('#roleSelect');
			roleSelect.empty();
			roleSelect.append($('<option>', {
				value: '',
				text: "Select Role",
				disabled: true,
				selected: true
			}))
			
			debugger;
			
			$.each(roleList, function(index, role) {

				
				
				if (roleName === "ROLE_ADMIN") {

					roleSelect.append($('<option>', {
						value: role.roleId,
						text: role.roleName.substring(5)
					}));

				} else {

					if (role.roleName !== 'ROLE_ADMIN' && role.roleName !== 'ROLE_MAKER' && role.roleName !== 'ROLE_AUTHOR') {
						roleSelect.append($('<option>', {
							value: role.roleId,
							text: role.roleName.substring(5)
						}));
					}

				}
			});
		},
		error: function(xhr, status, error) {
			console.error("Error fetching roles: " + error);
		}
	});

	$.ajax({
		url: "/" + contextPath + "/register/getAllBuildings",
		method: "GET",
		success: function(response) {
			let bldngList = response;
			let bldngSelect = $('#bldngSelect');
			bldngSelect.empty();
			bldngSelect.append($('<option>', {
				value: '',
				text: 'Select Building',
				disabled: true,
				selected: true
			}))
			$.each(bldngList, function(index, bldng) {
				bldngSelect.append($('<option>', {
					value: bldng.bldngId,
					text: bldng.bldngName
				}));
			});
		},
		error: function(xhr, status, error) {
			console.error("Error fetching buildings: " + error);
		}
	});

	$('#bldngSelect').change(function() {
		var selectedBldngId = $(this).val();
		if (selectedBldngId) {
			$.ajax({
				url: "/" + contextPath + "/register/getAptmntByBldng",
				method: "GET",
				data: { "selectedBldngId": selectedBldngId },
				success: function(response) {
					console.log(response);
					let aptmntList = response;
					let aptmntSelect = $('#aptmntSelect');
					aptmntSelect.empty();
					aptmntSelect.append($('<option>', {
						value: '',
						text: "Select Apartment",
						disabled: true,
						selected: true
					}));
					$.each(aptmntList, function(index, aptmnt) {
						aptmntSelect.append($('<option>', {
							value: aptmnt.aptmntId,
							text: aptmnt.aptmntNo
						}));
					});
				},
				error: function(xhr, status, error) {
					console.error("Error fetching apartments: " + error);
				}
			})
		}
	});

	$('#checkUsername').click(function() {
		var username = $('#username').val();
		if (username.length > 0) {
			$.ajax({
				url: "/" + contextPath + "/register/checkUsernameValidity",
				method: 'GET',
				data: { 'username': username },
				success: function(response) {
					if (response.flag) {
						$('#usernameStatus').text(response.message).css('color', 'green');
					} else {
						$('#usernameStatus').text(response.message).css('color', 'red');
					}
				}, error: function(xhr, status, error) {
					console.error("Error checking username: ", error);
				}
			});
		} else {
			$('#usernameStatus').text('');
		}
	});

	$('#registerFormSubmit').click(function() {
		var formData = {
			"registerModel.user.firstName": $("#firstName").val(),
			"registerModel.user.lastName": $("#lastName").val(),
			"registerModel.user.username": $("#username").val(),
			"registerModel.user.password": $("#password").val(),
			"registerModel.user.apartmentId": $("#aptmntSelect").val(),
			"registerModel.user.emailId": $("#emailId").val(),
			"registerModel.user.contactNum": $("#contactNum").val(),
			"registerModel.roleId": $("#roleSelect").val()
		};

		$.ajax({
			url: "/" + contextPath + "/register/registerUserAlt",
			type: 'POST',
			data: formData,
			success: function(response) {
				if (response.status === "success") {
					alert("Registration successfull");
					window.location.href = "welcome.jsp";
				} else if (response.status === "error") {
					alert("Registration failed!!");
				}
			},
			error: function(xhr, status, error) {
				alert("An error occurred: " + error);
			}
		});
	});
});


document.addEventListener('DOMContentLoaded', function() {
	const passwordInput = document.getElementById('password');
	const confirmPasswordInput = document.getElementById('confirmPassword');

	confirmPasswordInput.addEventListener('input', function() {
		confirmPasswordInput.classList.remove('invalid', 'valid');
		passwordInput.classList.remove('invalid', 'valid');

		if (passwordInput.value === confirmPasswordInput.value) {
			confirmPasswordInput.classList.add('valid');
			passwordInput.classList.add('valid');
		} else {
			confirmPasswordInput.classList.add('invalid');
			passwordInput.classList.add('invalid');
		}
	});
});

document.addEventListener('DOMContentLoaded', function() {
	const passwordInput = document.getElementById('password');
	const confirmPasswordInput = document.getElementById('confirmPassword');

	confirmPasswordInput.addEventListener('input', function() {
		confirmPasswordInput.classList.remove('invalid', 'valid');
		passwordInput.classList.remove('invalid', 'valid');

		if (passwordInput.value === confirmPasswordInput.value) {
			confirmPasswordInput.classList.add('valid');
			passwordInput.classList.add('valid');
		} else {
			confirmPasswordInput.classList.add('invalid');
			passwordInput.classList.add('invalid');
		}
	});

	/*document.getElementById('registerForm').onsubmit = function(event) {
		event.preventDefault();
		handleRegisterUser();
	};

	function handleRegisterUser() {
		const formData = {
			"registerModel.user.firstName": document.getElementById("firstName").value,
			"registerModel.user.lastName": document.getElementById("lastName").value,
			"registerModel.user.username": document.getElementById("username").value,
			"registerModel.user.password": document.getElementById("password").value,
			"registerModel.user.apartmentId": document.getElementById("aptmntSelect").value,
			"registerModel.user.emailId": document.getElementById("emailId").value,
			"registerModel.user.contactNum": document.getElementById("contactNum").value,
			"registerModel.roleId":document.getElementById("roleSelect").value
		};

		fetch('register/registerUserAlt', {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json',
			},
			body: JSON.stringify(formData)
		})
		.then(response => response.json())
		.then(data => {
			if (data.success === true) {
				alert("Registration Succesfull");
				window.location.href="welcome.jsp";	
			} else {
				alert("Something went wrong. Please try again later!");
				console.log("An error occurred: "+data.errorMsg);
			}
			
		})
		.catch(error => {
			alert("An error occurred: "+error.message);
	});
  } */
})