<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="../resources/static/css/app.css">
</head>
<body>

<h3>e-shop of gadgets & electronic</h3>
<sec:authorize access="isAuthenticated()">
    <p>Welcome, <sec:authentication property="principal.username" /></p>
    <p><a href="/logout">Logout</a></p>
</sec:authorize>

<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal">Sign In</button>

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
                    <p>
                        <label for="username">Username</label>
                        <input type="text" id="username" name="username"/>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </p>
                    <p>
                        <label for="password">Password</label>
                        <input type="password" id="password" name="password"/>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </p>
                    <button onclick="form_submit()" name="user_search" class="btn btn-secondary" data-dismiss="modal">Log in</button>
                </form>
                <script type="text/javascript">
                    function form_submit() {
                        document.getElementById("search_form").submit();
                    }
                </script>
            </div>
        </div>
    </div>
</div>



<div id="mainpage">

    <div class="container">

        <div class="row">
            <div class="col-sm" style="height: 100px;">
                <span class="badge badge-secondary" >Logo</span>
            </div>
            <div class="col-sm-2">
                <button type="button" class="btn btn-outline-primary"><a href="/resources/static/views/support.html">Support</a></button>
            </div>
            <div class="col-sm-3">
                <button type="button" class="btn btn-outline-primary"><a href="/resources/static/views/payment.html">Payment & Delivery</a></button>
            </div>
            <div class="col-sm-2">
                <button type="button" class="btn btn-outline-primary"><a href="/resources/static/views/contacts.html">About us</a></button>
            </div>
            <div class="col-sm-1">
                <span class="badge badge-secondary">Basket</span>
            </div>
            <div class="col-sm-1">
                <span class="badge badge-secondary">Sign In</span>
            </div>
        </div>

        <div class="row justify-content-around" >
            <div class="col-sm-2" style="height: 150px;">
               <a href="/phones" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Phones</a>
            </div>
            <div class="col-sm-2">
                <a href="#" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">TV & Video</a>
            </div>
            <div class="col-sm-2">
                <a href="#" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Audio & Hi-Fi</a>
            </div>
            <div class="col-sm-2">
                <a href="#" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Laptops</a>
            </div>
            <div class="col-sm-2">
                <a href="#" class="btn btn-primary btn-lg active" role="button" aria-pressed="true">Tablets</a>
            </div>
        </div>

        <div class="row">
            <div class="card" style="width: 17rem;">
                <img src="/resources/static/images/tv.jpg" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">HIT 1</h5>
                    <p class="card-text">special offer</p>
                </div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">Description</li>
                    <li class="list-group-item">Common price</li>
                    <li class="list-group-item">Special price</li>
                </ul>
                <div class="card-body">
                    <a href="#" class="card-link">Buy</a>
                </div>
            </div>
            <div class="card" style="width: 17rem;">
                <img src="/resources/static/images/laptop.jpg" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">HIT 2</h5>
                    <p class="card-text">special offer</p>
                </div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">Description</li>
                    <li class="list-group-item">Common price</li>
                    <li class="list-group-item">Special price</li>
                </ul>
                <div class="card-body">
                    <a href="#" class="card-link">Buy</a>
                </div>
            </div>
            <div class="card" style="width: 17rem;">
                <img src="/resources/static/images/tablet.jpg" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">HIT 3</h5>
                    <p class="card-text">special offer</p>
                </div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">Description</li>
                    <li class="list-group-item">Common price</li>
                    <li class="list-group-item">Special price</li>
                </ul>
                <div class="card-body">
                    <a href="#" class="card-link">Buy</a>
                </div>
            </div>
            <div class="card" style="width: 17rem;">
                <img src="/resources/static/images/audio.jpg" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">HIT 4</h5>
                    <p class="card-text">special offer</p>
                </div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item">Description</li>
                    <li class="list-group-item">Common price</li>
                    <li class="list-group-item">Special price</li>
                </ul>
                <div class="card-body">
                    <a href="#" class="card-link">Buy</a>
                </div>
            </div>
        </div>
        <div>
            <sec:authorize access="hasRole('ADMIN')">
                <a href="/admin">Temp admin link</a>
            </sec:authorize>
        </div>
    </div>
</div>


<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>