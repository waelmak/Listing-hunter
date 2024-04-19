<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isErrorPage="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>House Hunter Dashboard</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
</head>
<body>

	<div class="container">
		<br>
		<h1>
			Welcome,
			<c:out value="${user.userName}" />
		</h1>
		<h5>
			<a href="/logout">log out</a>
		</h5>
		<br>
		<table class="table table-striped">
			<tr class="thead-dark">
				<th>Address</th>
				<th>Listed On</th>
				<th>Added By</th>
				<th>Price</th>
			</tr>
			<c:forEach var="listing" items="${listings}">
				<tr>
					<td><a href="listings/${listing.id}"><c:out
								value="${listing.address}" /></a></td>
					<td><c:out value="${listing.listingDate}" /></td>
					<td><c:out value="${listing.user.userName}" /></td>
					<td>$<c:out value="${listing.price}" /></td>
				</tr>
			</c:forEach>
		</table>

		<br> <br>
		<div class="row mx-1">
			<a href="/listings/new" class="btn btn-outline-primary">Add
				Listing</a>
		</div>
	</div>
	<!-- change to match your file/naming structure -->
	<script src="/webjars/jquery/jquery.min.js"></script>
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>