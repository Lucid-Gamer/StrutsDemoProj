<%@page import="com.apptrove.ledgerly.admin.models.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link href="../resources/bootstrap-4.5.3-dist/css/bootstrap.min.css"
	rel="stylesheet" type="text/css">

<meta charset="UTF-8">
<title>Dashboard Page</title>
</head>
<body>
	<%
	if (session.getAttribute("user") != null) {
		User user = (User) session.getAttribute("user");
		String username = user.getUsername();
	%>
	<div></div>
	<%
	} else {
	%>
	<a href="../index.jsp">Please login again</a>
	<%
	}
	%>
</body>
</html>