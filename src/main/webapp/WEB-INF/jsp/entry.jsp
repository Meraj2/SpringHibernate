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
<h1>Add new employee</h1>
<form:form action="store" method="post" modelAttribute="bean">
Employee Name :<br />
<form:input path="empname"/><br />
Designation :<br />
<form:input path="designation"/><br />
E-mail :<br />
<form:input path="email"/><br />
<input type="submit" value="Entry">

</form:form>
</body>
</html>