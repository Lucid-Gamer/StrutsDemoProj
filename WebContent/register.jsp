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
</style>
</head>
<body>
<script type="text/javascript">
	$(document).ready(function() {
		$.ajax({
			url: "getAllRoles",
			method: "GET",
			success: function(response) {
				let roleList = response;
				let roleSelect = $('#roleSelect');
				roleSelect.empty();
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
		    url: "getAllBuildings",
		    method: "GET",
		    success: function(response) {
		        let bldngList = response;
		        let bldngSelect = $('#bldngSelect');
		        bldngSelect.empty();
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
	});
</script>
	<div class="container custom-registration-container">
		<div class="card custom-register-card">
			<div class="card-header custom-register-card-header text-center">
				<h1>Registration Form</h1>
			</div>
			<div class="card-body custom-register-card-body">
				<form>
                    <!-- Tab navigation -->
                    <ul class="nav nav-tabs custom-register-nav-tabs" id="myTab" role="tablist">
                        <li class="nav-item">
                            <a class="nav-link active" id="perInfoTag" data-toggle="tab" href="#per-info" role="tab" aria-controls="per-info" aria-selected="true">Personal Information</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" id="contactTag" data-toggle="tab" href="#con-info" role="tab" aria-controls="con-info" aria-selected="false">Contact Information</a>
                        </li>
                    </ul>

                    <!-- Tab content -->
                    <div class="tab-content mt-3">
      
                        <!-- Personal Information Tab -->
                        <div class="tab-pane fade show active" id="per-info" role="tabpanel" aria-labelledby="perInfoTag">
                            <div class="row custom-personal-register-row">
                            	<div class="col-6">
                                	<label class="form-label custom-register-form-label">First Name</label>
                                	<input class="form-control custom-register-form-control" type="text" name="firstName" id="firstName" placeholder="First Name">
                            	</div>
                            	<div class="col-6">
                            		<label class="form-label custom-register-form-label">Last Name</label>
                            		<input class="form-control custom-register-form-control" type="text" name="lastName" id="lastName" placeholder="Last Name">
                            	</div>
                            </div>
                            <div class="row custom-personal-register-row">
                            	<div class="col-6">
                            		<label class="form-label" >New Registration for </label>
                            		<select id="roleSelect" class="form-select custom-register-form-select" aria-label="Default select example"></select>
                            	</div>
                            </div>
                            <div class="row custom-personal-register-row">
                            	<div class="col-6">
                            		<label>Building</label>
                            		<select id="bldngSelect" class="form-select custom-register-form-select" aria-label="Default select example"></select>
                            	</div>
                            </div>
                        </div>
                        <!-- End of Personal Information Tab -->

                        <!-- Contact Information Tab -->
                        <div class="tab-pane fade" id="con-info" role="tabpanel" aria-labelledby="contactTag">
                            <div class="row custom-contact-register-row">
                            	<div class="col-6">
                                	<label class="form-label custom-register-form-label" for="contactNum" id="contactLabel">Contact Number</label>
                                	<input class="form-control custom-register-form-control" type="text" name="contactNum" id="contactNum" placeholder="Contact Number">
                            	</div>
                            	<div class="col-6">
                                	<label class="form-label custom-register-form-label" for="altContactNum" id="altContactLabel">Alternate Contact Number (Optional)</label>
                                	<input class="form-control custom-register-form-control" type="text" name="altContactNum" id="altContactNum" placeholder="Alternate Contact Number">
                            	</div>
                            </div>
                            <div class="row custom-contact-register-row">
                            	<div class="col-6">
                            		<label class="form-label custom-register-form-label" for="emailId" id="emailLabel">Email Id</label>
                            		<input class="form-control custom-register-form-control" type="email" name="emailId" id="emailId" placeholder="Email Id">
                            	</div>
                            	<div class="col-6">
                            		<label class="form-label custom-register-form-label" for="altEmailId" id="altEmailLabel">Alternate Email Id (Optional)</label>
                            		<input class="form-control custom-register-form-control" type="email" name="altEmailId" id="altEmailId" placeholder="Alternate Email Id">
                            	</div>
                            </div>
                        </div>
                        <!-- End of Contact Information Tab -->
                    </div>
                </form>
			</div>
		</div>
	</div>
</body>
</html>