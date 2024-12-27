<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="${pageContext.request.contextPath}/resources/jquery.min.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/bootstrap-4.5.3-dist/css/bootstrap.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap-4.5.3-dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/bootstrap-4.5.3-dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/index.js"></script>
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
	<div class="container custom-login-container">
		<div class="card custom-login-card">
			<div class="card-header custom-login-card-header text-center">
				<h1 class="custom-login-card-header-text">Login</h1>
			</div>
			<div class="card-body custom-login-card-body">
				<form id="customLoginForm" autocomplete="off" class="custom-login-form">
					<div data-mdb-input-init class="row mb-4 custom-login-input-div">
						<label class="form-label text-center login-form-label col-3">Username</label>
						<input class="form-control custom-login-input col-8" type="text" id="username" placeholder="Enter username" name="username" required autocomplete="off">
					</div>
					<div>
						<span id="customLoginUsernameStatus"></span>
					</div>
					<div class="row mb-4 custom-login-input-div">
						<label class="form-label custom-login-form-label text-center col-3">Password</label>
						<input class="form-control custom-login-input col-8" type="password" id="password" placeholder="Enter Password" name="password" required autocomplete="off">
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
	<!-- Button trigger modal -->
	<!-- <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#modelId">
	  Launch
	</button> -->
	
	<!-- Modal -->
	<div class="modal fade" id="loginPageModal" tabindex="-1" role="dialog" aria-labelledby="loginPageModalTitle" aria-hidden="true">
		<div class="modal-dialog modal-dialog-centered" role="document">
			<div class="modal-content">
					<div class="modal-header">
							<h5 class="modal-title" id="loginPageModalTitle"></h5>
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">&times;</span>
								</button>
						</div>
				<div class="modal-body">
					<div class="container-fluid">
						
					</div>
				</div>
				<div class="modal-footer">
					<!-- <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
					<button type="button" class="btn btn-primary">Save</button> -->
				</div>
			</div>
		</div>
	</div>
	
	<!-- <script>
		$('#exampleModal').on('show.bs.modal', event => {
			var button = $(event.relatedTarget);
			var modal = $(this);
			// Use above variables to manipulate the DOM
			
		});
	</script> -->
</body>
</html>