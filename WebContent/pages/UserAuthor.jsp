<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="${pageContext.request.contextPath}/resources/jquery.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/bootstrap-4.5.3-dist/css/bootstrap.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap-4.5.3-dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/bootstrap-4.5.3-dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/popper.min.js"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/dashboard.css">
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/userauthor.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/UserAuthor.js"></script>
<title>User Authorization Page</title>
</head>
<body>
	<div class="container custom-user-author-container">
		<div class="card custom-user-author-card">
			<div class="card-header">
				<h1>User Author</h1>
			</div>
			<div class="card-body custom-user-author-card-body">
				<s:if test="hasActionErrors()">
					<div class="alert alert-danger">
						<s:actionerror />
					</div>
				</s:if>

				<table class="table table-bordered text-center">
					<thead>
						<tr>
							<th>User ID</th>
							<th>Name</th>
							<th>Username</th>
							<th>Actions</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="userList" var="user">
							<tr>
								<td><s:property value="#user.userId" /></td>
								<td><s:property value="#user.firstName" /> <s:property value="#user.lastName" /></td>
								<td><s:property value="#user.username" /></td>
								<td>
									<s:form cssClass="d-inline">
										<s:hidden name="userId" value="#user.userId" />
										<button type="button" class="btn btn-success btn-sm" onclick="authorizeUser(${user.userId})">Approve</button>
									</s:form> 
									<s:form cssClass="d-inline">
										<s:hidden name="userId" value="#user.userId" />
										<button type="button" class="btn btn-danger btn-sm">Reject</button>
									</s:form>
								</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</body>
</html>