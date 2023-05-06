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
				<h1 class="me-5">Edit, <c:out value="${job.name}"/></h1>
				<a href="/thefamilyplanner">Home</a>
			</div>
			<div>
				<form:form class="width mt-3" action="/jobs/${job.id}" method="post" modelAttribute="job">
					<input type="hidden" name="_method" value="put">
					<div>
						<form:label class="form-label fw-bold mt-2" path='name'>Name:</form:label>
						<form:errors class="text-danger" path="name"/>
			   			<form:input class="form-control" path='name'/>
					</div>
					<div>
		    			<form:label class="form-label fw-bold mt-2" path="description">Description:</form:label>
		    			<form:errors class="text-danger" path="description"/>
		    			<form:textarea class="form-control" path="description" cols="20" rows="3"></form:textarea>
		    		</div>
					<div>
						<form:label class="form-label fw-bold mt-2" path='date'>Due Date:</form:label>
						<form:errors class="text-danger" path="date"/>
			    		<form:input class="form-control" path='date' type='date'/>
					</div>
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
					<a href="/thefamilyplanner/jobs"><button class="mt-3 btn btn-outline-dark me-3 rounded-0 myshadow">Cancel</button></a>
		    		<input class="btn btn-outline-dark me-3 rounded-0 myshadow mt-3" type="submit" value="Submit"/>	
		 		</form:form>
			</div>
		</div>
	</div>
</body>
</html>