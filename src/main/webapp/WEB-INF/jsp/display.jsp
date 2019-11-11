<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    <%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style type="text/css">
h4
{
	color:green:
}
</style>
</head>
<body>
<h4>${msg }</h4>
<h1>List of Employees</h1>
<table width="100%" border="3">
<tr><th>Employee ID</th><th>Employee name</th><th>Designation</th><th>E-mail</th><th>Update</th><th>Delete</th></tr>
<c:forEach var="tab" items="${list }">
<tr><td><form action="update" method="post" modelAttribute="bean">
<input type="text" name="empcode" value=${tab.empcode } ></td>
<td><input type="text" name="empname" value=${tab.empname } ></td>
<td><input type="text" name="designation" value=${tab.designation } ></td>
<td><input type="text" name="email" value=${tab.email } ></td>
<td><input type="submit" value="Update"></form></td>
<td><form action="delete" method="post" modelAttribute="bean">
<input type="hidden" name="empcode" value=${tab.empcode } >
<input type="submit" value="Delete"></form></td></tr>
</c:forEach>
</table>

</body>
</html>