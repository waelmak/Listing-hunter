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
<title>View Listing</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/css/main.css">
</head>
<body>

	<div class="container">
		<br>
		<h1>
			<c:out value="${listing.address}" />
		</h1>
		<br> <b><a href="/home">Dashboard</a></b> <br> <br>
		<div class="container px-0">
			<p>
				<b>Address:</b>
				<c:out value="${listing.address}"></c:out>
			</p>
			<p>
				<b>Listing Date:</b>
				<c:out value="${listing.listingDate}"></c:out>
			</p>
			<p>
				<b>Price:</b> $
				<c:out value="${listing.price}"></c:out>
			</p>
		</div>
	</div>

	<div class="container">
		<h4>
			<b>Notes:</b>
		</h4>
		<div class="border my-3 border-4 border-secondary col-6 overflow-auto"
			style="height: 130px;">
			<c:forEach var="noteList" items="${notes}">
				<div class="mx-3 my-auto">
					Added by<b> <c:out value="${noteList.user.userName}" />
					</b>
					<p class="mx-3">
						-
						<c:out value="${noteList.noteUser}" />
						<c:if test="${noteList.user.id != user.id}">
							<a href="javascript:void(0)" class="text-danger ms-3 fw-semibold"
								style="text-decoration: none">Delete</a>
						</c:if>
						<c:if test="${noteList.user.id == user.id}">
							<a href="/delete?id=${noteList.id}"
								class="text-danger fw-semibold ms-3"
								style="text-decoration: none">Delete</a>
						</c:if>
					</p>
				</div>
			</c:forEach>
		</div>
	</div>

	<div class="container">
		<h4>
			<b>Add Note:</b>
		</h4>
		<div class="border my-2 border-4 border-secondary col-6">
			<form:form action="/listings/${listing.id}/addNote"
				modelAttribute="note" class="form my-2 p-2" method="post">
				<div class="form row px-1">
					<div class="col-5">
						<form:label for="noteUser" path="noteUser">
							<b>Note:</b>
						</form:label>
					</div>
					<div class="col-7">
						<form:input type="text" path="noteUser"
							class="form-control border border-4 border-secondary" />
						<form:errors path="noteUser" class="text-danger" />
					</div>
				</div>

				<div class="form row my-1">
					<form:input type="hidden" path="user" value="${user.id}"
						class="form-control" />
					<form:errors path="user" class="text-danger" />
				</div>
				<div class="form row my-1 border-top border-4 border-secondary">
					<form:input type="hidden" path="listing" value="${listing.id}"
						class="form-control" />
					<form:errors path="listing" class="text-danger" />
				</div>

				<div class="form row mx-1 mt-3">
					<input type="submit" value="Add Note"
						class="btn btn-outline-secondary" />
				</div>
			</form:form>
		</div>
	</div>
	<br>
	<div class="container mb-4">
		<c:if test="${listing.user.id != user.id}">
			<a href="javascript:void(0)" class="btn btn-danger">Delete</a>
			<a href="javascript:void(0)" class="btn btn-primary">Edit</a>
		</c:if>
		<c:if test="${listing.user.id == user.id}">
			<a href="/listings/${listing.id}/delete" class="btn btn-danger">Delete</a>
			<a href="/listings/${listing.id}/edit" class="btn btn-primary">Edit</a>
		</c:if>
	</div>
	<script src="/webjars/jquery/jquery.min.js"></script>
	<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</body>
</html>