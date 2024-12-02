<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="./resources/bootstrap-4.5.3-dist/css/bootstrap.min.css">
<script type="text/javascript" src="./resources/bootstrap-4.5.3-dist/js/bootstrap.min.js"></script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/js/bootstrap.bundle.min.js"></script>
<meta charset="UTF-8">
<title>Registration Page</title>
<style>
.custom-registration-container {
	padding-top: 5%;
	padding-left: 2%;
	padding-right: 2%;
	padding-bottom: 5%;
}
</style>
</head>
<body>
	<div class="container custom-registration-container">
		<div class="card">
			<div>
				<form>
                    <!-- Tab navigation -->
                    <ul class="nav nav-tabs" id="myTab" role="tablist">
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
                            <div class="row">
                            	<div class="col-6">
                                	<label>First Name</label>
                                	<input class="form-control" type="text" name="firstName" id="firstName" placeholder="First Name">
                            	</div>
                            	<div class="col-6">
                            		<label>Last Name</label>
                            		<input class="form-control" type="text" name="lastName" id="lastName" placeholder="Last Name">
                            	</div>
                            </div>
                        </div>

                        <!-- Contact Information Tab -->
                        <div class="tab-pane fade" id="con-info" role="tabpanel" aria-labelledby="contactTag">
                            <div>
                                <label>Contact Number</label>
                                <input class="form-control" type="text" name="contactNum" id="contactNum" placeholder="Contact Number">
                            </div>
                        </div>
                    </div>
                </form>
			</div>
		</div>
	</div>
</body>
</html>