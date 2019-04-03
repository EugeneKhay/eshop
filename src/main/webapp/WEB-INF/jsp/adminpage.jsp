<%@ page import="com.e_shop.domain.Product" %>
<%@ page import="java.util.ArrayList" %><%--
  Created by IntelliJ IDEA.
  User: evgenijhajmovskij
  Date: 2019-03-03
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>E-shop</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="/resources/static/css/app.css">
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="//ajax.aspnetcdn.com/ajax/jquery.ui/1.10.3/jquery-ui.min.js"></script>
    <%--<script src="/resources/static/js/app.js"></script>--%>
</head>
<body>

<div class="container" style="color: aliceblue; margin: 0 auto">

    <div>
        <h3 style="color: aliceblue"> Admin page </h3>
    </div>

    <div class="row">
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="/"> Home </a></li>
                <li class="breadcrumb-item active" aria-current="page"> Admin </li>
            </ol>
        </nav>
    </div>

    <%--<div class="row">--%>
        <%--<div class="btn-group" role="group" aria-label="Button group with nested dropdown">--%>
            <%--<div class="btn-group" role="group">--%>
                <%--<button id="btnGroupDrop1" type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">--%>
                    <%--Control menu--%>
                <%--</button>--%>
                <%--<div class="dropdown-menu" aria-labelledby="btnGroupDrop1">--%>
                    <%--<a class="dropdown-item" href="/orders"> Orders </a>--%>
                    <%--<a class="dropdown-item" href="/viewproducts"> Products </a>--%>
                    <%--<a class="dropdown-item" href="/viewclients"> Clients </a>--%>
                <%--</div>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>



    <br>

    <div class="row" style="color: aliceblue; margin: 0 auto">
        <h4> Statistics </h4>
    </div>

    <br>

    <div class="row" style="margin: 0 auto">
        <h5> ${period}</h5>
    </div>

    <br>

    <div class="row" style="color: aliceblue; background-color: transparent">

        <div class="col-sm-2">
                <div class="btn-group-vertical" role="group" aria-label="Basic example">
                    <button type="button" class="btn btn-secondary" onclick="orders()"> Orders </button>
                    <button type="button" class="btn btn-secondary" onclick="products()">Products</button>
                    <button type="button" class="btn btn-secondary" onclick="clients()"> Clients</button>
                    <button type="button" class="btn btn-secondary" onclick="stats()"> Statistics</button>
                </div>
        </div>

        <div class="col-sm-1">
            <button type="button" class="btn btn-secondary btn-md" data-toggle="modal" data-target="#exampleModal">Change period</button>
        </div>

        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel2" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 style="color: black" class="modal-title" id="exampleModalLabel2">Enter period</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form method="post" action="/admin" id="time_form">
                            <div class="form-group">
                                <input type="date" name="start" class="form-control" placeholder="Start">
                            </div>
                            <div class="form-group">
                                <input type="date" name="finish" class="form-control" placeholder="End">
                            </div>
                            <button onclick="time_submit()" name="user_search" class="btn btn-secondary" data-dismiss="modal">Enter</button>
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <div class="col-sm-10" id="stats" style="display: none">

                    <li> Total sum of all orders: ${totalSumOfAllOrders}</li>
                    <br>

                    <li> Total amount of orders: ${totalAmountOfOrders}</li>
                    <br>

                    <li> Top products:
                        <ol>
                            <c:forEach items="${bestProducts}" var="product">
                                <c:set var = "params" scope = "session" value = "${product.productParameteres}"/>
                                <li> ${params.brand} ${product.productName} ${params.colour}</li>
                            </c:forEach>
                        </ol>
                    </li>

                    <li> Top clients:
                        <ol>
                            <c:forEach items="${output}" var="data">
                                <li> ${data}</li>
                            </c:forEach>
                        </ol>
                    </li>

                    <%--<li>Top clients:--%>
                            <%--<ol>--%>
                                <%--<c:forEach items="${bestClient}" var="client">--%>
                                    <%--<li>${client.firstName} ${client.lastName}</li>--%>
                                <%--</c:forEach>--%>
                            <%--</ol>--%>
                    <%--</li>--%>

                    <%--<li> Number of orders:--%>
                            <%--<ol>--%>
                                <%--<c:forEach items="${bestNumberOfOrders}" var="number">--%>
                                    <%--<li> - ${number} orders </li>--%>
                                <%--</c:forEach>--%>
                            <%--</ol>--%>
                    <%--</li>--%>
        </div>

            <div id="orders" style="display: none">
                <div class="row">
                <table class="table table-borderless" style="margin: 0 auto; color: aliceblue">
                    <tr>
                        <th>Order number</th>
                        <th>Client</th>
                        <th>Products</th>
                        <th>Sum</th>
                        <th>Date of order</th>
                        <th>Payment method</th>
                        <th>Delivery method</th>
                        <th>Order status</th>
                        <th>Payment status</th>
                    </tr>
                    <c:forEach items="${orders}" var="order">
                        <tr>
                            <c:set var = "client" scope = "session" value = "${order.client}"/>
                            <td>${order.id}</td>
                            <td>${client.firstName} ${client.lastName}</td>
                            <td>
                                <%--<c:set var = "products" scope = "session" value = "${order.productsInOrder}"/>--%>
                                <c:forEach items="${order.productsInOrder}" var="product">
                                    <table>
                                        <tr>${product.productName}</tr>
                                    </table>
                                </c:forEach>
                            </td>
                            <td>${order.sumOfOrder}</td>
                            <td>${order.dateOfOrder}</td>
                            <td>${order.paymentMethod}</td>
                            <td>${order.deliveryMethod}</td>
                            <td>${order.orderStatus}</td>
                            <td>${order.paymentStatus}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
                <div class="col-sm-1">
                <button type="button" class="btn btn-secondary btn-md" data-toggle="modal" data-target="#editModal"> Edit </button>
            </div>
                <div class="modal fade" id="editModal" tabindex="-1" role="dialog" aria-labelledby="editModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="editModalLabel"> Edit data </h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form action="/editorder" method="post" id="edited_order">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                <p>
                                    <label for="orderForEdit"> Order number </label>
                                    <input type="number" id="orderForEdit" name="orderForEdit"/>
                                </p>
                                <p>
                                    <label for="paymentStatus"> Payment status </label>
                                    <select class="custom-select mr-sm-2" name="paymentStatus" id="paymentStatus">
                                        <option selected>NOT_PAID</option>
                                        <option >PAID</option>
                                    </select>
                                </p>
                                <p>
                                    <label for="orderStatus"> Order status </label>
                                    <select class="custom-select mr-sm-2" name="orderStatus" id="orderStatus">
                                        <option selected>WAITING_FOR_PAYMENT</option>
                                        <option >WAITING_FOR_SHIPPMENT</option>
                                        <option >SHIPPED</option>
                                        <option >DELIVERIED</option>
                                    </select>
                                </p>
                                <button onclick="edit_submit()" name="user_search" class="btn btn-secondary" data-dismiss="modal"> Submit </button>
                            </form>
                            <script type="text/javascript">
                                function edit_submit() {
                                    document.getElementById("edited_order").submit();
                                }
                            </script>
                        </div>
                    </div>
                </div>
            </div>
            </div>

            <div id="products" style="display: none; width: 90%">
                    <h3 style="color: aliceblue">List of products</h3>
                    <table class="table table-borderless" style="withdth: 65%; margin: 0 auto; color: aliceblue">
                    <%--<table class="table table-borderless" style=" color: aliceblue">--%>
                        <tr>
                            <th>Product ID</th>
                            <th>Product name</th>
                            <th>Brand</th>
                            <th>Price</th>
                            <th>Amount</th>
                            <th>Category</th>
                            <th>Colour</th>
                        </tr>
                        <c:forEach items="${products}" var="product">
                            <tr>
                                <c:set var = "parameters" scope = "session" value = "${product.productParameteres}"/>
                                <td>${product.id}</td>
                                <td>${product.productName}</td>
                                <td>${parameters.brand}</td>
                                <td>${product.productPrice}</td>
                                <td>${product.amount}</td>
                                <td>${product.category}</td>
                                <td>${parameters.colour}</td>
                            </tr>
                        </c:forEach>
                    </table>

                    <h3 style="margin: 0 auto; color: aliceblue">Add new product</h3>
                    <form method="post" action="/addproducts" style="width: 35%; margin: 0 auto">
                        <b style="color:aliceblue"> Enter product properties </b>
                        <div class="form-group">
                            <label style="color: aliceblue" for="productName"> Product name </label>
                            <input style="background: transparent; color: aliceblue" class="form-control form-control-md" name="productName" type="text" class="form-control" id="productName" aria-describedby="emailHelp" placeholder="">
                        </div>
                        <div class="form-group">
                            <label style="color: aliceblue" for="productPrice"> Product price </label>
                            <input style="background: transparent; color: aliceblue" class="form-control form-control-md" name="productPrice" type="number" class="form-control" id="productPrice" aria-describedby="emailHelp" placeholder="">
                        </div>
                        <div>
                            <label style="color: aliceblue" for="productPrice"> Product type </label>
                            <select class="custom-select mr-sm-2" name="category" id="category" style="color: aliceblue; background: transparent">
                                <option selected>Choose category</option>
                                <option value="PHONE"> PHONE </option>
                                <option value="TV_VIDEO"> TV_VIDEO </option>
                                <option value="AUDIO"> AUDIO </option>
                                <option value="LAPTOP"> LAPTOP </option>
                                <option value="TABLET"> TABLET </option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label style="color: aliceblue" for="productPrice">Amount</label>
                            <input style="background: transparent; color: aliceblue" class="form-control form-control-md" name="amount" type="number" class="form-control" id="amount" aria-describedby="emailHelp" placeholder="">
                        </div>
                        <p><b style="color:aliceblue"> Enter product parameteres: </b></p>
                        <div class="form-group">
                            <label style="color: aliceblue" for="productPrice"> Colour </label>
                            <input style="background: transparent; color: aliceblue" class="form-control form-control-md" name="colour" type="text" class="form-control" id="colour" aria-describedby="emailHelp" placeholder="">
                        </div>
                        <div class="form-group">
                            <label style="color: aliceblue" for="productPrice"> Brand </label>
                            <input style="background: transparent; color: aliceblue" class="form-control form-control-md" name="brand" type="text" class="form-control" id="brand" aria-describedby="emailHelp" placeholder="">
                        </div>
                        <button type="submit" class="btn btn-secondary"> Add product </button>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
            </div>

            <div id="clients" style="display: none">

                    <h3>List of clients</h3>
                    <table class="table table-borderless" style="width: 65%; margin: 0 auto; color: aliceblue">
                        <tr>
                            <th>ID</th>
                            <th>First name</th>
                            <th>Last name</th>
                            <th>Date of birth</th>
                            <th>Email</th>
                            <th>Password</th>
                            <th>Address</th>

                            <%--<th>Country</th>--%>
                            <%--<th>City</th>--%>
                            <%--<th>Post code</th>--%>
                            <%--<th>Street</th>--%>
                            <%--<th>Hose number</th>--%>
                            <%--<th>Flat number</th>--%>
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
                                <td>${address.country}, ${address.city}, ${address.postCode}, ${address.street}, ${address.houseNumber}, ${address.flatNumber}</td>


                                <%--<td>${address.country}</td>--%>
                                <%--<td>${address.city}</td>--%>
                                <%--<td>${address.postCode}</td>--%>
                                <%--<td>${address.street}</td>--%>
                                <%--<td>${address.houseNumber}</td>--%>
                                <%--<td>${address.flatNumber}</td>--%>
                            </tr>
                        </c:forEach>
                    </table>
            </div>

        </div>
    </div>
</div>

<%--<script>--%>
    <%--function form_submit() {--%>
        <%--document.getElementById("search_form").submit();--%>
    <%--}--%>
<%--</script>--%>
<%--<script>--%>
<%--function stats() {--%>
<%--$("#stats").css({"display" : "block"});--%>
<%--$("#orders").css({"display" : "none"});--%>
<%--$("#products").css({"display" : "none"});--%>
<%--$("#clients").css({"display" : "none"});--%>
<%--}--%>
<%--function orders() {--%>
<%--$("#orders").css({"display" : "block"});--%>
<%--$("#stats").css({"display" : "none"});--%>
<%--$("#products").css({"display" : "none"});--%>
<%--$("#clients").css({"display" : "none"});--%>
<%--}--%>
<%--function products() {--%>
<%--$("#products").css({"display" : "block"});--%>
<%--$("#stats").css({"display" : "none"});--%>
<%--$("#orders").css({"display" : "none"});--%>
<%--$("#clients").css({"display" : "none"});--%>
<%--}--%>
<%--function clients() {--%>
<%--$("#clients").css({"display" : "block"});--%>
<%--$("#products").css({"display" : "none"});--%>
<%--$("#stats").css({"display" : "none"});--%>
<%--$("#orders").css({"display" : "none"});--%>
<%--}--%>
<%--</script>--%>

<%--<footer style="color: aliceblue; margin-left: 50px">--%>
    <%--<p> Copyright ...</p>--%>
    <%--<p> Our contacts ...</p>--%>
    <%--<p> Support... </p>--%>
<%--</footer>--%>
<script type="text/javascript" src="../resources/static/js/app2.js"/>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
