<%@page import="com.apptrove.ledgerly.admin.models.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	String username = "";
	if(session.getAttribute("user")!= null) {
		User user = (User)session.getAttribute("user");
		username = user.getUsername();
	}
%>
	<h1>Welcome <%= username!=""? username : null %> </h1>
	 <h2>Welcome to Struts 2 with Maven!</h2>
	 
</body>
</html>