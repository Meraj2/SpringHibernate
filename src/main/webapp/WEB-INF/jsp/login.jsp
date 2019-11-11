<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>User login</h1>
<form:form action="sign" method="post" modelAttribute="bean">
Employee code :<br />
<form:input path="empcode" placeholder="Enter employee code"/><br />
Employee name :<br />
<form:input path="empname"/><br />
<input type="submit" value="Login">
</form:form>
</body>
</html>