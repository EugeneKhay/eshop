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
    <link rel="stylesheet" href="../resources/static/css/app.css">
</head>
<body>

<div class="container">

    <div class="row">
        <h3 style="color: aliceblue">List of orders:</h3>
    </div>

    <div class="row">
        <nav aria-label="breadcrumb">
            <ol class="breadcrumb">
                <li class="breadcrumb-item"><a href="/"> Home </a></li>
                <li class="breadcrumb-item"><a href="/admin"> Admin </a> </li>
                <li class="breadcrumb-item active" aria-current="page"> View orders </li>
            </ol>
        </nav>
    </div>

    <div class="row">
        <table class="table table-borderless table-hover" style="margin: 0 auto; color: aliceblue">
        <tr>
            <th>Order number</th>
            <th>Client</th>
            <th>Products</th>
            <th>Sum</th>
            <th>Payment status</th>
            <th>Payment method</th>
            <th>Delivery method</th>
            <th>Order status</th>
            <th>Date of order</th>
        </tr>
    <c:forEach items="${orders}" var="order">
        <tr>
        <c:set var = "client" scope = "session" value = "${order.client}"/>
        <c:set var = "products" scope = "session" value = "${order.productsInOrder}"/>
            <td>${order.id}</td>
            <td>${client.firstName} ${client.lastName}</td>
            <td>
                <c:forEach items="${products}" var="product">
                    ${product.productName}
                </c:forEach>
            </td>
            <td>${order.sumOfOrder}</td>
            <td>${order.paymentStatus}</td>
            <td>${order.paymentMethod}</td>
            <td>${order.deliveryMethod}</td>
            <td>${order.orderStatus}</td>
            <td>${order.dateOfOrder}</td>
        </tr>
    </c:forEach>
        </table>
    </div>

    <div class="col-sm-1">
        <button type="button" class="btn btn-secondary btn-md" data-toggle="modal" data-target="#exampleModal"> Edit </button>
    </div>

    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
        <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel"> Edit data </h5>
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
                    <%--<input type="hidden" name="orderForEdit" value="${order.id}">--%>
                    <button onclick="form_submit()" name="user_search" class="btn btn-secondary" data-dismiss="modal"> Submit </button>
                </form>
                <script type="text/javascript">
                    function form_submit() {
                        document.getElementById("edited_order").submit();
                    }
                </script>
                </div>
            </div>
        </div>
    </div>

</div>

<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
