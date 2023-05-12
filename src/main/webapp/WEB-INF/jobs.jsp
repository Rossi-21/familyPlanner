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
			<h1 class="me-5">Jobs @the Family Planner</h1>
			<div class="dropdown">
				<button class="btn" type="button" data-bs-toggle="dropdown" aria-expanded="false">
					<div class="icon"></div>
					<div class="icon"></div>
					<div class="icon"></div>
				</button>
				<ul class="dropdown-menu dropdown-menu-dark">
					<li><a class="dropdown-item" href="/thefamilyplanner">Home</a></li>
					<li><a class="dropdown-item" href="/thefamilyplanner/events">Events</a></li>
					<li><a class="dropdown-item" href="/thefamilyplanner/jobs/new">Create a Job</a></li>
					<li><a class="dropdown-item" href="/thefamilyplanner/events/new">Create an Event</a></li>
					<li><hr class="dropdown-divider"></li>
					<li><a class="dropdown-item" href="/logout">Logout</a></li>
				</ul>
			</div>
		</div>
		<div class="mt-4 border-light rounded myshadow mx-auto border" style ="width:80%;">
			<div class="mt-4 mb-5" style="width:50%; margin-left:25%;">
				<h3 class="text-decoration-underline"><c:out value="${user.firstName}"/>'s Jobs:</h3>
				<Table class="table table-dark table-hover table-borderless mt-3">
					<thead>
						<tr class="text-light">				
							<th>Name</th>
							<th>Date</th>
							<th>Action</th>	
						</tr>
					</thead>
					<tbody>
						<c:forEach var="job" items="${myJobs}">
							<tr class="text-light">
								<td style="width: 50%;"><a href="/thefamilyplanner/jobs/${job.id}" class="text-light"><c:out value="${job.name}"/></a></td>
								<td style="width: 25%;"><fmt:formatDate value="${job.date}" pattern="MM/dd"/></td>
								<td style="width: 20%;"><a href="/thefamilyplanner/jobs/${job.id}/edit" class="text-light">edit</a></td> 
							</tr>
						</c:forEach>
					</tbody>
				</Table>
			</div>
			<div style="width:50%; margin-left:25%;">
				<h3 class="text-decoration-underline">All Jobs:</h3>
				<Table class="table table-dark table-borderless table-hover">
					<thead>
						<tr class="text-light">
							<th>Job</th>
							<th>Date</th>
							<th>Assigned to:</th>	
						</tr>
					</thead>
					<tbody>
						<c:forEach var="job" items="${avalibleJobs}">
							<tr class="text-light">
								<td style="width: 50%;"><a href="/thefamilyplanner/jobs/${job.id}" class="text-light"><c:out value="${job.name}"/></a></td>
								<td style="width: 25%;"><fmt:formatDate value="${job.date}" pattern="MM/dd"/></td>
								<td style="width: 20%;">
									<c:forEach var="users" items="${job.users}">
										<c:out value="${users.firstName}"/>
									</c:forEach>
								</td>
							</tr>
							
						</c:forEach>
					</tbody>
				</Table>
			</div>
		</div>
	</div>		
</body>
</html>