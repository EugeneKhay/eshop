<%--
  Created by IntelliJ IDEA.
  User: evgenijhajmovskij
  Date: 2019-03-03
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<html>
<head>
    <title>New order</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="../resources/static/css/app.css"></head>
</head>
<body>
<h3>Add new order</h3>
<div>
    <!--<form method="post" action="/order">
        <p>Please, enter order properties:</p>
        <p><input type="text" name="client_name" placeholder="Client Name"></p>
        <p><input type="text" name="product_name" placeholder="Product Name"></p>
        <p><input type="text" name="paymentMethod" placeholder="Payment Method"></p>
        <p><input type="text" name="deliveryMethod" placeholder="Delivery Method"></p>
        <p><input type="text" name="paymentStatus" placeholder="Payment Status"></p>
        <p><input type="text" name="orderStatus" placeholder="Order Status"></p>
        <p><button type="submit" value="OK">Add</button></p>
    </form>-->

    <form method="post" action="/order">
        <div class="form-row align-items-center">
            <div class="form-group">
                <label for="client_name">Client name</label>
                <input class="form-control form-control-lg" type="text" name="client_name" class="form-control" id="client_name" aria-describedby="emailHelp" placeholder="Name">
            </div>
            <div class="form-group">
                <label for="productName">Product name</label>
                <input class="form-control form-control-lg" type="text" name="product_name" class="form-control" id="productName" aria-describedby="emailHelp" placeholder="Name">
            </div>
            <div class="col-auto my-1">
                <label class="mr-sm-2" for="paymentMethod">Payment Method</label>
                <select class="custom-select mr-sm-2" name="paymentMethod" id="paymentMethod">
                    <option selected>Choose...</option>
                    <option value="CARD">CARD</option>
                    <option value="CASH">CASH</option>
                </select>
            </div>
            <div class="col-auto my-1">
                <label class="mr-sm-2" for="deliveryMethod">Delivery method</label>
                <select class="custom-select mr-sm-2" name="deliveryMethod" id="deliveryMethod">
                    <option selected>Choose...</option>
                    <option value="COURIER">COURIER</option>
                    <option value="SELF">SELF</option>
                </select>
            </div>
            <div class="col-auto my-1">
                <label class="mr-sm-2" for="paymentStatus">Payment status</label>
                <select class="custom-select mr-sm-2" name="paymentStatus" id="paymentStatus">
                    <option selected>Choose...</option>
                    <option value="PAID">PAID</option>
                    <option value="NOT_PAID">NOT_PAID</option>
                </select>
            </div>
            <div class="col-auto my-1">
                <label class="mr-sm-2" for="orderStatus">Status</label>
                <select class="custom-select mr-sm-2" name="orderStatus" id="orderStatus">
                    <option selected>Choose...</option>
                    <option value="WAITING_FOR_PAYMENT">WAITING_FOR_PAYMENT</option>
                    <option value="WAITING_FOR_SHIPPMENT">WAITING_FOR_SHIPPMENT</option>
                    <option value="SHIPPED">SHIPPED</option>
                    <option value="DELIVERIED">DELIVERIED</option>
                </select>
            </div>
            <div class="col-auto my-1">
                <button type="submit" class="btn btn-primary">Submit</button>
            </div>
        </div>
    </form>




</div>

<a href="/orders">List of orders</a>

</div>
</body>
</html>
