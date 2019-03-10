<!DOCTYPE html>
<%@ page import="com.e_shop.domain.Product" %>
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
    <title>E-shop</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="../resources/static/css/app.css">
</head>
<body>

<h2>Phones</h2>

    <div class="container">
        <div class="row">
         <c:forEach items="${phones}" var="phone">
            <div class="card" style="width: 17rem;">
                <img src="/resources/static/images/phones/phone1.jpeg" class="card-img-top" alt="...">
                <div class="card-body" >
                    <h5 class="card-title">${phone.productName}</h5>
                    <p class="card-text">${phone.productPrice}</p>
                </div>
                <c:set var = "params" scope = "session" value = "${phone.productParameteres}"/>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item" >${params.brand}</li>
                    <li class="list-group-item" >${params.colour}</li>
                </ul>
                <div class="card-body">
                    <a href="#" class="card-link">Buy</a>
                </div>
            </div>
         </c:forEach>
        </div>
    </div>

    <h2>Search</h2>
    <div class="container">
        <form method="post" action="/catalog">
            <p><select class="custom-select mr-sm-2" name="search_type" id="search">
                <option selected>Choose filter</option>
                <option value="Price">Price</option>
                <option value="Brand">Brand</option>
                <option value="Colour">Colour</option>
            </select></p>
            <input type="text" name="search_res" placeholder="filter">
            <button type="submit" value="OK">Search</button>
        </form>
    </div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>