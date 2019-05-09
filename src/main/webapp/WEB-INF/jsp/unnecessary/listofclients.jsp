<%@ page import="com.eshopadd.domain.Product" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: evgenijhajmovskij
  Date: 2019-03-03
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Admin page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/static/css/app.css"></head>
<body>

<div class="container">

    <div>
        <h3 style="color: aliceblue"> Clients </h3>
    </div>

    <div class="row">
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"> <a href="/"> Home </a> </li>
                <li class="breadcrumb-item"> <a href="/admin"> Admin </a> </li>
                <li class="breadcrumb-item active" aria-current="page"> Clients </li>
            </ol>
        </nav>
    </div>

    <div class="row">
        <h3>List of clients</h3>
        <table class="table table-borderless table-hover" style="width: 65%; margin: 0 auto; color: aliceblue">
            <tr>
                <th>ID</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Date of birth</th>
                <th>Email</th>
                <th>Password</th>
                <th>Country</th>
                <th>City</th>
                <th>Post code</th>
                <th>Street</th>
                <th>Hose number</th>
                <th>Flat number</th>
            </tr>
            <c:forEach items="${clients}" var="client">
                <tr>
                    <td>${client.id}</td>
                    <td>${client.firstName}</td>
                    <td>${client.lastName}</td>
                    <td>${client.birthDate}</td>
                    <td>${client.email}</td>
                    <td>${client.password}</td>
                    <c:set var = "address" scope = "session" value = "${client.address}"/>
                    <td>${address.country}</td>
                    <td>${address.city}</td>
                    <td>${address.postCode}</td>
                    <td>${address.street}</td>
                    <td>${address.houseNumber}</td>
                    <td>${address.flatNumber}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
