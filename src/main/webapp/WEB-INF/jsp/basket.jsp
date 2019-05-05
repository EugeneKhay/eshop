<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>Basket</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="../resources/static/css/style.css"/>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>

<div class="container">

    <div class="row" id="basketTitle">
        <div class="col-sm">
            <h3>Basket</h3>
        </div>
    </div>

    <!-- Nav -->
    <div class="row" id="basketNav">
        <div class="col-sm-3">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb" style="background-color: transparent">
                    <li class="breadcrumb-item"><a href="/">Home</a></li>
                    <li class="breadcrumb-item"><a href="/phone">Products</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Basket</li>
                </ol>
            </nav>
        </div>
        <div class="col-sm">
        </div>
        <div class="col-sm-1">
            <sec:authorize access="isAuthenticated()">
                <button type="button" class="btn btn-secondary">
                    <a style="color: aliceblue" href="/logout"> Clear </a>
                </button>
            </sec:authorize>
        </div>
        <!-- Sign In-->
        <div class="col-sm-1">
            <button type="button" class="btn btn-primary-outline btn-md" data-toggle="modal" data-target="#exampleModal">
                <span><img style="width: 45px; height: 45px" src="/resources/static/images/sign_in2.png"/> </span>
            </button>
        </div>
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


<%-- Table --%>
    <div class="row">
            <table class="table" style="width: 80%; color: black; margin: 0 auto">
                <tr>
                    <th>Product ID</th>
                    <th>Product name</th>
                    <th>Price</th>
                    <th>Amount</th>
                    <th>Category</th>
                    <th>Colour</th>
                    <th>Brand</th>
                    <th>Change amount</th>
                </tr>
                <c:forEach items="${items}" var="entry">
                    <c:set var = "product" scope = "session" value = "${entry.key}"/>
                    <c:set var = "amount" scope = "session" value = "${entry.value}"/>
                    <div>
                    <tr id="product-${product.id}del">
                        <td>${product.id}</td>
                        <td>${product.productName}</td>
                        <td>${product.productPrice}</td>
                        <td id="amount${product.id}edit">${amount}</td>
                        <td>${product.category}</td>
                        <c:set var = "parameters" scope = "session" value = "${product.productParameteres}"/>
                        <td>${parameters.colour}</td>
                        <td>${parameters.brand}</td>
                        <td>
                            <div class="btn-group" role="group" aria-label="Basic example">
                                <button onclick="deleteItem(${product.id})" type="submit" class="btn btn-secondary"> Delete </button>
                                <button onclick="increase(${product.id})" type="submit" class="btn btn-secondary"> + </button>
                                <button onclick="decrease(${product.id})" type="submit" class="btn btn-secondary"> - </button>
                            </div>
                        </td>
                    </tr>
                    </div>
                </c:forEach>
            </table>
        </div>

    <!-- Total price -->
    <div class="row" id="price">
        <div class="col-sm-4" style="text-align: center">
            <h5> Total price: </h5>
        </div>
        <div class="col-sm-8"  style="text-align: left">
            <h5 id="totalPrice"> ${sessionScope.totalPrice}</h5>
        </div>
    </div>
    <br>

    <!-- Confirm -->
    <sec:authorize access="isAuthenticated()">
    <form method="post" action="/confirm" style="width: 80%">
        <div class="row">

            <div class="container">
                <div class="row">
                    <div class="col-sm">
                        <p>Client: <sec:authentication property="principal.firstName" /> <sec:authentication property="principal.lastName" /></p>
                        <p>Please, choose payment and delivery method</p>
                    </div>
                </div>
                <br>
                <div class="row">
                    <div class="col-sm-4">
                        <p>Payment Method</p>

                        <%--<div class="form-check form-group" >--%>
                            <%--<input class="form-check-input" type="checkbox" name="paymentMethod" value="CARD" id="defaultCheck1">--%>
                            <%--<label class="form-check-label" for="defaultCheck1">Card</label>--%>
                        <%--</div>--%>
                            <%--<div class="form-check form-group">--%>
                            <%--<input class="form-check-input" type="checkbox" name="paymentMethod" value="CASH" id="defaultCheck2">--%>
                            <%--<label class="form-check-label" for="defaultCheck2">Cash</label>--%>
                            <%--</div>--%>

                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="paymentMethod" id="exampleRadios1" value="CARD">
                            <label class="form-check-label" for="exampleRadios1">Card</label>
                        </div>
                        <br>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="paymentMethod" id="exampleRadios2" value="CASH">
                            <label class="form-check-label" for="exampleRadios2">Cash</label>
                        </div>
                    </div>
                    <div class="col-sm" style="display: none" id="carddata">
                        <p>Enter your card data:</p>
                        <p>Input card data</p>
                    </div>
                    <script>
                        $("#exampleRadios1").change(function(){
                            if(this.checked){
                                $("#carddata").css({"display" : "block"});
                            }
                        });
                        $("#exampleRadios2").change(function(){
                            if(this.checked){
                                $("#carddata").css({"display" : "none"});
                            }
                        });
                    </script>
                </div>
                <br>
                <br>
                <div class="row">
                    <div class="col-sm-4">
                        <p>Delivery method</p>

                        <%--<div class="form-check form-group">--%>
                            <%--<input class="form-check-input" type="checkbox" name="deliveryMethod" value="COURIER" id="defaultCheck3">--%>
                            <%--<label class="form-check-label" for="defaultCheck3">Courier</label>--%>
                        <%--</div>--%>
                            <%--<div class="form-check form-group">--%>
                            <%--<input class="form-check-input" type="checkbox" name="deliveryMethod" value="SELF" id="defaultCheck4">--%>
                            <%--<label class="form-check-label" for="defaultCheck4">Self</label>--%>
                            <%--</div>--%>

                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="deliveryMethod" id="exampleRadios3" value="COURIER">
                            <label class="form-check-label" for="exampleRadios3">Courier</label>
                        </div>
                        <br>
                        <div class="form-check">
                            <input class="form-check-input" type="radio" name="deliveryMethod" id="exampleRadios4" value="SELF">
                            <label class="form-check-label" for="exampleRadios4">Self</label>
                        </div>
                    </div>
                    <div class="col-sm" style="display: none" id="addressdata">
                        <p>Please, choose address for delivery:</p>
                            <c:forEach items="${addresses}" var="item">
                                <div class="form-check">
                                    <input class="form-check-input" type="radio" name="deliveryMethod" id="delivery" value="${item}">
                                    <label class="form-check-label" for="delivery">${item}</label>
                                </div>
                                <br>
                            </c:forEach>
                            <div>
                                <button type="button" class="btn btn-secondary" onclick="changeAddress()"> Change </button>
                            </div>
                            <script type="text/javascript">
                                function changeAddress() {
                                    $("#add").toggle();
                                }
                            </script>
                            <br>
                    </div>
                    <script>
                        $("#exampleRadios3").change(function(){
                            if(this.checked){
                                $("#addressdata").css({"display" : "block"});
                            }
                        });
                        $("#exampleRadios4").change(function(){
                            if(this.checked){
                                $("#addressdata").css({"display" : "none"});
                            }
                        });
                    </script>
                </div>
                <br>
                <br>
                <div class="row">
                    <div class="col-sm-4">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        <div class="input-group-append">
                            <button class="btn btn-secondary" type="submit">Confirm</button>
                        </div>
                    </div>
                </div>

            </div>

        </div>
    </form>
    </sec:authorize>

    <!-- Add address form -->
    <div id="add" style="display: none; width: 80%; margin: 0 auto">
        <form action="/addaddress" method="post" id="add_adr">
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            <div class="form-group">
                <label for="country" style="color: black">Country</label>
                <input class="form-control form-control-md" type="text" id="country" name="country"/>
            </div>
            <div class="form-group">
                <label for="city" style="color: black">City</label>
                <input class="form-control form-control-md" type="text" id="city" name="city" />
            </div>
            <div class="form-group">
                <label for="postcode" style="color: black">Postcode</label>
                <input class="form-control form-control-md" type="number" id="postcode" name="postcode" />
            </div>
            <div class="form-group">
                <label for="street" style="color: black">Street</label>
                <input class="form-control form-control-md" type="text" id="street" name="street" />
            </div>
            <div class="form-group">
                <label for="houseNumber" style="color: black">House number</label>
                <input class="form-control form-control-md" type="number" id="houseNumber" name="houseNumber" />
            </div>
            <div class="form-group">
                <label for="flatNumber" style="color: black">Flat number</label>
                <input class="form-control form-control-md" type="number" id="flatNumber" name="flatNumber" />
            </div>
            <button onclick="form3_submit()" name="user_search" class="btn btn-secondary" data-dismiss="modal"> Submit </button>
            <script>
                function form3_submit() {
                    document.getElementById("add_adr").submit();
                };
            </script>
        </form>
    </div>

<br>
<br>
<br>
<br>
<br>
    <div class="footer">
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
<script type="text/javascript" src="../resources/static/js/app2.js"/>
<%--<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>--%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
















