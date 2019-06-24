<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Items Added to the Cart</title>
</head>
<body>
	<table cellpadding="2" cellspacing="2" border="1">
		<tr>
			<th>ID</th>
			<th>Title</th>
			<th>Author</th>
			<th>Publisher</th>
			<th>Isbn</th>
		</tr>
		<c:forEach var="item" items="${sessionScope.cart}">
			<tr>
				<td>${item.b.id}</td>
				<td>${item.b.title}</td>
				<td>${item.b.author}</td>
				<td>${item.b.publisher}</td>
				<td>${item.b.isbn}</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>