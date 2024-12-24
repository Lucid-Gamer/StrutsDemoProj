<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="../resources/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="../resources/bootstrap-4.5.3-dist/css/bootstrap.min.css">
<script type="text/javascript" src="../resources/bootstrap-4.5.3-dist/js/bootstrap.min.js"></script>
<script src="../resources/bootstrap-4.5.3-dist/js/bootstrap.bundle.min.js"></script>
<link href="./style/dashboard.css" type="text/css" rel="stylesheet">
<title>User Authorization Page</title>
</head>
<body>
	<div class="card">
		<div class="card-header">
			<h1>User Author</h1>
		</div>
		<div class="card-body">
			<s:if test="hasActionErrors()">
				<div class="alert alert-danger">
					<s:actionerror/>
				</div>
			</s:if>
			
			<table class="table table-bordered">
				<thead>
					<tr>
						<th>User ID</th>
						<th>Name</th>
						<th>Username</th>
					</tr>
				</thead>
			</table>
		</div>
	</div>
</body>
</html>