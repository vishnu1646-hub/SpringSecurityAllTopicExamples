<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>login</title>
</head>
<body>
	<!-- to handle invalid -->
	<c:if test="${param.error!=null}">
		<i style="color: red;">Invalid login or password</i>
	</c:if>
	<c:if test="${param.logout!=null}">
		<i style="color: red;">you are successfully logged out</i>
	</c:if>
	<h1>My Custom Login Page</h1>
	<form:form action="process-login" method="POST">
      Username : <input type="text" name="username">
		<br>
      Password : <input type="text" name="password">
		<br>
		<input type="submit" value="Login">
	</form:form>

</body>
</html>