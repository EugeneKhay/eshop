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
    <title>Basket</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="../resources/static/css/style.css">
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>

<div class="container">

    <div class="row">
        <div class="col-sm-8">
            <h3 style="color: aliceblue; margin-top: 10px">Basket</h3>
        </div>
        <div class="col-sm-4">
            <sec:authorize access="isAuthenticated()">
                <b style="color: aliceblue"> <a style="color: aliceblue" href="/personal"> <sec:authentication property="principal.username" /> </a> </b>
                <a style="color: aliceblue; padding-left: 20px" href="/logout"> Clear all </a>
            </sec:authorize>
        </div>
    </div>

    <div class="row">
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="/">Home</a></li>
                <li class="breadcrumb-item"><a href="/phone">Products</a></li>
                <li class="breadcrumb-item active" aria-current="page">Basket</li>
            </ol>
        </nav>
    </div>


    <div id="basketInfo">
        <div class="row">
            <table class="table" style="width: 65%; color: aliceblue">
                <tr>
                    <th>Product ID</th>
                    <th>Product name</th>
                    <th>Price</th>
                    <th>Amount</th>
                    <th>Category</th>
                    <th>Colour</th>
                    <th>Brand</th>
                </tr>

                <c:forEach items="${items}" var="entry">
                    <c:set var = "product" scope = "session" value = "${entry.key}"/>
                    <c:set var = "amount" scope = "session" value = "${entry.value}"/>
                    <div >
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
        <div class="row" style="color: aliceblue">
            <h5> Total price: </h5>
            <h5 id="totalPrice" style="padding-left: 35px"> ${sessionScope.totalPrice} </h5>
        </div>
    </div>

    <form method="post" action="/confirm" style="width: 40%; color: aliceblue; margin-top: 15px">
        <p class="form-row align-items-center">
            <div class="col-auto my-1">
                <select class="custom-select mr-sm-2" name="paymentMethod" style="background-color: transparent">
                    <option selected>Payment Method</option>
                    <option value="CARD">Card</option>
                    <option value="CASH">Cash</option>
                </select>
            </div>
            <div class="col-auto my-1">
                <select class="custom-select mr-sm-2" name="deliveryMethod" style="background-color: transparent">
                    <option selected>Delivery method</option>
                    <option value="COURIER">Courier</option>
                    <option value="SELF">Self</option>
                </select>
            </div>
            <div class="col-auto my-1">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <button type="submit" class="btn btn-secondary">Confirm</button>
            </div>
    </form>
</div>

<%--<footer>--%>
    <%--<p> Copyright ...</p>--%>
    <%--<p> Our contacts ...</p>--%>
    <%--<p> Support... </p>--%>
<%--</footer>--%>


<script type="text/javascript" src="../resources/static/js/app2.js"/>
<%--<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>--%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
















<%--<script type="text/javascript">--%>
<%--function deleteItem(id) {--%>
<%--$.ajax({--%>
<%--url : '/delete',--%>
<%--type: 'GET',--%>
<%--dataType: 'json',--%>
<%--data : ({--%>
<%--delete: id--%>
<%--}),--%>
<%--success: function (result) {--%>
<%--$('#totalPrice').text(result);--%>
<%--$('#product-'+ id + 'del').css({"display" : "none"});--%>
<%--&lt;%&ndash;$('.product${product.id}').css({"display" : "none"});&ndash;%&gt;--%>
<%--// $('.prod-name[prod-id="'+ id +'"]').text(name)--%>
<%--}--%>
<%--});--%>
<%--}--%>
<%--</script>--%>
<%--<script type="text/javascript">--%>
<%--function increase(id) {--%>
<%--$.ajax({--%>
<%--url : '/editOrderPlus',--%>
<%--type: 'GET',--%>
<%--dataType: 'json',--%>
<%--data : ({--%>
<%--editOrderPlus: id--%>
<%--}),--%>
<%--success: function (result) {--%>
<%--$('#totalPrice').text(result[0]);--%>
<%--$('#amount'+id+'edit').text(result[1]);--%>
<%--}--%>
<%--});--%>
<%--}--%>
<%--</script>--%>
<%--<script type="text/javascript">--%>
<%--function decrease(id) {--%>
<%--$.ajax({--%>
<%--url : '/editOrderMinus',--%>
<%--type: 'GET',--%>
<%--dataType: 'json',--%>
<%--data : ({--%>
<%--editOrderMinus: id--%>
<%--}),--%>
<%--success: function (result) {--%>
<%--$('#totalPrice').text(result[0]);--%>
<%--$('#amount'+id+'edit').text(result[1]);--%>
<%--}--%>
<%--});--%>
<%--}--%>
<%--</script>--%>
