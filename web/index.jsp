<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
    request.getRequestDispatcher("/WEB-INF/view/user/login_form.jsp").forward(request, response);
%>
	<%--<a href="listUser.do">--%>
		<%--<c:out value="List Users" ></c:out>--%>
	<%--</a>--%>

</body>
</html>