
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
    <link rel="stylesheet" type="text/css" href="../resources/static/css/style.css"/>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
<div class="page-container">

<div class="container" style="margin-top: 40px">

    <div class="row">
        <div class="col-sm">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb" style="background-color: transparent">
                    <li class="breadcrumb-item"><a href="/">Home</a></li>
                    <li class="breadcrumb-item"><a href="/phone">Products</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Error</li>
                </ol>
            </nav>
        </div>

        <!-- Sign In-->
        <div class="col-sm-1">
            <button type="button" class="btn btn-primary-outline btn-md" data-toggle="modal" data-target="#exampleModal">
                <span><img style="width: 45px; height: 45px" src="/resources/static/images/sign_in2.png"/> </span>
            </button>
        </div>

        <%--<div class="col-sm-2">--%>
            <%--<button type="button" class="btn btn-secondary btn-md" data-toggle="modal" data-target="#exampleModal">Sign In</button>--%>
        <%--</div>--%>
    </div>

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
                            </p>
                            <p>
                                <label for="password">Password</label>
                                <input type="password" id="password" name="password"/>
                            </p>
                            <button onclick="form_submit()" name="user_search" class="btn btn-secondary" data-dismiss="modal">Log in</button>
                            <a href="/registration" class="btn btn-secondary btn-md active" role="button" aria-pressed="true">Registration</a>

                        </form>
                    </div>
                </div>
            </div>
        </div>

    <div align="center" id="errormessage" style="color: black">
        <c:if test="${not empty errMsg}">
            <h4 class="text-danger">${errMsg}</h4>
            <br>
            <h4>Please, try again or contact our support:</h4>
            <h5>phone number: 8 800 2000 600</h5>
            <h5>email: support@eshop.com</h5>
        </c:if>
    </div>
</div>
</div>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<br>
<div class="footer" style="color: black; margin-left: 30px">
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






</body>
<script type="text/javascript" src="../resources/static/js/app2.js"/>
<%--<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>--%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>