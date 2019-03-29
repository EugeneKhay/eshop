<%@ page import="java.util.List" %>
<%@ page import="com.e_shop.domain.Product" %>
<%@ page import="com.e_shop.domain.Basket" %>
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
    <link rel="stylesheet" href="/resources/static/css/app.css">
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body style="background-image: url(/resources/static/images/background.jpg);">

<div class="container">
    <div class="row">
        <div class="col-sm-6">
            <h3 style="color: aliceblue">E-shop of gadgets & electronics</h3>
        </div>
        <div class="col-sm-3">
            <sec:authorize access="isAuthenticated()">
                <b style="color: aliceblue"> Welcome, <a style="color: aliceblue" href="/personal"> <sec:authentication property="principal.username" /> </a> </b>
                <%--<a style="color: aliceblue; padding-left: 20px" href="/personal"> Personal </a>--%>
                <a style="color: aliceblue; padding-left: 30px" href="/logout"> Logout </a>
            </sec:authorize>
        </div>
        <div class="col-sm-3">
            <sec:authorize access="hasRole('ADMIN')">
                <div class="col-sm-3"> <a style="color: aliceblue" href="/admin"> Admin </a> </div>
                <%--<div class="col-sm-3"> <a href="/order">Temp admin order</a> </div>--%>
            </sec:authorize>
        </div>
    </div>
</div>


<%--<h3>E-shop of gadgets & electronics</h3>--%>
<%--<sec:authorize access="isAuthenticated()">--%>
    <%--<b style="color: white"> Welcome, <sec:authentication property="principal.username" /></b>--%>
    <%--<a style="color: white" href="/personal"> Personal </a>--%>
    <%--<a style="color: white"href="/logout"> Logout </a>--%>
<%--</sec:authorize>--%>

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
                    <!--<button type="button" class="btn btn-secondary"><a href="/registration">Registration</a></button>-->
                    <a href="/registration" class="btn btn-secondary btn-md active" role="button" aria-pressed="true">Registration</a>

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
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item active" aria-current="page">Home</li>
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
                                <a class="nav-link" href="/phone"> Phones </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/tv"> TV & Video </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/audio"> Audio & Hi-Fi </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/laptop"> Laptops </a>
                            </li>
                            <li class="nav-item">
                                <a class="nav-link" href="/tablet"> Tablets </a>
                            </li>
                        </ul>
                    </div>
                </nav>
            </div>

            <div class="col-sm-1">
                <button type="button" class="btn btn-secondary">
                    <a style="color: aliceblue" href="/basket">Basket</a>
                    <%--<span class="badge badge-light"><%= ((Basket) session.getAttribute("shop_basket")).getProductsInBasket().keySet().size() %></span>--%>
                    <span class="badge badge-light"><%= ((Basket) session.getAttribute("shop_basket")).getProductsInBasket().values().stream().reduce((s1, s2) -> s1 + s2).orElse(0) %></span>
                </button>
            </div>

            <div class="col-sm-1">
                <button type="button" class="btn btn-secondary btn-md" data-toggle="modal" data-target="#exampleModal">Sign In</button>
            </div>
        </div>

        <div class="row">
            <div class="card" style="width: 17rem; background-color: transparent; color: aliceblue;">
                <img src="/resources/static/images/phones/tv.jpg" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">HIT 1</h5>
                    <p class="card-text">special offer</p>
                </div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item" style="background-color: transparent; color: aliceblue;">Description</li>
                    <li class="list-group-item" style="background-color: transparent; color: aliceblue;">Common price</li>
                    <li class="list-group-item" style="background-color: transparent; color: aliceblue;">Special price</li>
                </ul>
                <div class="card-body">
                    <a href="#" class="card-link">Buy</a>
                </div>
            </div>
            <div class="card" style="width: 17rem; background-color: transparent; color: aliceblue;">
                <img src="/resources/static/images/" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">HIT 2</h5>
                    <p class="card-text">special offer</p>
                </div>
                <ul class="list-group list-group-flush">
                    <li class="list-group-item" style="background-color: transparent;">Description</li>
                    <li class="list-group-item" style="background-color: transparent;">Common price</li>
                    <li class="list-group-item" style="background-color: transparent;">Special price</li>
                </ul>
                <div class="card-body">
                    <a href="#" class="card-link">Buy</a>
                </div>
            </div>
            <div class="card" style="width: 17rem; background-color: transparent; color: aliceblue;">
                <img src="/resources/static/images/" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">HIT 3</h5>
                    <p class="card-text">special offer</p>
                </div>
                <ul class="list-group list-group-flush" >
                    <li class="list-group-item" style="background-color: transparent;">Description</li>
                    <li class="list-group-item" style="background-color: transparent;">Common price</li>
                    <li class="list-group-item" style="background-color: transparent;">Special price</li>
                </ul>
                <div class="card-body">
                    <a href="#" class="card-link" >Buy</a>
                </div>
            </div>
            <div class="card" style="width: 17rem; background-color: transparent; color: aliceblue;">
                <img src="/resources/static/images/" class="card-img-top" alt="...">
                <div class="card-body">
                    <h5 class="card-title">HIT 4</h5>
                    <p class="card-text">special offer</p>
                </div>
                <ul class="list-group list-group-flush" style="background-color: transparent;">
                    <li class="list-group-item" style="background-color: transparent;">Description</li>
                    <li class="list-group-item" style="background-color: transparent;">Common price</li>
                    <li class="list-group-item" style="background-color: transparent;">Special price</li>
                </ul>
                <div class="card-body">
                    <a href="#" class="card-link">Buy</a>
                </div>
            </div>
        </div>
    </div>
</div>



<h3>Enter text:</h3>
<input id="input_str" type="text">
<input type="button" value="OK" onclick="doAjax()">

<script type="text/javascript">
    function doAjax() {
        $.ajax({
            url : '/ajax',
            type: 'GET',
            dataType: 'json',
            data : ({
                'text': $("#input_str").val()
            }),
            success: function (result) {
                alert(result.dayOfWeek);
            }
        });
    }
</script>

<footer style="color: aliceblue; margin-left: 50px">
    <p> Copyright ...</p>
    <p> Our contacts ...</p>
    <p> Support... </p>
</footer>


<%--<script src="https://code.jquery.com/jquery-3.3.1.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>--%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>