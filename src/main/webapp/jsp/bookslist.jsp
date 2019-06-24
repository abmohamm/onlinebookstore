<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Get Book Form</title>
</head>
<body>
	Available books for a given department :
	<table cellpadding="2" cellspacing="2" border="1">
		<tr>
			<th>Title</th>
		</tr>
		<c:forEach items="${booksList}" var="item">
			<tr>
				<td>${item}</td>
			</tr>
		</c:forEach>
	</table>
	<br/><br/>
	Books Available for a Given Isbn : ${isbn}
	<table cellpadding="2" cellspacing="2" border="1">
		<tr>
			<th>ID</th>
			<th>Title</th>
			<th>Author</th>
			<th>Publisher</th>
			<th>Isbn</th>	
			<th>Buy</th>	
		</tr>
		<c:forEach items="${booksListIsbn}" var="book">
			<tr>
				<td>${book.id}</td>
				<td>${book.title}</td>
				<td>${book.author}</td>
				<td>${book.publisher}</td>
				<td>${book.isbn}</td>
				<td align="center"><a href="ShoppingController?id=${book.id}&action=ordernow">Order Now</a></td>
			</tr>
		</c:forEach>
	</table>
	<br/><br/>
	Books Available for a Given Title : ${title}
	<table cellpadding="2" cellspacing="2" border="1">
		<tr>
			<th>ID</th>
			<th>Title</th>
			<th>Author</th>
			<th>Publisher</th>
			<th>Isbn</th>
			<th>Buy</th>
		</tr>
	<c:forEach items="${booksTitleList}" var="book">
			<tr>
				<td>${book.id}</td>
				<td>${book.title}</td>
				<td>${book.author}</td>
				<td>${book.publisher}</td>
				<td>${book.isbn}</td>
				<td align="center"><a href="">Order Now</a></td>
			</tr>
		</c:forEach>
	</table>
	<br/><br/> 
	Books Available for a Given Author : ${author}
	<table cellpadding="2" cellspacing="2" border="1">
		<tr>
			<th>ID</th>
			<th>Title</th>
			<th>Author</th>
			<th>Publisher</th>
			<th>Isbn</th>
			<th>Buy</th>
		</tr>
		<c:forEach items="${booksAuthorList}" var="book">
			<tr>
				<td>${book.id}</td>
				<td>${book.title}</td>
				<td>${book.author}</td>
				<td>${book.publisher}</td>
				<td>${book.isbn}</td>
				<td align="center"><a href="">Order Now</a></td>
			</tr>
		</c:forEach>
	</table>
	<br/><br/> 
	Books Available for a Given Publisher : ${publisher}
	<table cellpadding="2" border="1">
		<tr>
			<th>ID</th>
			<th>Title</th>
			<th>Author</th>
			<th>Publisher</th>
			<th>Isbn</th>
			<th>Buy</th>
		</tr>
		<c:forEach items="${bookPublishersList}" var="book">
			<tr>
				<td>${book.id}</td>
				<td>${book.title}</td>
				<td>${book.author}</td>
				<td>${book.publisher}</td>
				<td>${book.isbn}</td>
				<td align="center"><a href="">Order Now</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>