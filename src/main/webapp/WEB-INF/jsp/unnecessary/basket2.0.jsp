<!DOCTYPE html>
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
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Basket</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="../resources/static/css/app.css">
</head>
<body>

<div class="container">

    <div class="row">
        <div class="col-sm-8">
            <h3 style="color: aliceblue">Basket</h3>
        </div>
        <div class="col-sm-4">
            <sec:authorize access="isAuthenticated()">
                <b style="color: aliceblue"> <a style="color: aliceblue" href="/personal"> <sec:authentication property="principal.username" /> </a> </b>
                <a style="color: aliceblue; padding-left: 20px" href="/logout"> Logout </a>
            </sec:authorize>
        </div>
    </div>

    <div class="row">
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="/">Home</a></li>
                <li class="breadcrumb-item"><a href="/phones">Phones</a></li>
                <li class="breadcrumb-item active" aria-current="page">Basket</li>
            </ol>
        </nav>
    </div>

    <div class="row">
        <table class="table table-hover" style="width: 65%; color: aliceblue">
        <tr>
            <th>Product ID</th>
            <th>Product name</th>
            <th>Price</th>
            <th>Amount</th>
            <th>Category</th>
            <th>Colour</th>
            <th>Brand</th>
        </tr>
            <c:forEach items="${phones}" var="product">
            <tr>
                <td>${product.id}</td>
                <td>${product.productName}</td>
                <td>${product.productPrice}</td>
                <td>${product.amount}</td>
                <td>${product.category}</td>
                <c:set var = "parameters" scope = "session" value = "${product.productParameteres}"/>
                <td>${parameters.colour}</td>
                <td>${parameters.brand}</td>
                <td>
                    <form method="post" action="/delete">
                        <input type="hidden" name="delete" value=${product.id}>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <input type="submit" value="Delete">
                    </form>
                </td>
            </tr>
        </c:forEach>
        </table>
    </div>

    <div class="row" style="color: aliceblue">
        <h5> Total price: </h5>
        <h5 style="padding-left: 35px"> ${sessionScope.totalPrice} </h5>
    </div>

    <form method="post" action="/confirm" style="width: 40%; color: aliceblue; margin-top: 15px">
        <p class="form-row align-items-center">
            <div class="col-auto my-1">
                <%--<label class="mr-sm-2" for="paymentMethod">Payment Method</label>--%>
                <select class="custom-select mr-sm-2" name="paymentMethod" id="paymentMethod">
                    <option selected>Payment Method</option>
                    <option value="CARD">CARD</option>
                    <option value="CASH">CASH</option>
                </select>
            </div>
            <div class="col-auto my-1">
                <%--<label class="mr-sm-2" for="deliveryMethod">Delivery method</label>--%>
                <select class="custom-select mr-sm-2" name="deliveryMethod" id="deliveryMethod">
                    <option selected>Delivery method</option>
                    <option value="COURIER">COURIER</option>
                    <option value="SELF">SELF</option>
                </select>
            </div>
            <div class="col-auto my-1">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-primary">Confirm</button>
            </div>
        </div>
    </form>



<%--<div>--%>
        <%--<form method="get" action="/confirm">--%>
            <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
            <%--<button type="submit" class="btn btn-secondary btn-md">Confirm</button>--%>
        <%--</form>--%>
    <%--</div>--%>


</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>