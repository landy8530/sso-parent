<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String username = (String) session.getAttribute("username");
%>
<html>
<body>
<h2>Hi <%=username%>,welcome to OA system!</h2>
</body>
</html>
