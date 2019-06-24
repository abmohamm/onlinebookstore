<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Delete Book Form</title>
</head>
<body>

	<form:form id="deletebookForm" modelAttribute="deletebook" action="deleteBookProcess" method="post">
		<table align="center">
		
 			<tr>
 				<td><form:label path="id">Id:</form:label></td>
 				<td><form:select path="id" items="${booksIdList}"/></td> 				
 			</tr>
			<tr>
		    	<td></td>
				<td align="left"><form:button id="deletebook" name="deletebook">Delete Book</form:button></td>
		    </tr>
			<tr></tr> 
		</table>
	</form:form>
</body>
</html>