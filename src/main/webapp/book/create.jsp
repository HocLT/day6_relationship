<%@page import="vn.aptech.entity.Category"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create new Book</title>
</head>
<body>
	<h1>Create new Book</h1>
	<form action="${pageContext.request.contextPath}/Controller" method="post">
		<div>Title: <input name="title"/></div>
		<div>Price: <input name="price"/></div>
		<div>Category: <select name="category">
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
		</div>
		<div><input type="submit" name="a" value="Create"/></div>
	</form>
</body>
</html>