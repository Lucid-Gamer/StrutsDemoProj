<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="../resources/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="../resources/bootstrap-4.5.3-dist/css/bootstrap.min.css">
<script type="text/javascript" src="../resources/bootstrap-4.5.3-dist/js/bootstrap.min.js"></script>
<script src="../resources/bootstrap-4.5.3-dist/js/bootstrap.bundle.min.js"></script>
<link href="./style/usermaker.css" type="text/css" rel="stylesheet">
<link href="./style/dashboard.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="./scripts/UserMaker.js"></script>
<title>User Maker</title>
</head>
<body>
	<div class="container custom-registration-container">
		<div class="card custom-register-card">
			<div class="card-header custom-register-card-header text-center">
				<h1>Registration Form</h1>
			</div>
			<div class="card-body custom-register-card-body">
			<div id="errorMsgDiv" style="display: none;">
    			<span id="errorMsg"></span>
			</div>
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
                        		<div class="col-6 maker-user-cred-password-div">
                        			<label class="form-label custom-register-form-label" for="username" id="usernameLabel">Password</label>
                        			<input type="password" name="password" id="password" autocomplete="off" class="form-control custom-register-form-control maker-user-cred-password-input" placeholder="Enter Password">
                        		</div>
                        		<div class="col-6 maker-user-cred-confirm-password-div">
                        			<label class="form-label custom-register-form-label" for="username" id="usernameLabel">Confirm Password</label>
                        			<input type="password" id="confirmPassword" autocomplete="off" class="form-control custom-register-form-control maker-user-cred-confirm-password-input" placeholder="Confirm Password">
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