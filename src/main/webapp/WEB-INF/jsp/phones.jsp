<!DOCTYPE html>
<%@ page import="com.e_shop.domain.Product" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.e_shop.domain.Basket" %><%--
  Created by IntelliJ IDEA.
  User: evgenijhajmovskij
  Date: 2019-03-03
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>E-shop</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="../resources/static/css/app.css">
</head>
<body style="background-image: url(/resources/static/images/background.jpg);">

<div class="container">
    <div class="row">
        <div class="col-sm-8">
            <h3 style="color: aliceblue">Phones</h3>
        </div>
        <div class="col-sm-4">
            <sec:authorize access="isAuthenticated()">
                <%--<b style="color: aliceblue"> <sec:authentication property="principal.username" /></b>--%>
                <b style="color: aliceblue"> <a style="color: aliceblue" href="/personal"> <sec:authentication property="principal.username" /> </a> </b>
                <%--<a style="color: aliceblue; padding-left: 20px" href="/personal"> Personal </a>--%>
                <a style="color: aliceblue; padding-left: 20px" href="/logout"> Logout </a>
            </sec:authorize>
        </div>
    </div>
</div>

<div class="container">
    <div class="row">
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="/">Home</a></li>
                <li class="breadcrumb-item active" aria-current="page">Phones</li>
            </ol>
        </nav>
    </div>

    <div class="row">
        <div class="col-sm-1" style="height: 100px;">
            <span class="badge badge-secondary" >Logo</span>
        </div>

        <div class="col-sm">
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                <div class="collapse navbar-collapse" id="navbarNavDropdown">
                    <ul class="navbar-nav">
                        <li class="nav-item">
                            <a class="nav-link active" href="/phones"> Phones </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#"> TV & Video </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#"> Audio & Hi-Fi </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#"> Laptops </a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="#"> Tablets </a>
                        </li>
                    </ul>
                </div>
            </nav>
        </div>

        <div class="col-sm-1">
            <button type="button" class="btn btn-primary">
                <a href="/basket">Basket</a>
                <span class="badge badge-light"><%= ((Basket) session.getAttribute("shop_basket")).getProductList().size() %></span>
            </button>
        </div>

        <div class="col-sm-1">
            <button type="button" class="btn btn-primary btn-md" data-toggle="modal" data-target="#exampleModal">Sign In</button>
        </div>
    </div>

    <div class="row">
        <c:forEach items="${phones}" var="phone">
            <form class="card" style="width: 17rem; background-color: transparent; color: aliceblue;" action="/basket" method="post">
                <img src="/static/images/phones/qw.png" class="card-img-top" alt="...">
                <div class="card-body" >
                    <h5 class="card-title">${phone.productName}</h5>
                    <p class="card-text">${phone.productPrice}</p>
                </div>
                <c:set var = "params" scope = "session" value = "${phone.productParameteres}"/>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item" style="width: 17rem; background-color: transparent; color: aliceblue;">${params.brand}</li>
                    <li class="list-group-item" style="width: 17rem; background-color: transparent; color: aliceblue;">${params.colour}</li>
                </ul>
                <input  type="hidden" name="item" value=${phone.id}>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit">Buy</button>
            </form>
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
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        </form>
    </div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>