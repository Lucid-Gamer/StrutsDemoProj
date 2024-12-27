<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script> -->
<script src="${pageContext.request.contextPath}/resources/jquery.min.js" type="text/javascript"></script>
<!-- <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script> -->
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/bootstrap-4.5.3-dist/css/bootstrap.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap-4.5.3-dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/bootstrap-4.5.3-dist/js/bootstrap.bundle.min.js"></script>
<title>Index Page</title>
<style type="text/css">
.custom-login-card {
	height: 45vh;
}

.custom-login-card-header {
	background-color: #28a745;
	color: white;
	font-size: 1.5em;
}

.custom-login-card-header-text {
	font-weight: bolder;
	font-style: italic;
}

.custom-login-card-body {
	padding-bottom: 5%;
	padding-top: 10%;
	padding-left: 10%;
	padding-right: 10%;
}

.custom-login-container {
	padding: 10%;
}

.custom-login-btn {
	margin-top: 3%;
	width: 100%
}

.custom-login-form-label {
	margin-top: 1%;
}

.custom-login-card-footer {
	height: 5vh;
	max-height: 10%;
}

.custom-login-input-div {
	display: flex;
	justify-content: center;
}

.custom-login-input {
	width: 700px;
}

#customLoginButton.disabled {
	background-color: gray;
	border-color: black;
	color: white;
}

.login-form-label {
	margin-top: 2%;
}
</style>
</head>
<body>
<script>
		$(document).ready(function() {

			
	        $("#customLoginButton").click(function(event)  {
	        	event.preventDefault();
	        	var loginButton = $(this);
	        	loginButton.addClass('disabled');
	        	loginButton.prop('disabled',true);
	        	var formdata = {
	        		    "loginModel.username": $("#username").val(),
	        		    "loginModel.password": $("#password").val()
	        		};
	            $.ajax({
	                url: 'login/loginUser',
	                method: "POST",
	                data: formdata,
	                success: function(response) {
	                	console.log(response);
	                	if (response.status === "success") {
							alert("Login Successfull");
							
							window.location.href='dashboard';
						} else if(response.status === "errorLock"){
							alert(response.message);
							loginButton.prop('disabled',false);
							loginButton.removeClass('disabled');
						} else if(response.status === "errorPass") {
							alert(response.message);
							loginButton.prop('disabled',false);
							loginButton.removeClass('disabled');
						} else if(response.status === "errorUname") {
							alert(response.message);
							loginButton.prop('disabled',false);
							loginButton.removeClass('disabled');
						} else {
							alert(response.message);
							loginButton.prop('disabled',false);
							loginButton.removeClass('disabled');
						}
	                },
	                error : function(xhr,status,error) {
	                    console.log(error);
	                    loginButton.prop('disabled',false);
	                    loginButton.removeClass('disabled');
	                }
	            });
	        });
	    });
</script>
	<div class="container custom-login-container">
		<div class="card custom-login-card">
			<div class="card-header custom-login-card-header text-center">
				<h1 class="custom-login-card-header-text">Login</h1>
			</div>
			<div class="card-body custom-login-card-body">
				<form id="customLoginForm" autocomplete="off" class="custom-login-form">
					<div data-mdb-input-init class="row mb-4 custom-login-input-div">
						<label class="form-label text-center login-form-label col-3">Username</label>
						<input class="form-control custom-login-input col-8" 
							   type="text" 
							   id="username" 
							   placeholder="Enter username" 
							   name="username"
							   required
							   autocomplete="off"
							   >
					</div>
					<div>
						<span id="customLoginUsernameStatus"></span>
					</div>
					<div class="row mb-4 custom-login-input-div">
						<label class="form-label custom-login-form-label text-center col-3">Password</label>
						<input class="form-control custom-login-input col-8" 
							   type="password" 
							   id="password" 
							   placeholder="Enter Password" 
							   name="password"
							   required
							   autocomplete="off"
							   >
					</div>
					<div>
						<span id="customLoginPasswordStatus"></span>
					</div>
					<div class="text-center col-max">
						<button class="btn btn-outline-success custom-login-btn" id="customLoginButton">Submit</button>
					</div>
				</form>
			</div>
			<div class="card-footer text-center">
				<p>Don't have an account? <a href="register.jsp">Register Here</a></p>
			</div>
		</div>
	</div>
</body>
</html>