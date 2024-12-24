/**
 * 
 */

$(document).ready(function() {

	var contextPath = window.location.pathname.split('/')[1];
	var roleName;

	$.ajax({
		url: '/' + contextPath + '/getRole',
		method: 'GET',
		success: function(response) {
			if (response.status === "success") {
				roleName = response.roleName;
			} else {
				window.location.href = '/error/error.jsp';
				return;
			}
		}, error: function(xhr, status, error) {
			console.error("An error occurred: ", error);
		}
	});


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
		var firstName = $('#firstName');
		var lastName = $("#lastName");
		var username = $('#username');
		var password = $('#password');
		var confirmPassword = $('#confirmPassword');
		var bldngId = $('#bldngSelect');
		var apartmentId = $('#aptmntSelect');
		var emailId = $('#emailId');
		var contactNum = $('#contactNum');
		var roleId = $('#roleSelect');

		var formData = {
			"registerModel.user.firstName": firstName.val(),
			"registerModel.user.lastName": lastName.val(),
			"registerModel.user.username": username.val(),
			"registerModel.user.password": password.val(),
			"registerModel.user.apartmentId": apartmentId.val(),
			"registerModel.user.emailId": emailId.val(),
			"registerModel.user.contactNum": contactNum.val(),
			"registerModel.roleId": roleId.val()
		};

		var errorMsgDiv = $('#errorMsgDiv');
		var errorMsg = $('#errorMsg');

		/*var flag = false;*/

		// First Name Validation
		if (firstName.val().trim() === '') {
			$('#errorMsg').text('First Name is required.').css('color', 'red');
			$('#errorMsgDiv').show();
			$('#firstName').focus();
			return false;
		}

		// Last Name Validation
		if (lastName.val().trim() === '') {
			$('#errorMsg').text('Last Name is required.').css('color', 'red');
			$('#errorMsgDiv').show();
			$('#lastName').focus();
			return false;
		}

		// Password Validation
		if (password.val().trim() === '') {
			$('#errorMsg').text('Password is required.').css('color', 'red');
			$('#errorMsgDiv').show();
			$('#password').focus();
			return false;
		}

		if (confirmPassword.val().trim() === '') {
			$('#errorMsg').text('Confirm Password is required.').css('color', 'red');
			$('#errorMsgDiv').show();
			$('#confirmPassword').focus();
			return false;
		}

		if (password.val().trim() !== confirmPassword.val().trim()) {
			console.log("Password: ",password.val().trim());
			console.log("Confirm password: ",confirmPassword.val().trim());
			$('#errorMsg').text('Passwords do not match.').css('color', 'red');
			$('#errorMsgDiv').show();
			$('#confirmPassword').focus();
			return false;
		}

		// Building ID Validation
		if (bldngId.val() === null || bldngId.val() === '') {
			$('#errorMsg').text('Please select a building.').css('color', 'red');
			$('#errorMsgDiv').show();
			bldngId.focus();
			return false;
		}

		// Apartment ID Validation
		if (apartmentId.val() === null || apartmentId.val() === '') {
			$('#errorMsg').text('Please select an apartment number.').css('color', 'red');
			$('#errorMsgDiv').show();
			apartmentId.focus();
			return false;
		}

		// Email Validation
		if (emailId.val().trim() === '') {
			$('#errorMsg').text('Enter your Email Id.').css('color', 'red');
			$('#errorMsgDiv').show();
			emailId.focus();
			return false;
		}

		// Contact Number Validation
		if (contactNum.val().trim() === '') {
			$('#errorMsg').text('Enter your Contact Number.').css('color', 'red');
			$('#errorMsgDiv').show();
			contactNum.focus();
			return false;
		}

		// Role ID Validation
		if (roleId.val() === null || roleId.val() === '') {
			$('#errorMsg').text('Select a role id.').css('color', 'red');
			$('#errorMsgDiv').show();
			roleId.focus();
			return false;
		}



		errorMsgDiv.hide();
		errorMsg.text('');

		$.ajax({
			url: "/" + contextPath + "/user/makerAction",
			type: 'POST',
			data: formData,
			success: function(response) {
				if (response.status === "success") {
					alert("Registration successfull");
					window.location.href = "/" + contextPath + "/pages/dashboard.jsp";
				} else if (response.status === "error") {
					alert(response.message);
				}
			},
			error: function(xhr, status, error) {
				alert("An error occurred: " + error);
				console.error(error);
			}
		});
	});

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

/*document.addEventListener('DOMContentLoaded', function() {
	
})*/