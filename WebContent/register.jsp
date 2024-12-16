<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
<!-- <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script> -->
<link rel="stylesheet" type="text/css" href="./resources/bootstrap-4.5.3-dist/css/bootstrap.min.css">
<script type="text/javascript" src="./resources/bootstrap-4.5.3-dist/js/bootstrap.min.js"></script>
<script src="./resources/bootstrap-4.5.3-dist/js/bootstrap.bundle.min.js"></script>
<meta charset="UTF-8">
<title>Registration Page</title>
<style>
.custom-registration-container {
	padding-top: 5%;
	padding-left: 2%;
	padding-right: 2%;
	padding-bottom: 5%;
}

.custom-register-card-header {
	background-color: #6b5312;
	color: white;
	font-size: 1.5em;
	font-family: cursive;
}

.custom-register-card-body {
	padding-left: 10%;
	padding-right: 10%;
	padding-top: 5%;
	padding-bottom: 5%;
	font-family: cursive;
}

.custom-contact-register-row {
	margin-bottom: 3%;
}

.custom-register-nav-tabs .nav-item {
	background-color: #d4d2cd;
	color: #8a8988;
	border: white;
	border-top-left-radius: 5px;
	border-top-right-radius: 5px;
}

.custom-register-nav-tabs .nav-item .active {
	background-color: #134dba;
	color: white;
	font-weight: bolder;
	border-top-left-radius: 5px;
	border-top-right-radius: 5px;
}

.custom-personal-register-row {
	margin-bottom: 3%;
}

.custom-register-form-select {
	width: 100%;
	height: 50%;
	border: none;
}

.custom-register-username-check-button {
	margin-top: 17%;
}


.reg-user-cred-password-input.valid{
	border: 3px solid green;
}

.reg-user-cred-password-input.invalid{
	border: 3px solid red;
}

.reg-user-cred-confirm-password-input.valid{
	border: 3px solid green;
}

.reg-user-cred-confirm-password-input.invalid{
	border: 3px solid red;
}

.custom-register-nav-item {
	margin-left: 2px;
}
</style>
</head>
<body>
<script type="text/javascript">
	$(document).ready(function() {
		$.ajax({
			url: "register/getAllRoles",
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
				$.each(roleList,function(index,role) {
					if (role.roleName !== 'ROLE_ADMIN' && role.roleName !== 'ROLE_MAKER' && role.roleName !== 'ROLE_AUTHOR') {
						roleSelect.append($('<option>',{
						value: role.roleId,
						text: role.roleName.substring(5)
					}));
					}
				});
			},
			error: function(xhr, status, error) {
					console.error("Error fetching roles: "+error);
				}
		});
		
		$.ajax({
		    url: "register/getAllBuildings",
		    method: "GET",
		    success: function(response) {
		        let bldngList = response;
		        let bldngSelect = $('#bldngSelect');
		        bldngSelect.empty();
		        bldngSelect.append($('<option>', {
		        	value:'',
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
			if(selectedBldngId) {
				$.ajax({
					url : "register/getAptmntByBldng",
					method : "GET",
					data: { "selectedBldngId" : selectedBldngId },
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
						$.each(aptmntList,function(index,aptmnt) {
							aptmntSelect.append($('<option>',{
								value: aptmnt.aptmntId,
								text: aptmnt.aptmntNo
							}));
						});
					},
					error: function(xhr,status,error) {
						console.error("Error fetching apartments: "+error);
					}
				})
			}
		});
		
		$('#checkUsername').click(function() {
            var username = $('#username').val();
            if(username.length > 0) {
                $.ajax({
                    url: 'register/checkUsernameValidity',
                    method: 'GET',
                    data: {'username': username}, 
                    success: function(response) {
                        if (response.flag) {
                            $('#usernameStatus').text(response.message).css('color','green');
                        } else {
                            $('#usernameStatus').text(response.message).css('color','red');
                        }
                    },error: function(xhr, status, error) {
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
				"registerModel.roleId":$("#roleSelect").val()
			};

			$.ajax({
				url: "register/registerUserAlt",
				type: 'POST',
				data: formData,
				success: function(response) {
					if (response.status === "success") {
						alert("Registration successfull");
						window.location.href="welcome.jsp";
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
</script>

<script>
	document.addEventListener('DOMContentLoaded',function() {
		const passwordInput = document.getElementById('password');
		const confirmPasswordInput = document.getElementById('confirmPassword');
		
		confirmPasswordInput.addEventListener('input', function() {
			confirmPasswordInput.classList.remove('invalid','valid');
			passwordInput.classList.remove('invalid','valid');
			
			if(passwordInput.value === confirmPasswordInput.value) {
				confirmPasswordInput.classList.add('valid');
				passwordInput.classList.add('valid');
			} else {
				confirmPasswordInput.classList.add('invalid');
				passwordInput.classList.add('invalid');
			}
		});
		
		/*document.getElementById('registerForm').addEventListener('submit', function(event) {
			event.preventDefault();

			handleRegisterUser();
		})

		function handleRegisterUser() {
			const registerModel = {
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
					'Accept': 'application/json'
				},
				body: JSON.stringify(registerModel)
			})
			.then(response => response.json())
			.then(data => {
				alert("Registration Succesfull");
			})
			.catch(error => {
				alert("An error occurred: "+error.message);
			})
		}*/
	});
</script>
<script>
	document.addEventListener('DOMContentLoaded',function() {
		const passwordInput = document.getElementById('password');
		const confirmPasswordInput = document.getElementById('confirmPassword');
		
		confirmPasswordInput.addEventListener('input', function() {
			confirmPasswordInput.classList.remove('invalid','valid');
			passwordInput.classList.remove('invalid','valid');
			
			if(passwordInput.value === confirmPasswordInput.value) {
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
</script>
	<div class="container custom-registration-container">
		<div class="card custom-register-card">
			<div class="card-header custom-register-card-header text-center">
				<h1>Registration Form</h1>
			</div>
			<div class="card-body custom-register-card-body">
				<form id="registerForm" action="javascript:void(0);">
                    <!-- Tab navigation -->
                    <ul class="nav nav-tabs custom-register-nav-tabs" id="myTab" role="tablist">
                        <li class="nav-item custom-register-nav-item">
                            <a class="nav-link active" id="perInfoTag" data-toggle="tab" href="#per-info" role="tab" aria-controls="per-info" aria-selected="true">Personal Information</a>
                        </li>
                        <li class="nav-item custom-register-nav-item">
                            <a class="nav-link" id="contactTag" data-toggle="tab" href="#con-info" role="tab" aria-controls="con-info" aria-selected="false">Contact Information</a>
                        </li>
                        <li class="nav-item custom-register-nav-item">
                        	<a class="nav-link" id="userCredentialTab" data-toggle="tab" href="#user-info" role="tab" aria-controls="user-info" aria-selected="false">User Credentials</a>
                        </li>
                    </ul>

                    <!-- Tab content -->
                    <div class="tab-content mt-3">
      
                        <!-- Personal Information Tab -->
                        <div class="tab-pane fade show active" id="per-info" role="tabpanel" aria-labelledby="perInfoTag">
                            <div class="row custom-personal-register-row">
                            	<div class="col-6">
                                	<label class="form-label custom-register-form-label">First Name</label>
                                	<input class="form-control custom-register-form-control" type="text" autocomplete="off" name="firstName" id="firstName" placeholder="First Name">
                            	</div>
                            	<div class="col-6">
                            		<label class="form-label custom-register-form-label">Last Name</label>
                            		<input class="form-control custom-register-form-control" type="text" autocomplete="off" name="lastName" id="lastName" placeholder="Last Name">
                            	</div>
                            </div>
                            <div class="row custom-personal-register-row">
                            	<div class="col-6">
                            		<label class="form-label" >New Registration for </label>
                            		<select id="roleSelect" class="form-select custom-register-form-select" aria-label="Default select example"></select>
                            		<span id="roleSelectStatus"></span>
                            	</div>
                            </div>
                            <div class="row custom-personal-register-row">
                            	<div class="col-6">
                            		<label>Building</label>
                            		<select id="bldngSelect" class="form-select custom-register-form-select" aria-label="Default select example"></select>
                            		<span id="bldngSelectStatus"></span>
                            	</div>
                            	<div class="col-6">
                            		<label>Apartment</label>
                            		<select id="aptmntSelect" class="form-select custom-register-form-select" aria-label="Default select example"></select>
                            		<span id="aptmntSelectStatus"></span>
                            	</div>
                            </div>
                            <div class="row custom-personal-register-row">
                            	
                            </div>
                        </div>
                        <!-- End of Personal Information Tab -->

                        <!-- Contact Information Tab -->
                        <div class="tab-pane fade" id="con-info" role="tabpanel" aria-labelledby="contactTag">
                            <div class="row custom-contact-register-row">
                            	<div class="col-6">
                                	<label class="form-label custom-register-form-label" for="contactNum" id="contactLabel">Contact Number</label>
                                	<input class="form-control custom-register-form-control" type="text"  autocomplete="off" name="contactNum" id="contactNum" placeholder="Contact Number">
                            	</div>
                            	<div class="col-6">
                                	<label class="form-label custom-register-form-label" for="altContactNum" id="altContactLabel">Alternate Contact Number (Optional)</label>
                                	<input class="form-control custom-register-form-control" type="text" autocomplete="off" name="altContactNum" id="altContactNum" placeholder="Alternate Contact Number">
                            	</div>
                            </div>
                            <div class="row custom-contact-register-row">
                            	<div class="col-6">
                            		<label class="form-label custom-register-form-label" for="emailId" id="emailLabel">Email Id</label>
                            		<input class="form-control custom-register-form-control" type="email" autocomplete="off" name="emailId" id="emailId" placeholder="Email Id">
                            	</div>
                            	<div class="col-6">
                            		<label class="form-label custom-register-form-label" for="altEmailId" id="altEmailLabel">Alternate Email Id (Optional)</label>
                            		<input class="form-control custom-register-form-control" type="email" autocomplete="off" name="altEmailId" id="altEmailId" placeholder="Alternate Email Id">
                            	</div>
                            </div>
                        </div>
                        <!-- End of Contact Information Tab -->
                        
                        <!-- User Credential Information Tab -->
                        <div class="tab-pane fade register-user-credential-tab-form-div" id="user-info" role="tabpanel" aria-labelledby="userCredentialTab">
                        	<div class="row custom-personal-register-row">
                        		<div class="col-6">
                        			<label class="form-label custom-register-form-label" for="username" id="usernameLabel">Username</label>
                        			<input class="form-control custom-register-form-control" type="text" autocomplete="off" name="username" id="username" placeholder="Username">
                        			<span id="usernameStatus"></span> 
                        		</div>
                        		<div class="col-3 custom-register-username-check-button-div">
                        			<button class="btn btn-outline-primary custom-register-username-check-button" id="checkUsername" type="button">Check Username</button>
                        		</div>
                        	</div>
                        	<div class="row custom-personal-register-row register-user-cred-pass-div">
                        		<div class="col-6 reg-user-cred-password-div">
                        			<label class="form-label custom-register-form-label" for="username" id="usernameLabel">Password</label>
                        			<input type="password" name="password" id="password" autocomplete="off" class="form-control custom-register-form-control reg-user-cred-password-input" placeholder="Enter Password">
                        		</div>
                        		<div class="col-6 reg-user-cred-confirm-password-div">
                        			<label class="form-label custom-register-form-label" for="username" id="usernameLabel">Confirm Password</label>
                        			<input type="password" id="confirmPassword" autocomplete="off" class="form-control custom-register-form-control reg-user-cred-confirm-password-input" placeholder="Confirm Password">
                        		</div>
                        	</div>
                        	<div class="btn-grp text-center">
                        		<button class="btn btn-outline-success" id="registerFormSubmit" type="button" >Submit</button>
								<button class="btn btn-outline-danger" type="reset">Cancel</button>
                        			<input type="password" name="password" id="password" class="form-control custom-register-form-control custom-register-password" placeholder="Enter Password">
                        		</div>
                        		<div class="col-6">
                        			<label class="form-label custom-register-form-label" for="username" id="usernameLabel">Password</label>
                        			<input type="password" id="confirmPassword" class="form-control custom-register-form-control custom-register-confirm-password" placeholder="Confirm Password">
                        		</div>
                        	</div>
                        	<div class="btn-grp text-center">
                        		<button class="btn btn-outline-success" id="registerFormSubmit" type="submit" >Submit</button>
                        		<button class="btn btn-outline-danger" type="reset">Cancel</button>
                        	</div>
                        </div>
                        
                         <!-- End of Credential Information Tab -->
                        
                        
                        
                    </div>
                </form>
			</div>
		</div>
	</div>
</body>
</html>