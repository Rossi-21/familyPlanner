<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!-- Formatting (dates) --> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
<head>
    <meta charset="UTF-8">
    <title>The Family Planner</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-KK94CHFLLe+nY2dmCWGMq91rCGa5gtU4mk92HdvYe+M/SXH301p5ILy+dN9+nJOZ" crossorigin="anonymous">
    <script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ENjdO4Dr2bkBIFxQpeoTz1HIcje39Wm4jDKdf19U8gI4ddQ3GYNS7NTKfAdVQSZe" crossorigin="anonymous"></script>
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link href="https://fonts.googleapis.com/css2?family=Acme&family=Roboto+Mono&display=swap" rel="stylesheet">
</head>
<body class="myfont bg-dark text-light">
	<div class="container-fluid pt-5">
		<div class="d-flex align-items-center justify-content-between border-bottom border-light">
			<h1 class="me-5">the Family Planner</h1>
			<div class="dropdown">
				<button class="btn" type="button" data-bs-toggle="dropdown" aria-expanded="false">
					<div class="icon"></div>
					<div class="icon"></div>
					<div class="icon"></div>
				</button>
				<ul class="dropdown-menu dropdown-menu-dark">
					<li><a class="dropdown-item" href="/thefamilyplanner">Home</a></li>
					<li><a class="dropdown-item" href="/thefamilyplanner/jobs">Jobs</a></li>
					<li><a class="dropdown-item" href="/thefamilyplanner/events">Events</a></li>
					<li><a class="dropdown-item" href="/thefamilyplanner/jobs/new">Create a Job</a></li>
					<li><a class="dropdown-item" href="/thefamilyplanner/events/new">Create an Event</a></li>
					<li><hr class="dropdown-divider"></li>
					<li><a class="dropdown-item" href="/logout">Logout</a></li>
				</ul>
			</div>
		</div>
		<div>
			<c:if test="${user.id == event.user.id}">
				<form style="margin-left:83%;" action="/events/${event.id}" method="post">
					<input type="hidden" name="_method" value="delete">
					<input class="mt-3 btn btn-danger btn-outline-dark me-3 rounded-5 myshadow" type="submit" value="delete">
				</form>
			</c:if>
		</div>
		<div class="mt-4 border-light rounded myshadow mx-auto border text-center" style ="width:80%;">
			<h4>Event:</h4><p><c:out value="${event.name}"/></p>
			<h4>Description:</h4><p><c:out value="${event.description}"/></p>
			<h4>Location:</h4><p><c:out value="${event.location}"/></p>
			<h4>Date:</h4><p><fmt:formatDate value="${event.date}" pattern="MM/dd"/></p>
		</div>
		
	</div>
</body>
</html>