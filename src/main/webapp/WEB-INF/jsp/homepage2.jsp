<!DOCTYPE html>
<%@ page import="com.eshop.domain.Basket" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>E-shop</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="https://fonts.googleapis.com/css?family=Dancing+Script" rel="stylesheet">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="/resources/static/css/style.css"/>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>

<div class="wrapper">

    <div class="wrapperForFooter">

        <div class="header">
            <div class="row" id="title">
                <div id="shopname" class="col-sm-9">
                    <h3>E-shop. Gadgets & electronics</h3>
                </div>
                <div class="col-sm-1">
                    <sec:authorize access="hasRole('ADMIN')">
                        <button type="button" class="btn btn-secondary btn-sm"><a style="color: aliceblue" href="/admin"/> Admin </button>
                    </sec:authorize>
                </div>
                <div class="col-sm-1">
                    <sec:authorize access="hasRole('USER')">
                        <strong style="color: black"> Account: <a style="color: black" href="/personal"> <sec:authentication property="principal.firstName" /> </a> </strong>
                    </sec:authorize>
                </div>
                <div class="col-sm-1">
                    <sec:authorize access="isAuthenticated()">
                        <button type="button" class="btn btn-secondary btn-sm"><a style="color: aliceblue" href="/logout"/> Logout </button>
                    </sec:authorize>
                </div>
            </div>

            <!--Modal window sign in-->
            <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Please, sign in</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <c:url var="loginUrl" value="/login" />
                            <form action="${loginUrl}" method="post" id="search_form">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <c:if test="${param.error != null}">
                                    <p>
                                        Invalid username and password.
                                    </p>
                                </c:if>
                                <div class="form-group">
                                    <label for="username">E-mail</label>
                                    <input class="form-control form-control-md" type="text" id="username" name="username"required/>
                                </div>
                                <div class="form-group">
                                    <label for="password">Password</label>
                                    <input class="form-control form-control-md" type="password" id="password" name="password" required/>
                                </div>
                                <button onclick="form_submit()" name="user_search" class="btn btn-secondary" data-dismiss="modal">Log in</button>
                                <a href="/registration" class="btn btn-secondary btn-md active" role="button" aria-pressed="true">Registration</a>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <!--Nav-->
            <div class="row" id="navigation">

                <div class="col-sm-1"></div>

                <div class="col-sm">
                    <ul class="nav justify-content-center">
                        <c:forEach items="${categories}" var="category">
                            <li class="nav-item">
                                <a class="nav-link" href="/${category}">${category}</a>
                            </li>
                        </c:forEach>
                    </ul>
                </div>

                <!-- Basket -->
                <div class="col-sm-1">
                    <button type="button" class="btn btn-primary-outline">
                        <a href="/basket" style="color: aliceblue">
                            <span><img style="width: 45px; height: 30px" src="/resources/static/images/basket2.png" alt="Basket"/> </span>
                            <span class="badge badge-light" id="basketCount2"><%= ((Basket) session.getAttribute("shop_basket")).getProductsInBasket().values().stream().reduce((s1, s2) -> s1 + s2).orElse(0) %></span>
                        </a>
                    </button>
                </div>

                <!-- Sign In-->
                <div class="col-sm-1">
                    <button type="button" class="btn btn-primary-outline btn-md" data-toggle="modal" data-target="#exampleModal">
                        <span><img style="width: 45px; height: 45px" src="/resources/static/images/sign_in2.png" alt="Sign in"/> </span>
                    </button>
                </div>
            </div>
        </div>

        <div class="container content">

            <div class="row">
                <h5 style="margin-bottom: 50px; text-align: center">Our bestsellers:</h5>
            </div>

            <!-- Cards -->
            <div class="row" >
                <div class="cardparent">
                    <c:forEach items="${items}" var="item">
                        <div class="cardchild">
                                <div class="card" >
                                    <c:url var="imageUrl" value="${item.imagePath}" />
                                    <img style="margin-top: 10px" src="${imageUrl}/1.jpg" class="card-img-top" alt="Image">
                                    <div class="card-body">
                                        <h5 class="card-title">${item.productName}</h5>
                                        <p class="card-text">${item.productPrice} ₽</p>
                                        <c:set var = "params" scope = "session" value = "${item.productParameteres}"/>
                                        <ul class="list-group list-group-flush">
                                            <li class="list-group-item" >${params.brand}</li>
                                            <li class="list-group-item" >${params.colour}</li>
                                        </ul>
                                        <input  type="hidden" name="item" value=${item.id}>
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                        <div>
                                            <button onclick="add(${item.id})" type="submit" class="btn btn-secondary" > Add </button>
                                        </div>
                                        <br>
                                        <br>
                                    </div>
                                </div>
                        </div>
                    </c:forEach>
                </div>
            </div>

            <br>
            <br>



            <div class="row" id="text">
                <h5>High level of practice orientation</h5>
                <p>Another key component of working at E-shop is a high level of practice orientation from the word go. This means that vocational trainees are given the opportunity to get to know all the departments and categories of products in a store, while more experienced employees are provided with regular product, customer relations or sales strategy training courses.</p>
                <br>
            </div>

            <div class="row">
                <div class="col-sm">
                    <img style="height: 60px; width: 60px" src="/resources/static/images/tracking.png">
                    FREE SHIPMENT WITHIN EU
                </div>
                <div class="col-sm">
                    <img style="height: 60px; width: 60px"src="/resources/static/images/return-to-the-past.png">
                    7 DAYS FOR RETURN
                </div>
                <div class="col-sm">
                    <img style="height: 60px; width: 60px"src="/resources/static/images/customer-support.png">
                    CUSTOMER SUPPORT 24 H
                </div>
            </div>

            <style>
                .cardparent {
                    width: 1200px;
                    /*height:600px;*/
                    overflow: hidden;
                    overflow-x: scroll;
                    white-space:nowrap;
                }
                .cardchild {
                    display: inline-block;
                    vertical-align: top;
                    width: 15rem;
                    /*height:600px;*/
                }
            </style>

            <br>
            <br>
            <br>

        </div>

        <div class="footer" >
            <div class="container">
                <div class="row">
                    <div class="col-sm-5" style="padding: 20px">
                        <p>Phone: 8 800 2000 600, 8 800 5353 777</p>
                        <p>Email: shop@eshop.com, info@eshop.com</p>
                    </div>
                    <div class="col-sm" style="padding: 20px">
                        <p>Address: Russia</p>
                        <p>SPb, Somestreet st., 35</p>
                    </div>
                    <div class="col-sm" style="padding: 20px">
                        <p>Social nets: </p>
                        <p>link1, link2</p>
                    </div>
                </div>
            </div>
        </div>

    </div>

</div>



<script type="text/javascript" src="../resources/static/js/app2.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>