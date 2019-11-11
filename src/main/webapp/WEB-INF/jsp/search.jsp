<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Searching</h1>
<form action="find" >
Select for search :<br />
<select name="t1">
<option value="empcode">Employee code</option>
<option value="empname">Employee name</option>
<option value="designation">Designation</option>
<option value="email">E-mail</option>
</select><br />
Enter value :<br />
<input type="text" name="t2"><br />
<input type="submit" value="Search">
</form>
</body>
</html>