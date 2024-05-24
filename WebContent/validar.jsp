<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Farmacia</title>
</head>
<body>

<%
	if (session.getAttribute("datos") == null) {
		pageContext.forward("ServletPersonal?tipo=cerrarSesion");
	}
%>

</body>
</html>