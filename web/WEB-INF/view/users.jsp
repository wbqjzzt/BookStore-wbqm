<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	td {
		width: auto;
		background: #273142;
		text-align: center;
		padding: 8px 15px;
		color: #ffffff;
	}
</style>
</head>
<body>

	<table>
		<tr>
			<td>ID</td>
			<td>NickName</td>
			<td>Email</td>
			<td>Email Verified</td>
			<td>Last Login Ip</td>
		</tr>
		<c:forEach items="${ users }" var="user">
			<tr>
				<td><c:out value="${ user.id }" /></td>
				<td><c:out value="${ user.nickName }"></c:out></td>
				<td><c:out value="${ user.email }"></c:out></td>
				<td><c:out value="${ user.emailVerified }"></c:out></td>
				<td><c:out value="${ user.lastLoginIp }"></c:out></td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>