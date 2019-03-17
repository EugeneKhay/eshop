<%--
  Created by IntelliJ IDEA.
  User: evgenijhajmovskij
  Date: 2019-03-03
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>New order</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
</head>
<body>

<div>
    <h3>List of orders:</h3>
    <table class="table table-borderless table-hover" style="width: 65%">
        <tr>
            <th>First name</th>
            <th>Last name</th>
            <th>Payment Status</th>
            <th>Payment Method</th>
            <th>Delivery Method</th>
            <th>Order Status</th>
        </tr>
    <c:forEach items="${orders}" var="order">
        <tr>
        <c:set var = "client" scope = "session" value = "${order.client}"/>
            <td>${client.firstName}</td>
            <td>${client.lastName}</td>

        <c:set var = "paymentStatus" scope = "session" value = "${order.paymentStatus}"/>
            <td>${paymentStatus}</td>

        <c:set var = "paymentMethod" scope = "session" value = "${order.paymentMethod}"/>
            <td>${paymentMethod}</td>

        <c:set var = "deliveryMethod" scope = "session" value = "${order.deliveryMethod}"/>
            <td>${deliveryMethod}</td>

        <c:set var = "orderStatus" scope = "session" value = "${order.orderStatus}"/>
            <td>${orderStatus}</td>

            <td><a href="/edit">Edit</a> </td>

        <br>
        </tr>
    </c:forEach>

</div>
</body>
</html>
