<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update Book Form</title>
</head>
<body>

	<form:form id="updatebookForm" modelAttribute="updatebook" action="updateBookProcess" method="post">
		<table align="center">
		
 			<tr>
				<td><form:label path="id">Id:</form:label></td>
				<td><form:select path="id" items="${booksIdList}" /></td>
			</tr>
		    <tr>
				<td><form:label path="isbn">Isbn:</form:label></td>
				<td><form:input path="isbn" name="isbn" id="isbn" /></td>
			</tr> 
			<tr>
				<td><form:label path="title">Title:</form:label></td>
				<td><form:input path="title" name="title" id="title" /></td>
			</tr>
			<tr>
				<td><form:label path="author">Author:</form:label></td>
				<td><form:input path="author" name="author" id="author" /></td>
			</tr>
			<tr>
				<td><form:label path="description">Description:</form:label></td>
				<td><form:input path="description" name="description" id="description" /></td>
			</tr>
			<tr>
				<td><form:label path="publisher">Publisher:</form:label></td>
				<td><form:input path="publisher" name="publisher" id="publisher" /></td>
			</tr>
			<tr>
				<td><form:label path="bookCategory">Book Category:</form:label></td>
				<td><form:select path="bookCategory" items="${categoryList}" /></td>
			<tr>
				<td></td>
				<td align="left"><form:button id="updatebook" name="updatebook">Update Book</form:button></td>
			</tr>
			<tr></tr> 
		</table>
	</form:form>
	

</body>
</html>