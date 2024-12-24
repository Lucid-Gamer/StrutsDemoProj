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
		var firstName = $('#firstName').val();
		var lastName = $("#lastName").val();
		var username = $('#username').val();
		var password = $('#password').val();
		var confirmPassword = $('#confirmPassword').val();
		var bldngId = $('#bldngSelect').val();
		var apartmentId = $('#aptmntSelect').val();
		var emailId = $('#emailId').val();
		var contactNum = $('#contactNum').val();
		var roleId = $('#roleSelect').val();
		
		var formData = {
			"registerModel.user.firstName": firstName,
			"registerModel.user.lastName": lastName,
			"registerModel.user.username": username,
			"registerModel.user.password": password,
			"registerModel.user.apartmentId": apartmentId,
			"registerModel.user.emailId": emailId,
			"registerModel.user.contactNum": contactNum,
			"registerModel.roleId": roleId
		};

		var errorMsgDiv = $('#errorMsgDiv');
		var errorMsg = $('#errorMsg');

		var flag = false;

		if (firstName === '') {
			errorMsgDiv.show();
			errorMsg.text('First Name is required').css('color','red');
		} else if (lastName === '') {
			errorMsgDiv.show();
			errorMsg.text('Last name is required').css('color','red');
		} else if (username === '') {
			errorMsgDiv.show();
			errorMsg.text('Username is required.').css('color','red');
		} else if (password === '') {
			errorMsgDiv.show();
			errorMsg.text('Password is required.').css('color','red');
		} else if (password !== confirmPassword) {
			errorMsgDiv.show();
			errorMsg.text('Password and confirm password should be the same.').css('color','red');
		} else if (bldngId === '') {
			errorMsgDiv.show();
			errorMsg.text('Please select a building.').css('color','red');
		} else if (apartmentId === '') {
			errorMsgDiv.show();
			errorMsg.text('Please select an apartment number.').css('color','red');
		} else if (emailId === '') {
			errorMsgDiv.show();
			errorMsg.text('Enter your Email Id.').css('color','red');		
		} else if (contactNum === '') {
			errorMsgDiv.show();
			errorMsg.text('Enter your Contact Number.').css('color','red');	
		} else if (roleId === '') {
			errorMsgDiv.show();
			errorMsg.text('Select a role id.').css('color','red');	
		} else {
			errorMsgDiv.hide();
			errorMsg.text('');	
			flag = true;
		} 


		if(!flag) {
			return false;
		}

		$.ajax({
			url: "/" + contextPath + "/user/makerAction",
			type: 'POST',
			data: formData,
			success: function(response) {
				if (response.status === "success") {
					alert("Registration successfull");
					window.location.href = "/" + contextPath + "/index.jsp";
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