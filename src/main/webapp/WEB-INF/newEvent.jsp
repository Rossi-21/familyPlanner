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
	<div class="container-fluid">
	<div class="p-5">
		<div class="d-flex align-items-center justify-content-between border-bottom border-dark">
			<h1>Create an Event!</h1>
		</div>
		<div class="mt-3 width">	
			<form:form action="/events/create" method="post" modelAttribute="event">
					<form:errors path="user" class="error"/>
					<form:input type="hidden" path="user" value="${user.id}" class="form-control"/>
				<div>
					<form:label class="fw-bold mt-2 form-label" path='name'>Name:</form:label>
					<form:errors class="text-danger" path="name"/>
		   			<form:input class="form-control" path='name'/>
				</div>
				<div>
					<form:label class="fw-bold mt-2 form-label" path='location'>Location:</form:label>
					<form:errors class="text-danger" path="location"/>
		   			<form:input class="form-control" path='location'/>
				</div>
				<div>
					<form:label class="fw-bold mt-2 form-label" path='description'>Description:</form:label>
					<form:errors class="text-danger" path="description"/>
					<form:textarea class="form-control" path="description" cols="20" rows="3"></form:textarea>    		
				</div>	
				<div>
					<form:label class="fw-bold mt-2 form-label" path='date'>Date:</form:label>
					<form:errors class="text-danger" path="date"/>
					<form:input class="form-control" path='date' type='date'/>
				</div>	
				<div class="mt-3">
					<form:label class="fw-bold mt-2 form-label" path='users'>Assigned to:</form:label>
					<c:forEach var="user" items="${users}">
						<form:checkbox path="users" value="${user.id}"/>
  						<form:label path="users"><c:out value="${user.firstName}"/></form:label>
					</c:forEach>
				</div>
				<a href="/thefamilyplanner/events"><button class="mt-3 btn btn-outline-dark me-3 rounded-0 myshadow">Cancel</button></a>
		   		<input class="mt-3 btn btn-outline-dark me-3 rounded-0 myshadow" type="submit" value="Create"/>	
   			</form:form>
		</div>	
	</div>	
</div>
</body>
</html>