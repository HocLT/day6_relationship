<%@page import="vn.aptech.entity.Category"%>
<%@page import="vn.aptech.entity.Book"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Book List</title>
</head>
<body>
	<h3><a href="Controller?a=DisplayCreate">Create New</a></h3>
	<form action="Controller">
		<select name="category">
			<option value="0">Choose</option>
			<%
			if (request.getAttribute("cates") != null) {
				List<Category> cates = (List<Category>)request.getAttribute("cates");
				for (Category c: cates) {
			%>
				<option value="<%= c.getId() %>"><%= c.getName() %></option>
			<%
				}
			}
			%>
		</select>
		<input type="submit" name="a" value="Filter"/>
	</form>
	<h1>Book List</h1>
	<table>
		<thead>
			<tr>
				<th>Id</th>
				<th>Title</th>
				<th>Price</th>
				<th>Category</th>
			</tr>
		</thead>
		<tbody>
		<%
		if (request.getAttribute("books") != null) {
			List<Book> books = (List<Book>)request.getAttribute("books");
			for (Book b: books) {
		%>
		<tr>
			<td><%= b.getId() %></td>
			<td><%= b.getTitle() %></td>
			<td><%= b.getPrice() %></td>
			<td><%= b.getCategory().getName() %></td>
		</tr>
		<%
			}
		}
		%>
		</tbody>
	</table>
</body>
</html>