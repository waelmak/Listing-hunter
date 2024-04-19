<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- Formatting (dates) -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>House Hunter</title>
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
<script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
</head>
<body>
	<div class="container m-3">
		<h1 class="text-center">Welcome, House Hunter!</h1>
		<br>
		<div>
			<h2 class="text-center">Register</h2>
			<br>
			<form:form method="POST" action="/register" modelAttribute="newUser">

				<div class="mb-3 row">
					<div class="col-3"></div>
					<div class="col-2">
						<form:label path="userName" class="form-label">User Name: </form:label>
					</div>
					<div class="col-4">
						<form:input class="form-control" path="userName" />
						<form:errors path="userName" class="text-danger" />
					</div>
					<div class="col-3"></div>
				</div>
				<div class="mb-3 row">
					<div class="col-3"></div>
					<div class="col-2">
						<form:label class="form-label" path="email">Email: </form:label>
					</div>
					<div class="col-4">
						<form:input class="form-control" path="email" />
						<form:errors path="email" class="text-danger" />
					</div>
					<div class="col-3"></div>
				</div>
				<div class="mb-3 row">
					<div class="col-3"></div>
					<div class="col-2">
						<form:label path="password" class="form-label">Password: </form:label>
					</div>
					<div class="col-4">
						<form:password class="form-control" path="password" />
						<form:errors path="password" class="text-danger" />
					</div>
					<div class="col-3"></div>
				</div>
				<div class="mb-3 row">
					<div class="col-3"></div>
					<div class="col-2">
						<form:label path="confirm" class="form-label">Confirm PW: </form:label>
					</div>
					<div class="col-4">
						<form:password class="form-control" path="confirm" />
						<form:errors path="confirm" class="text-danger" />
					</div>
					<div class="col-3"></div>
				</div>
				<br>
				<div class="mb-3 row mx-auto col-6">
					<input type="submit" value="Register"
						class="btn btn-outline-primary text-center" />
				</div>

			</form:form>
		</div>
		<br>
		<div class="">
			<h2 class="text-center">Login In</h2>
			<br>
			<form:form method="POST" action="/login" modelAttribute="newLogin">
				<div class="mb-3 row">
					<div class="col-3"></div>
					<div class="col-2">
						<form:label class="form-label" path="email">Email: </form:label>
					</div>
					<div class="col-4">
						<form:input class="form-control" path="email" />
						<form:errors path="email" class="text-danger" />
					</div>
					<div class="col-3"></div>
				</div>
				<div class="mb-3 row">
					<div class="col-3"></div>
					<div class="col-2">
						<form:label class="form-label" path="password">Password: </form:label>
					</div>
					<div class="col-4">
						<form:password class="form-control" path="password" />
						<form:errors path="password" class="text-danger" />
					</div>
					<div class="col-3"></div>
				</div>
				<br>
				<div class="row mx-auto col-6">
					<input type="submit" value="Login In"
						class="btn btn-outline-primary" />
				</div>
			</form:form>
		</div>


	</div>
</body>
</html>