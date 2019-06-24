<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Get Book Form</title>
</head>
<body>

	<form:form id="getbookForm" modelAttribute="getbook" action="searchBook" method="post">
		<table align="center">
		
 			<tr>
 				<td><form:label path="bookCategoryId">Book Category:</form:label></td>
 				<td><form:select path="bookCategoryId" items="${booksCategorySet}"/></td> 				
 			</tr>
 			
 			<tr>
 				<td><form:label path="isbn">ISBN : </form:label></td>
 				<td><form:select path="isbn" items="${booksListIsbnSet}"/></td> 				
 			</tr>
 			
 			<tr>
 				<td><form:label path="author">Author : </form:label></td>
 				<td><form:select path="author" items="${bookAuthorSet}"/></td> 				
 			</tr>
 			
 			<tr>
 				<td><form:label path="title">Title : </form:label></td>
 				<td><form:select path="title" items="${bookTitleSet}"/></td> 				
 			</tr>
 			
 			<tr>
 				<td><form:label path="publisher">Publisher : </form:label></td>
 				<td><form:select path="publisher" items="${bookPublisherSet}"/></td> 				
 			</tr>
			<tr>
		    	<td></td>
				<td align="left"><form:button id="getbook" name="getbook">Get Book</form:button></td>
		    </tr>
			<tr></tr> 
		</table>
	</form:form>
</body>
</html>