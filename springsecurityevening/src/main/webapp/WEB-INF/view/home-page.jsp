<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h1>Home Page</h1>
	<h3>Hi ${username}</h3>
	<h4>Roles assigned : ${roles}</h4>
	<sec:authorize access='hasAuthority("Trainer")'>
		<a href="/springsecurityevening/trainer">Show Trainer's DashBoard</a>
	</sec:authorize>
	<br>
	<sec:authorize access='hasAuthority("Coder")'>
		<a href="/springsecurityevening/coder">Show Coder's DashBoard</a>
	</sec:authorize>
	<br>
	<a href="/springsecurityevening/deleteUser?username=${username}">Delete Account</a>
	&nbsp;
	<a href="/springsecurityevening/changePassword">Change Password</a>
	<br>
	<br>
	<form:form action="logout" method="POST">
		<input type="submit" value="logout">
	</form:form>
</body>
</html>