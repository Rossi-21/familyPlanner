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
<body class="myfont grade">
	<div class="pt-5 border container-fluid">
		<div class="d-flex align-items-center justify-content-between border-bottom border-dark">
			<h1 class="me-5 text-light">Check the Forecast, <c:out value="${user.firstName}"/></h1>
			<a class="ms-5" href="/logout">logout</a>
		</div>
		<table>
        <thead>
            <tr>
                <th>Date</th>
                <th>Max Temperature</th>
                <th>Min Temperature</th>
                <th>Condition</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${forecast}" var="item">
                <tr>
                    <td>${item.date}</td>
                    <td>${item.day.maxtemp_f}</td>
                    <td>${item.day.mintemp_f}</td>
                    <td>${item.day.condition.text}</td>
                    <td>
				        <img src="${item.day.condition.icon}" alt="Weather Icon">
				    </td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
	</div>
</body>
</html>