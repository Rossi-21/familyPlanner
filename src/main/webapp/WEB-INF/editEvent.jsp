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
	<div class="pt-5 container-fluid">
		<div class="d-flex align-items-center justify-content-between border-bottom border-light">
			<h1 class="me-5">Edit Event</h1>
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
		<div class="mt-4 p-4 border-light rounded myshadow mx-auto border" style ="width:80%;">
			<form:form action="/thefamilyplanner/events/${event.id}/edit" method="post" modelAttribute="event">
				<input type="hidden" name="_method" value="put">
				<form:errors path="user" class="error"/>
				<form:input type="hidden" path="user" value="${user.id}" class="form-control"/>
				<div>
					<form:label class="form-label fw-bold mt-2" path='name'>Name:</form:label>
					<form:errors class="text-danger" path="name"/>
		   			<form:input class="form-control" path='name'/>
				</div>
				<div>
	    			<form:label class="form-label fw-bold mt-2" path="location">Location:</form:label>
	    			<form:errors class="text-danger" path="location"/>
	    			<form:textarea class="form-control" path="location" cols="20" rows="3"></form:textarea>
	    		</div>
				<div>
	    			<form:label class="form-label fw-bold mt-2" path="description">Description:</form:label>
	    			<form:errors class="text-danger" path="description"/>
	    			<form:textarea class="form-control" path="description" cols="20" rows="3"></form:textarea>
	    		</div>
				<div>
					<form:label class="form-label fw-bold mt-2" path='date'>Date:</form:label>
					<form:errors class="text-danger" path="date"/>
		    		<form:input class="form-control" path='date' type='date'/>
				</div>
				<form:errors path="users" class="text-danger" />
				<div class="mt-3">
				    <form:label class="fw-bold mt-2 form-label" path='users'>Assigned to:</form:label>
				    <c:forEach var="user" items="${users}">
				        <c:set var="isChecked" value="false" />
				        <c:forEach var="myUser" items="${myUsers}">
				            <c:if test="${myUser.id == user.id}">
				                <c:set var="isChecked" value="true" />
				            </c:if>
				        </c:forEach>
				        <c:choose>
				            <c:when test="${isChecked}">
				                <form:checkbox path="users" value="${user.id}" checked="true" />
				            </c:when>
				            <c:otherwise>
				                <form:checkbox path="users" value="${user.id}" />
				            </c:otherwise>
				        </c:choose>
				        <form:label path="users"><c:out value="${user.firstName}"/></form:label>
				    </c:forEach>
				</div>
	    		<input class="btn btn-outline-light me-3 rounded-5 myshadow mt-3" type="submit" value="Submit"/>	
	 		</form:form>
		</div>
	</div>
</body>
</html>