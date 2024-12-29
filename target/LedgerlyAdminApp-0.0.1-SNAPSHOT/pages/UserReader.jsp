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
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/UserReader.js"></script>
<title>Read User List Page</title>
</head>
<body>
	<div class="container custom-user-reader-container">
		<div class="card custom-user-reader-card">
			<div class="card-header">
				<h1>Active Users List</h1>
			</div>
			<div class="card-body custom-user-reader-card-body">
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
									<%-- <s:form cssClass="d-inline">
										<s:hidden name="userId" value="#user.userId" />
										<button type="button" class="btn btn-success btn-sm" onclick="authorizeUser(${user.userId})">Approve</button>
									</s:form> 
									<s:form cssClass="d-inline">
										<s:hidden name="userId" value="#user.userId" />
										<button type="button" class="btn btn-danger btn-sm">Reject</button>
									</s:form> --%>
									<button class="btn btn-outline-secondary btn-sm" type="button" onclick="showUserDetails('<s:property value="#user.toJSON()" />')">Show Details</button>
								</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<div class="modal fade" id="viewUserDetailStaticModal" data-backdrop="static" data-keyboard="false" tabindex="-1" aria-labelledby="viewUserDetailStaticModalLabel" aria-hidden="true">
  		<div class="modal-dialog modal-dialog-centered modal-dialog-scrollable">
    		<div class="modal-content">
      			<div class="modal-header">
        			<h5 class="modal-title" id="viewUserDetailStaticModalLabel"></h5>
        			<%-- <span id="viewUserDetailStaticModalLabelDate" class="ms-auto"></span> --%>
        			<button type="button" class="close" data-dismiss="modal" aria-label="Close">
          				<span aria-hidden="true">&times;</span>
        			</button>
      			</div>
      			<div class="modal-body">
        			<table class="table table-bordered text-center">
        				<tr>
        					<th>User Id</th>
        					<td class="custom-reader-user-modal-user-id"></td>
        				</tr>
        				<tr>
        					<th>Name</th>
        					<td class="custom-user-reader-modal-user-name"></td>
        				</tr>
        				<tr>
        					<th>Username</th>
        					<td class="custom-user-reader-modal-user-username"></td>
        				</tr>
        				<tr>
        					<th>Email Id</th>
        					<td class="custom-user-reader-modal-user-email"></td>
        				</tr>
        				<tr>
        					<th>Contact Number</th>
        					<td class="custom-user-reader-modal-user-contact"></td>
        				</tr>
        				<tr>
        					<th>Created On</th>
        					<td class="custom-user-reader-modal-user-creation"></td>
        				</tr>
        				<tr>
        					<th>Valid Till</th>
        					<td class="custom-user-reader-modal-user-validity"></td>
        				</tr>
        				<tr>
        					<th>Maker Id</th>
        					<td class="custom-user-reader-modal-user-makerid"></td>
        				</tr>
        				<tr>
        					<th>Maker Date</th>
        					<td class="custom-user-reader-modal-user-makerdt"></td>
        				</tr>
        			</table>
      			</div>
      			<div class="modal-footer btn-group">
      			</div>
    		</div>
  		</div>
	</div>
</body>
</html>