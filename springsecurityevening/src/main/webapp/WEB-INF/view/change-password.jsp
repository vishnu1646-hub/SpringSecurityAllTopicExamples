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

   <div align="center">
      <h1>Reset Password</h1>
       <c:if test="${param.notMatched!=null}">
         <i style="color:red;">New Password and Confirm Password doesnot match</i>
      </c:if>
      <c:if test="${param.invalidPassword!=null}">
         <i style="color:red;">Invalid Old Password</i>
      </c:if>
      <form:form action="save-password" method="POST" modelAttribute="password-chng">
      <label>Old Password:</label><form:input path="oldPassword"/><br>
      <label>New Password:</label><form:input path="newPassword"/><br>
      <label>Confirm Password:</label><form:input path="confirmPassword"/>
      <br>
      <input type="submit" value="Change Password">
      </form:form>
   </div>

</body>
</html>