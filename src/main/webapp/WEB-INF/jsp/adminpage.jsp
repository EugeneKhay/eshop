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
    <link rel="stylesheet" href="/resources/static/css/app.css">
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

    <div class="row">
        <div class="btn-group" role="group" aria-label="Button group with nested dropdown">
            <div class="btn-group" role="group">
                <button id="btnGroupDrop1" type="button" class="btn btn-secondary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Control menu
                </button>
                <div class="dropdown-menu" aria-labelledby="btnGroupDrop1">
                    <a class="dropdown-item" href="/orders"> Orders </a>
                    <a class="dropdown-item" href="/viewproducts"> Products </a>
                    <a class="dropdown-item" href="/viewclients"> Clients </a>
                </div>
            </div>
        </div>
    </div>

    <br>

    <div class="row" style="color: aliceblue; margin: 0 auto">
        <h4> Statistics </h4>
    </div>

    <div class="row" style="color: aliceblue; margin: 0 auto">
        <h6> Period </h6>
    </div>

    <div class="row" style="color: aliceblue; background-color: transparent">
            <form action="/statistics" method="post" id="search_form">
                <div class="form-inline">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div style="width: 150px; color: aliceblue; background-color: transparent">
                    <div class="form-inline">
                    <select style="color: aliceblue; background-color: transparent" class="custom-select mr-sm-2" name="startDay">
                        <option selected>start day</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>
                        <option value="11">11</option>
                        <option value="12">12</option>
                        <option value="13">13</option>
                        <option value="14">14</option>
                        <option value="15">15</option>
                        <option value="16">16</option>
                        <option value="17">17</option>
                        <option value="18">18</option>
                        <option value="19">19</option>
                        <option value="20">20</option>
                        <option value="21">21</option>
                        <option value="22">22</option>
                        <option value="23">23</option>
                        <option value="24">24</option>
                        <option value="25">25</option>
                        <option value="26">26</option>
                        <option value="27">27</option>
                        <option value="28">28</option>
                        <option value="29">29</option>
                        <option value="30">30</option>
                        <option value="31">31</option>
                    </select>
                    <select style="color: aliceblue; background-color: transparent" class="custom-select mr-sm-2" name="startMonth">
                        <option selected>start month</option>
                        <option value="1">January</option>
                        <option value="2">February</option>
                        <option value="3">March</option>
                        <option value="4">April</option>
                        <option value="5">May</option>
                        <option value="6">June</option>
                        <option value="7">July</option>
                        <option value="8">August</option>
                        <option value="9">September</option>
                        <option value="10">October</option>
                        <option value="11">November</option>
                        <option value="12">December</option>
                    </select>
                    <select style="color: aliceblue; background-color: transparent" class="custom-select mr-sm-2" name="startYear">
                        <option selected>start year</option>
                        <option value="2018">2018</option>
                        <option value="2019">2019</option>
                        <option value="2020">2020</option>
                        <option value="2021">2021</option>
                        <option value="2022">2022</option>
                    </select>
                    </div>
                </div>
                <br>
                <div style="width: 150px; color: aliceblue; background-color: transparent">
                    <div class="form-inline">
                    <select style="color: aliceblue; background-color: transparent" class="custom-select mr-sm-2" name="finishDay">
                        <option selected>finish day</option>
                        <option value="1">1</option>
                        <option value="2">2</option>
                        <option value="3">3</option>
                        <option value="4">4</option>
                        <option value="5">5</option>
                        <option value="6">6</option>
                        <option value="7">7</option>
                        <option value="8">8</option>
                        <option value="9">9</option>
                        <option value="10">10</option>
                        <option value="11">11</option>
                        <option value="12">12</option>
                        <option value="13">13</option>
                        <option value="14">14</option>
                        <option value="15">15</option>
                        <option value="16">16</option>
                        <option value="17">17</option>
                        <option value="18">18</option>
                        <option value="19">19</option>
                        <option value="20">20</option>
                        <option value="21">21</option>
                        <option value="22">22</option>
                        <option value="23">23</option>
                        <option value="24">24</option>
                        <option value="25">25</option>
                        <option value="26">26</option>
                        <option value="27">27</option>
                        <option value="28">28</option>
                        <option value="29">29</option>
                        <option value="30">30</option>
                        <option value="31">31</option>
                    </select>
                    <select style="color: aliceblue; background-color: transparent" class="custom-select mr-sm-2" name="finishMonth">
                        <option selected>finish month</option>
                        <option value="1">January</option>
                        <option value="2">February</option>
                        <option value="3">March</option>
                        <option value="4">April</option>
                        <option value="5">May</option>
                        <option value="6">June</option>
                        <option value="7">Jule</option>
                        <option value="8">August</option>
                        <option value="9">September</option>
                        <option value="10">October</option>
                        <option value="11">November</option>
                        <option value="12">December</option>
                    </select>
                    <select style="color: aliceblue; background-color: transparent" class="custom-select mr-sm-2" name="finishYear">
                        <option selected>finish year</option>
                        <option value="2018">2018</option>
                        <option value="2019">2019</option>
                        <option value="2020">2020</option>
                        <option value="2021">2021</option>
                        <option value="2022">2022</option>
                    </select>
                    </div>
                </div>
                <br>
                <button type="submit" class="btn btn-secondary"> Enter </button>
                </div>
            </form>

            <%--<input type="text" class="form-control" id="example">--%>
            <%--<script>--%>
                <%--$(function () {--%>
                    <%--$('#example').datetimepicker();--%>
                <%--});--%>
            <%--</script>--%>
        </div>

        <br>

    <div class="row">
        <table class="table table-borderless" style="margin: 0 auto; color: aliceblue">
                <tr>
                    <th>Order number</th>
                    <th>First name</th>
                    <th>Last name</th>
                    <th>Payment status</th>
                    <th>Payment method</th>
                    <th>Delivery method</th>
                    <th>Order status</th>
                    <th>Date of order</th>
                </tr>
                <c:forEach items="${orders}" var="order">
                <tr>
                    <c:set var = "client" scope = "session" value = "${order.client}"/>
                    <td>${order.id}</td>
                    <td>${client.firstName}</td>
                    <td>${client.lastName}</td>
                    <td>${order.paymentStatus}</td>
                    <td>${order.paymentMethod}</td>
                    <td>${order.deliveryMethod}</td>
                    <td>${order.orderStatus}</td>
                    <td>${order.dateOfOrder}</td>
                </tr>
                </c:forEach>
        </table>
    </div>

    <br>

    <div>
        <ul>
            <li> Total sum of all orders: ${totalSumOfAllOrders}</li>
            <li> Total amount of orders: ${totalAmountOfOrders}</li>
            <li> Top item: </li>
            <li> Top client: </li>
        </ul>
    </div>

    </div>

<%--<p><a href="/orders">View orders</a></p>--%>
<%--<p><a href="/statistics">Statistics</a></p>--%>
<%--<p><a href="/viewproducts">View products</a></p>--%>
<%--<p><a href="/viewclients">View clients</a></p>--%>

    <%--<div class="row">--%>
        <%--<h3 style="margin: 0 auto">Add new product</h3>--%>
        <%--<form method="post" action="/addproducts" style="width: 35%; margin: 0 auto">--%>
        <%--<b style="color:aliceblue"> Enter product properties </b>--%>
        <%--<div class="form-group" style="padding-top: 30px">--%>
            <%--<label style="color: aliceblue" for="productName"> Product name </label>--%>
            <%--<input style="background: transparent; color: aliceblue" class="form-control form-control-md" name="productName" type="text" class="form-control" id="productName" aria-describedby="emailHelp" placeholder="">--%>
        <%--</div>--%>
        <%--<div class="form-group" style="padding-top: 30px">--%>
            <%--<label style="color: aliceblue" for="productPrice"> Product price </label>--%>
            <%--<input style="background: transparent; color: aliceblue" class="form-control form-control-md" name="productPrice" type="number" class="form-control" id="productPrice" aria-describedby="emailHelp" placeholder="">--%>
        <%--</div>--%>
        <%--<div>--%>
            <%--<select class="custom-select mr-sm-2" name="category" id="category" style="color: aliceblue; background: transparent; padding-top: 30px">--%>
                <%--<option selected > Choose... </option>--%>
                <%--<option value="PHONE"> PHONE </option>--%>
                <%--<option value="TV_VIDEO"> TV_VIDEO </option>--%>
                <%--<option value="AUDIO"> AUDIO </option>--%>
                <%--<option value="LAPTOP"> LAPTOP </option>--%>
                <%--<option value="TABLET"> TABLET </option>--%>
            <%--</select>--%>
        <%--</div>--%>
        <%--<div class="form-group" style="padding-top: 30px">--%>
            <%--<label style="color: aliceblue" for="productPrice">Amount</label>--%>
            <%--<input style="background: transparent; color: aliceblue" class="form-control form-control-md" name="amount" type="number" class="form-control" id="amount" aria-describedby="emailHelp" placeholder="">--%>
        <%--</div>--%>
        <%--<p><b style="color:aliceblue"> Enter product parameteres: </b></p>--%>
        <%--<div class="form-group" style="padding-top: 30px">--%>
            <%--<label style="color: aliceblue" for="productPrice"> Colour </label>--%>
            <%--<input style="background: transparent; color: aliceblue" class="form-control form-control-md" name="colour" type="text" class="form-control" id="colour" aria-describedby="emailHelp" placeholder="">--%>
        <%--</div>--%>
        <%--<div class="form-group" style="padding-top: 30px">--%>
            <%--<label style="color: aliceblue" for="productPrice"> Brand </label>--%>
            <%--<input style="background: transparent; color: aliceblue" class="form-control form-control-md" name="brand" type="text" class="form-control" id="brand" aria-describedby="emailHelp" placeholder="">--%>
        <%--</div>--%>
        <%--<button type="submit" class="btn btn-secondary"> Add product </button>--%>
        <%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
    <%--</form>--%>
    <%--</div>--%>

<%--<div>--%>
    <%--<h3>List of products</h3>--%>
    <%--<table class="table table-borderless table-hover" style="width: 65%; margin: 0 auto; color: aliceblue">--%>
        <%--<tr>--%>
            <%--<th>Product ID</th>--%>
            <%--<th>Product name</th>--%>
            <%--<th>Price</th>--%>
            <%--<th>Amount</th>--%>
            <%--<th>Category</th>--%>
            <%--<th>Colour</th>--%>
            <%--<th>Brand</th>--%>
        <%--</tr>--%>

        <%--<c:forEach items="${products}" var="product">--%>
        <%--<tr>--%>
            <%--<td>${product.id}</td>--%>
            <%--<td>${product.productName}</td>--%>
            <%--<td>${product.productPrice}</td>--%>
            <%--<td>${product.amount}</td>--%>
            <%--<td>${product.category}</td>--%>
            <%--<c:set var = "parameters" scope = "session" value = "${product.productParameteres}"/>--%>
            <%--<td>${parameters.colour}</td>--%>
            <%--<td>${parameters.brand}</td>--%>
        <%--</tr>--%>
        <%--</c:forEach>--%>
    <%--</table>--%>
<%--</div>--%>

    <%--<div class="row">--%>
    <%--<h3>List of products</h3>--%>
    <%--<table class="table table-borderless table-hover" style="width: 65%; margin: 0 auto; color: aliceblue">--%>
        <%--<tr>--%>
            <%--<th>Product ID</th>--%>
            <%--<th>Product name</th>--%>
            <%--<th>Brand</th>--%>
            <%--<th>Price</th>--%>
            <%--<th>Amount</th>--%>
            <%--<th>Category</th>--%>
            <%--<th>Colour</th>--%>
        <%--</tr>--%>
        <%--<c:forEach items="${products}" var="product">--%>
            <%--<tr>--%>
                <%--<c:set var = "parameters" scope = "session" value = "${product.productParameteres}"/>--%>
                <%--<td>${product.id}</td>--%>
                <%--<td>${product.productName}</td>--%>
                <%--<td>${parameters.brand}</td>--%>
                <%--<td>${product.productPrice}</td>--%>
                <%--<td>${product.amount}</td>--%>
                <%--<td>${product.category}</td>--%>
                <%--<td>${parameters.colour}</td>--%>
            <%--</tr>--%>
        <%--</c:forEach>--%>
    <%--</table>--%>
<%--</div>--%>

    <%--<div class="row">--%>
    <%--<h3>List of clients</h3>--%>
    <%--<table class="table table-borderless table-hover" style="width: 65%; margin: 0 auto; color: aliceblue">--%>
        <%--<tr>--%>
            <%--<th>ID</th>--%>
            <%--<th>First name</th>--%>
            <%--<th>Last name</th>--%>
            <%--<th>Date of birth</th>--%>
            <%--<th>Email</th>--%>
            <%--<th>Password</th>--%>
            <%--<th>Country</th>--%>
            <%--<th>City</th>--%>
            <%--<th>Post code</th>--%>
            <%--<th>Street</th>--%>
            <%--<th>Hose number</th>--%>
            <%--<th>Flat number</th>--%>
        <%--</tr>--%>
    <%--<c:forEach items="${clients}" var="client">--%>
        <%--<tr>--%>
            <%--<td>${client.id}</td>--%>
            <%--<td>${client.firstName}</td>--%>
            <%--<td>${client.lastName}</td>--%>
            <%--<td>${client.birthDate}</td>--%>
            <%--<td>${client.email}</td>--%>
            <%--<td>${client.password}</td>--%>
            <%--<c:set var = "address" scope = "session" value = "${client.address}"/>--%>
            <%--<td>${address.country}</td>--%>
            <%--<td>${address.city}</td>--%>
            <%--<td>${address.postCode}</td>--%>
            <%--<td>${address.street}</td>--%>
            <%--<td>${address.houseNumber}</td>--%>
            <%--<td>${address.flatNumber}</td>--%>
        <%--</tr>--%>
    <%--</c:forEach>--%>
    <%--</table>--%>
<%--</div>--%>

    <%--<div>--%>
    <%--<h3>List of orders:</h3>--%>
    <%--<table class="table table-borderless table-hover" style="width: 65%">--%>
        <%--<tr>--%>
            <%--<th>First name</th>--%>
            <%--<th>Last name</th>--%>
            <%--<th>Payment Status</th>--%>
            <%--<th>Payment Method</th>--%>
            <%--<th>Delivery Method</th>--%>
            <%--<th>Order Status</th>--%>
        <%--</tr>--%>
        <%--<c:forEach items="${orders}" var="order">--%>
        <%--<tr>--%>
            <%--<c:set var = "client" scope = "session" value = "${order.client}"/>--%>
            <%--<td>${client.firstName}</td>--%>
            <%--<td>${client.lastName}</td>--%>

            <%--<c:set var = "paymentStatus" scope = "session" value = "${order.paymentStatus}"/>--%>
            <%--<td>${paymentStatus}</td>--%>

            <%--<c:set var = "paymentMethod" scope = "session" value = "${order.paymentMethod}"/>--%>
            <%--<td>${paymentMethod}</td>--%>

            <%--<c:set var = "deliveryMethod" scope = "session" value = "${order.deliveryMethod}"/>--%>
            <%--<td>${deliveryMethod}</td>--%>

            <%--<c:set var = "orderStatus" scope = "session" value = "${order.orderStatus}"/>--%>
            <%--<td>${orderStatus}</td>--%>

            <%--<td><a href="/edit">Edit</a> </td>--%>

            <%--<br>--%>
        <%--</tr>--%>
        <%--</c:forEach>--%>

<%--</div>--%>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
