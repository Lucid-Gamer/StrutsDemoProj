<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<script src="${pageContext.request.contextPath}/resources/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/jquery-ui.min.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/bootstrap-4.5.3-dist/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/jquery-ui.min.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/bootstrap-4.5.3-dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/resources/bootstrap-4.5.3-dist/js/bootstrap.bundle.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/popper.min.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/resources/scripts/UserEditor.js"></script>
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/style/dashboard.css">
<title>User Editor Page</title>
</head>
<body>
	<div class="container custom-user-reader-container">
		<div class="card custom-user-reader-card">
			<div class="card-header text-center">
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
        			<h5 class="modal-title custom-user-reader-modal-title" id="viewUserDetailStaticModalLabel"></h5>
        			<%-- <span id="viewUserDetailStaticModalLabelDate" class="ms-auto"></span> --%>
        			<button type="button" class="close" onclick="closeModal()" aria-label="Close">
          				<span aria-hidden="true">&times;</span>
        			</button>
      			</div>
      			<div class="modal-body">
      				<form>
        			<table class="table table-bordered text-center">
                    	<tr>
                        	<td>User Id</td>
                        	<td><input type="text" name="updateModel.userId" id="userId" readonly="true" class="form-control" /></td>
                    	</tr>
                    	<tr>
                        	<td>First Name</td>
                        	<td><input type="text" name="updateModel.firstName" id="firstName" readonly="true" class="form-control" /></td>
                        </tr>
                    	<tr>
                        	<td>Last Name</td>
                        	<td><input type="text" name="updateModel.lastName" id="lastName" readonly="true" class="form-control" /></td>
                    	</tr>
                    	<tr>
                        	<td>Username</td>
                        	<td><input  type="text" name="updateModel.username" id="username" readonly="true" class="form-control" /></td>
                    	</tr>
                    	<tr>
                        	<td>Email Id</td>
                        	<td><input  type="text" name="updateModel.emailId" id="emailId" readonly="true" class="form-control" /></td>
                    	</tr>
                    	<tr>
                        	<td>Contact Number</td>
                        	<td><input  type="text" name="updateModel.contactNum" id="contactNum" readonly="true" class="form-control" /></td>
                    	</tr>
                    	<tr>
                    		<td>Created On</td>
                    		<td><input type="datetime" name="updateModel.makerDt" id="makerDt" readonly="true" class="form-control" /></td>
                    	</tr>
                    	<tr>
                    		<td>Created By</td>
                    		<td><input type="text"  name="updateModel.makerCd" id="makerCd" readonly="true" class="form-control" /></td>
                    	</tr>
                    	<tr>
                        	<td>Valid Till</td>
                        	<td><input type="datetime" name="updateModel.validTill" id="validTill" readonly="true" class="form-control" /></td>
                    	</tr>
                	</table>
                	</form>
      			</div>
      			<div class="modal-footer btn-group">
      				<button id="editUserDetailsBtn" class="btn btn-primary" onclick="editfunction()">Edit</button>
                	<button id="saveUserDetailsBtn" class="btn btn-success" style="display:none;" onclick="saveFunction()">Save</button>
                	<button type="button" id="deleteUserBtn" class="btn btn-danger" onclick="deactivateUser()">Delete</button>
                	<button class="btn btn-secondary" data-dismiss="modal" onclick="closeModal()">Close</button>
      			</div>
    		</div>
  		</div>
	</div>
</body>
</html>