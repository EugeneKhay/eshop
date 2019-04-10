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
    <link rel="stylesheet" type="text/css" href="/resources/static/css/style.css">
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="//ajax.aspnetcdn.com/ajax/jquery.ui/1.10.3/jquery-ui.min.js"></script>
</head>
<body>
<div class="container">

    <div class="row" id="adminTitle">
        <div class="col-sm">
            <h3> Admin page </h3>
        </div>
    </div>

    <!-- Nav -->
    <div class="row" id="nav">
        <div class="col-sm-4">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb" style="background-color: transparent">
                    <li class="breadcrumb-item"><a href="/"> Home </a></li>
                    <li class="breadcrumb-item"><a href="/phone"> Products </a></li>
                    <li class="breadcrumb-item"><a href="/basket"> Basket </a></li>
                    <li class="breadcrumb-item active" aria-current="page"> Admin </li>
                </ol>
            </nav>
        </div>
        <div class="col-sm-7"></div>
        <div class="col-sm-1">
            <sec:authorize access="isAuthenticated()">
                <button type="button" class="btn btn-secondary">
                    <a style="color: aliceblue"href="/logout"> Logout </a>
                </button>
            </sec:authorize>
        </div>
    </div>

    <div class="row" id="stat">
        <div class="col-sm">
            <h5> ${period}</h5>
        </div>
    </div>

    <!-- Buttons -->
    <div class="row">
        <div class="col-sm">
            <div class="btn-toolbar" role="toolbar" aria-label="Toolbar with button groups">
                <div class="btn-group mr-2" role="group" aria-label="First group">
                    <button type="button" class="btn btn-secondary" onclick="orders()"> Orders </button>
                    <button type="button" class="btn btn-secondary" onclick="products()">Products</button>
                    <button type="button" class="btn btn-secondary" onclick="clients()"> Clients</button>
                    <button type="button" class="btn btn-secondary" onclick="stats()"> Statistics</button>
                </div>
                <div class="btn-group mr-2" role="group" aria-label="Second group">
                    <button type="button" class="btn btn-secondary btn-md" data-toggle="modal" data-target="#exampleModal">Change period</button>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal window-->
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

    <div class="row" id="info">

        <!-- Statistics -->
        <div class="col-sm-10" id="stats" style="display: none; color: black">
            <h3>Statistics</h3>
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
        </div>

        <!-- Orders -->
        <div id="orders" style="display: none">
            <h3 style="color: black">Orders</h3>
            <div class="row">
                <%--<table class="table table-borderless" style="color: black">--%>
                    <%--<tr>--%>
                        <%--<th>Order number</th>--%>
                        <%--<th>Client</th>--%>
                        <%--<th>Products</th>--%>
                        <%--<th>Sum</th>--%>
                        <%--<th>Date of order</th>--%>
                        <%--<th>Payment method</th>--%>
                        <%--<th>Delivery method</th>--%>
                        <%--<th>Order status</th>--%>
                        <%--<th>Payment status</th>--%>
                    <%--</tr>--%>
                    <%--<c:forEach items="${orders}" var="order">--%>
                        <%--<tr>--%>
                            <%--<c:set var = "client" scope = "session" value = "${order.client}"/>--%>
                            <%--<td>${order.id}</td>--%>
                            <%--<td>${client.firstName} ${client.lastName}</td>--%>
                            <%--<td>--%>
                                <%--<c:forEach items="${order.orderProducts}" var="order_product">--%>
                                    <%--<c:set var = "product" scope = "session" value = "${order_product.product}"/>--%>
                                    <%--<table>--%>
                                        <%--<tr>${product.productName} - ${order_product.amount}</tr>--%>
                                    <%--</table>--%>
                                <%--</c:forEach>--%>
                            <%--</td>--%>
                            <%--<td>${order.sumOfOrder}</td>--%>
                            <%--<td>${order.dateOfOrder}</td>--%>
                            <%--<td>${order.paymentMethod}</td>--%>
                            <%--<td>${order.deliveryMethod}</td>--%>
                            <%--<td>${order.orderStatus}</td>--%>
                            <%--<td>${order.paymentStatus}</td>--%>
                        <%--</tr>--%>
                    <%--</c:forEach>--%>
                <%--</table>--%>

                    <table class="table table-borderless" style="color: black">
                        <tr>
                            <th>№</th>
                            <th>Client</th>
                            <th>Products</th>
                            <th>Price</th>
                            <th>Pcs.</th>
                            <th>Total sum</th>
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
                                    <c:forEach items="${order.orderProducts}" var="order_product">
                                        <c:set var = "product" scope = "session" value = "${order_product.product}"/>
                                        <table>
                                            <tr>${product.productName}</tr>
                                        </table>
                                    </c:forEach>
                                </td>
                                <td>
                                    <c:forEach items="${order.orderProducts}" var="order_product">
                                        <c:set var = "product" scope = "session" value = "${order_product.product}"/>
                                        <table>
                                            <tr>${product.productPrice}</tr>
                                        </table>
                                    </c:forEach>
                                </td>
                                <td>
                                    <c:forEach items="${order.orderProducts}" var="order_product">
                                        <c:set var = "product" scope = "session" value = "${order_product.product}"/>
                                        <table>
                                            <tr>${order_product.amount}</tr>
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

            <!-- Modal window 2-->
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
                                <button onclick="edit_order_submit()" type="submit" class="btn btn-secondary" data-dismiss="modal"> Submit </button>
                            </form>
                            <script type="text/javascript">
                                function edit_order_submit() {
                                    document.getElementById("edited_order").submit();
                                }
                            </script>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <!--Products -->
        <div id="products" style="display: none; width: 90%">
            <h5 style="color: black">List of products</h5>
            <table class="table table-borderless" style="withdth: 65%; margin: 0 auto; color: black">
                        <tr>
                            <th>Product ID</th>
                            <th>Product name</th>
                            <th>Brand</th>
                            <th>Price</th>
                            <th>Amount</th>
                            <th>Category</th>
                            <th>Colour</th>
                            <th></th>
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
                            <td>
                                <div class="col-sm-1">
                                    <button type="button" class="btn btn-secondary btn-md" data-toggle="modal" data-target="#editOrder"> Edit </button>
                                </div>

                                <!-- Modal window 3-->
                                <div class="modal fade" id="editOrder" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                                <div class="modal-dialog" role="document">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="exampleModalLabel"> Edit product </h5>
                                                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                <span aria-hidden="true">&times;</span>
                                                            </button>
                                                        </div>
                                                        <div class="modal-body">
                                                            <form action="/editproduct" method="post" id="edited_data_order">
                                                                <input type="hidden" name="productForEdit" value="${product.id}">
                                                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                                                <p>
                                                                    <label for="name" style="color: black">Product name</label>
                                                                    <input type="text" id="name" name="name" value="${product.productName}"/>
                                                                </p>
                                                                <p>
                                                                    <label for="brand2" style="color: black">Brand</label>
                                                                    <input type="text" id="brand2" name="brand" value="${parameters.brand}"/>
                                                                </p>
                                                                <p>
                                                                    <label for="price" style="color: black">Price</label>
                                                                    <input type="number" id="price" name="price" value="${product.productPrice}"/>
                                                                </p>
                                                                <p>
                                                                    <label for="amount2" style="color: black">Amount</label>
                                                                    <input type="number" id="amount2" name="amount" value="${product.amount}"/>
                                                                </p>
                                                                <p>
                                                                    <label for="category2" style="color: black">Category</label>
                                                                    <input type="text" id="category2" name="category" value="${product.category}"/>
                                                                </p>
                                                                <p>
                                                                    <label for="color" style="color: black">Color</label>
                                                                    <input type="text" id="color" name="color" value="${parameters.colour}"/>
                                                                </p>
                                                                <button onclick="form2_submit()" name="user_search" class="btn btn-secondary" data-dismiss="modal"> Submit </button>
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                            </td>
                        </tr>
                    </c:forEach>
                </table>

                <h5 style="color:black; margin-top: 20px">Add new product</h5>
                    <form method="post" action="/addproducts">
                        <b style="color:black"> Enter product properties </b>
                        <div class="form-group">
                            <label for="productName"> Product name </label>
                            <input class="form-control form-control-md" name="productName" type="text" class="form-control" id="productName" aria-describedby="emailHelp" required>
                        </div>
                        <div class="form-group">
                            <label  for="productPrice"> Product price </label>
                            <input  class="form-control form-control-md" name="productPrice" type="number" class="form-control" id="productPrice" aria-describedby="emailHelp" required>
                        </div>
                        <div>
                            <label for="productPrice"> Product type </label>
                            <select class="custom-select mr-sm-2" name="category" id="category" required>
                                <option selected>Choose category</option>
                                <option value="PHONE"> PHONE </option>
                                <option value="TV_VIDEO"> TV_VIDEO </option>
                                <option value="AUDIO"> AUDIO </option>
                                <option value="LAPTOP"> LAPTOP </option>
                                <option value="TABLET"> TABLET </option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label for="productPrice">Amount</label>
                            <input class="form-control form-control-md" name="amount" type="number" class="form-control" id="amount" aria-describedby="emailHelp" required>
                        </div>
                        <b style="color:black"> Enter product parameters </b>
                        <div class="form-group">
                            <label for="productPrice"> Colour </label>
                            <input class="form-control form-control-md" name="colour" type="text" class="form-control" id="colour" aria-describedby="emailHelp" required>
                        </div>
                        <div class="form-group">
                            <label for="productPrice"> Brand </label>
                            <input class="form-control form-control-md" name="brand" type="text" class="form-control" id="brand" aria-describedby="emailHelp" required>
                        </div>
                        <button type="submit" class="btn btn-secondary"> Add product </button>
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                    </form>
            </div>

        <!-- Clients -->
        <div id="clients" style="display: none">
            <h3 style="color: black">List of clients</h3>
            <table class="table table-borderless" style="width: 100%; margin: 0 auto; color: black">
                <tr>
                            <th>ID</th>
                            <th>First name</th>
                            <th>Last name</th>
                            <th>Date of birth</th>
                            <th>Email</th>
                            <th>Password</th>
                            <th>Address</th>
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
                            </tr>
                        </c:forEach>
            </table>
        </div>
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
<div class="footer">
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
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>




<%--<div class="col-sm-2">--%>
<%--<div class="btn-group-vertical" role="group" aria-label="Basic example">--%>
<%--<button type="button" class="btn btn-secondary" onclick="orders()"> Orders </button>--%>
<%--<button type="button" class="btn btn-secondary" onclick="products()">Products</button>--%>
<%--<button type="button" class="btn btn-secondary" onclick="clients()"> Clients</button>--%>
<%--<button type="button" class="btn btn-secondary" onclick="stats()"> Statistics</button>--%>
<%--</div>--%>
<%--</div>--%>

<%--<div class="col-sm-1">--%>
<%--<button type="button" class="btn btn-secondary btn-md" data-toggle="modal" data-target="#exampleModal">Change period</button>--%>
<%--</div>--%>

















