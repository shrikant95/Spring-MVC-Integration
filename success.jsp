<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Confirmation Page</title>
</head>
<body>
	Message : ${success}
	<br/>
	<br/>
    <a href="<c:url value='/login' />">Employee can Login Now</a>
	
</body>

</html>