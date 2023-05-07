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
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/app.js"></script><!-- change to match your file/naming structure -->
	<link rel="preconnect" href="https://fonts.googleapis.com">
	<link rel="preconnect" href="https://fonts.gstatic.com">
	<link href="https://fonts.googleapis.com/css2?family=Roboto+Mono&display=swap" rel="stylesheet">
</head>
<body class="myfont">
	<div class="container-fluid d-flex mx-auto reg">
		<div class="p-5">
			<div class="d-flex align-items-center border-bottom border-dark">
				<h1 class="me-5"><c:out value="${user.firstName}"/>'s Events</h1>
				<a href="/thefamilyplanner">Home</a>
			</div>
			<div>
				<Table class="table table-bordered table-hover mt-3">
					<thead>
						<tr>
							<th>Event</th>
							<th>Date</th>
							<th>Action</th>	
						</tr>
					</thead>
					<tbody>
						<c:forEach var="event" items="${myEvents}">
									<tr>
										<td><a href="/thefamilyplanner/events/${event.id}"><c:out value="${event.name}"/></a></td>
										<td><c:out value="${event.date}"/></td>
										<td><a href="/thefamilyplanner/events/${event.id}/edit">edit</a></td> 
									</tr>
						</c:forEach>
					</tbody>
				</Table>
			</div>
			<div>
				<h3>All Event:</h3>
				<Table class="table table-bordered table-hover">
					<thead>
						<tr>
							<th>Event</th>
							<th>Date</th>
							<th>Assigned to:</th>	
						</tr>
					</thead>
					<tbody>
						<c:forEach var="event" items="${avalibleEvents}">
							<tr>
								<td><a href="/thefamilyplanner/events/${event.id}"><c:out value="${event.name}"/></a></td>
								<td><c:out value="${event.date}"/></td>
								<td>
									<c:forEach var="users" items="${event.users}">
										<c:out value="${users.firstName}"/>
									</c:forEach>
								</td>
							</tr>
							
						</c:forEach>
					</tbody>
				</Table>
			</div>
			<div>
			<a href="/thefamilyplanner/events/new"><button class="btn btn-outline-dark btn-lg me-3 mt-3 rounded-0 myshadow">Create an Event</button></a>
			</div>
		</div>
	</div>		
</body>
</html>