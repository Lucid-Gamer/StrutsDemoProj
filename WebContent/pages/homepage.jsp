<%@page import="com.apptrove.ledgerly.admin.models.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard Page</title>
</head>
<body>
<% 
	User user = (User) session.getAttribute("user");
	String username = user.getUsername();
	if(user!=null && username!=null)
	{
%>
	<div>Welcome to Dashboard <%= username %></div>
<%
	} else {
%>
	<a href="index.jsp">Please login again</a>
<%
	} 
%>
</body>
</html>