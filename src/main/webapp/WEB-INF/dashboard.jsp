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
	<link href="https://fonts.googleapis.com/css2?family=Roboto+Mono&display=swap" rel="stylesheet">
</head>
<body class="myfont grade">
	<div class="pt-5 container-fluid">
		
			<div class="d-flex align-items-center justify-content-between border-bottom border-dark">
				<h1 class="me-5 text-light">Welcome to the Family Planner, <c:out value="${user.firstName}"/></h1>
				<div class="dropdown">
				  <button class="btn" type="button" data-bs-toggle="dropdown" aria-expanded="false">
				    <div class="icon"></div>
					<div class="icon"></div>
					<div class="icon"></div>
				  </button>
				  <ul class="dropdown-menu dropdown-menu-dark">
				    <li><a class="dropdown-item" href="/thefamilyplanner/jobs">Jobs</a></li>
				    <li><a class="dropdown-item" href="/thefamilyplanner/events">Events</a></li>
				    <li><hr class="dropdown-divider"></li>
				    <li><a class="dropdown-item" href="/logout">logout</a></li>
				  </ul>
				</div>
			</div>
			<div class="d-flex justify-content-evenly">
				<c:forEach items="${forecast}" var="item">
					<div class="align-items-center text-center mt-5">
						<h4>${item.day.condition.text}</h4>
						<h3>
			                <c:set var="dateFormat" value="MMM dd" />
			                <fmt:parseDate var="parsedDate" value="${item.date}" pattern="yyyy-MM-dd" />
			                <fmt:formatDate value="${parsedDate}" pattern="${dateFormat}" />
			            </h3>
						<img src="${item.day.condition.icon}" class="weather" alt="Weather Icon">
						<div class="d-flex justify-content-center">
							<p class="text-dark fw-bolder">High</p>
                    		<p class="text-dark fw-bold ms-4">Low</p>
						</div>	
						<div class="d-flex justify-content-center">
							<p class="text-dark fw-bolder">${item.day.maxtemp_f}</p>
                    		<p class="text-dark fw-bold ms-4">${item.day.mintemp_f}</p>
						</div>	
					</div>
			</c:forEach>
			</div>
		
	</div>			
</body>
</html>