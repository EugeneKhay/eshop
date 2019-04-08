<!DOCTYPE html>
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
    <link rel="stylesheet" type="text/css" href="../resources/static/css/style.css"/>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>

<div class="container">

    <div class="row">
        <div class="col-sm-8">
            <h3 style="color: aliceblue; margin-top: 10px">Basket</h3>
        </div>
    </div>

    <div class="row" style="margin-top: 30px">
        <div class="col-sm-3">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="/">Home</a></li>
                    <li class="breadcrumb-item"><a href="/phone">Products</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Basket</li>
                </ol>
            </nav>
        </div>
        <div class="col-sm">
        </div>
        <div class="col-sm-2">
            <sec:authorize access="isAuthenticated()">
                <button type="button" class="btn btn-secondary">
                    <a style="color: aliceblue" href="/logout"> Clear </a>
                </button>
            </sec:authorize>
        </div>
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

    <form method="post" action="/confirm" style="margin-top: 20px; color: aliceblue; width: 50%">
        <p>Payment Method</p>
        <div class="form-check form-group" >
            <input class="form-check-input" type="checkbox" name="paymentMethod" value="CARD" id="defaultCheck1">
            <label class="form-check-label" for="defaultCheck1">Card</label>
        </div>
        <div class="form-check form-group">
            <input class="form-check-input" type="checkbox" name="paymentMethod" value="CASH" id="defaultCheck2">
            <label class="form-check-label" for="defaultCheck2">Cash</label>
        </div>
        <p>Delivery method</p>
        <div class="form-check form-group">
            <input class="form-check-input" type="checkbox" name="deliveryMethod" value="COURIER" id="defaultCheck3">
            <label class="form-check-label" for="defaultCheck3">Courier</label>
        </div>
        <div class="form-check form-group">
            <input class="form-check-input" type="checkbox" name="deliveryMethod" value="SELF" id="defaultCheck4">
            <label class="form-check-label" for="defaultCheck4">Self</label>
        </div>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <div class="input-group-append">
            <button class="btn btn-secondary" type="submit">Confirm</button>
        </div>
    </form>
</div>

<br>
<br>
<br>
<br>
<div class="footer" style="color: aliceblue; margin-left: 30px">
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


<script type="text/javascript" src="../resources/static/js/app2.js"/>
<%--<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>--%>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
















