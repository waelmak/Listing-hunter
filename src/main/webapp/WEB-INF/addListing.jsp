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
<title>Add Listing</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
</head>
<body>
	<div class="container">
		<br>
		<h1>New Listing</h1>

		<a href="/home"> Dashboard </a> <br> <br> <br>
		<div class="container">
			<form:form action="/listings" modelAttribute="listing" class="form"
				method="post">

				<div class="form row">
					<div class="col-2">
						<form:label for="address" path="address">Address:</form:label>
					</div>
					<div class="col-4">
						<form:input type="text" path="address" class="form-control" />
						<form:errors path="address" class="text-danger" />
					</div>
				</div>
				<br>
				<div class="form row">
					<div class="col-2">
						<form:label for="price" path="price">Price:</form:label>
					</div>
					<div class="col-4">
						<form:input type="number" path="price" class="form-control" />
						<form:errors path="price" class="text-danger" />
					</div>
				</div>

				<div class="form row">
					<form:input type="hidden" path="user" value="${user.id}"
						class="form-control" />
					<form:errors path="user" class="text-danger" />
				</div>
				<br>
				<div class="form row col-6">
					<input type="submit" value="Submit" class="btn btn-outline-primary" />
				</div>

			</form:form>
		</div>
	</div>
	<!-- change to match your file/naming structure -->
	<script src="/webjars/jquery/jquery.min.js"></script>
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>