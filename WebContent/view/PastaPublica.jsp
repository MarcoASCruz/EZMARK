<%@page import="modelos.Pasta"%>
<%@page import="java.util.List"%>
<% 
	Pasta pasta = (Pasta) request.getAttribute("pasta"); 
%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Pasta Compartilhada</title>
</head>
<body>
	<% 
		for(Pasta p : pasta.getPastas()){
			%>
			Nome: <%=p.getNome() %> <br />
			<%
		}
	%>
</body>
</html>