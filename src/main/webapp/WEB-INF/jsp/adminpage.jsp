<!DOCTYPE html>
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
    <script src="<c:url value="/resources/static/js/app2.js"/>"></script>
</head>
<body>
<div class="wrapper">
    <div class="wrapperForFooter">
        <div class="header">
            <!-- Title -->
            <div class="row" id="adminTitle">
                <div class="col-sm-2">
                    <h3> Admin page </h3>
                </div>

                <!-- Nav -->
                <div class="col-sm-4">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb" style="background-color: transparent">
                            <li class="breadcrumb-item"><a href="/"> Home </a></li>
                            <li class="breadcrumb-item active" aria-current="page"> Admin </li>
                        </ol>
                    </nav>
                </div>
                <div class="col-sm"></div>
                <div class="col-sm-1">
                    <sec:authorize access="isAuthenticated()">
                        <button type="button" class="btn btn-secondary">
                            <a style="color: aliceblue"href="/logout"> Logout </a>
                        </button>
                    </sec:authorize>
                </div>
            </div>

            <!-- Nav -->
            <div class="row">
                <div class="col-sm-11"></div>

            </div>
        </div>

        <div class="container content">

            <!-- Period -->
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
                            <button type="button" class="btn btn-secondary" onclick="products()"> Catalog </button>
                            <button type="button" class="btn btn-secondary" onclick="clients()"> Clients</button>
                            <button type="button" class="btn btn-secondary" onclick="stats()"> Statistics</button>
                            <button type="button" class="btn btn-secondary" onclick="shops()"> Shops </button>
                        </div>
                        <div class="btn-group mr-2" role="group" aria-label="Second group">
                            <button type="button" class="btn btn-secondary btn-md" data-toggle="modal" data-target="#exampleModal"> Change period </button>
                        </div>
                        <div class="btn-group mr-2" role="group" aria-label="Third group">
                            <button type="button" class="btn btn-secondary btn-md" data-toggle="modal" data-target="#editModal"> Process order </button>
                        </div>
                    </div>
                </div>
            </div>

            <!-- Modal window change period-->
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

            <!-- Modal window add shop -->
            <div class="modal fade" id="addShop" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel2" aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 style="color: black" class="modal-title" id="exampleModalLabel3"> Enter shop data: </h5>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form action="/addshop" method="post" id="add_shop">
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
                            <label for="phone" style="color: black">Phone number</label>
                            <input class="form-control form-control-md" type="text" id="phone" name="phone" />
                        </div>
                        <button onclick="form7_submit()" name="user_search" class="btn btn-secondary" data-dismiss="modal"> Submit </button>
                        <script>
                            function form7_submit() {
                                document.getElementById("add_shop").submit();
                            };
                        </script>
                    </form>
                </div>
            </div>
        </div>
    </div>

            <div class="row" id="info">
                <!-- Statistics -->
                <div class="col-sm-10" id="stats" style="display: none; color: black">
            <h3>Statistics</h3>
                <ul>
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

                    <br>

                    <li> Top clients:
                        <ol>
                            <c:forEach items="${output}" var="data">
                                <li> ${data}</li>
                            </c:forEach>
                        </ol>
                    </li>
                </ul>
        </div>

                <!-- Orders -->
                <div id="orders" style="display: none">
                    <div class="cardparent">
                        <div class="cardchild">
                    <h3 style="color: black">Orders</h3>
                    <div class="row">
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
                                    <th>Delivery address</th>
                                    <th>Pickup address</th>
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
                                        <td>${order.addressForDelivery}</td>
                                        <td>${order.addressForSelfCollect}</td>
                                        <td>${order.orderStatus}</td>
                                        <td>${order.paymentStatus}</td>
                                    </tr>
                                </c:forEach>
                            </table>
                    </div>


                    <!-- Modal process order-->
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
                                        <div class="form-group">
                                            <label for="orderForEdit"> Order number </label>
                                            <input type="number" id="orderForEdit" name="orderForEdit"/>
                                        </div>
                                        <p>
                                            <label for="paymentStatus"> Payment status </label>
                                            <select class="custom-select mr-sm-2" name="paymentStatus" id="paymentStatus" style="width: auto">
                                                <option selected>NOT_PAID</option>
                                                <option >PAID</option>
                                            </select>
                                        </p>
                                        <p>
                                            <label for="orderStatus"> Order status </label>
                                            <select class="custom-select mr-sm-2" name="orderStatus" id="orderStatus" style="width: auto">
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
                </div>
                </div>

                <style>
                    .cardparent {
                        width: 1200px;
                        height:800px;
                        overflow: hidden;
                        overflow-x: scroll;
                        white-space:nowrap;
                    }
                    .cardchild {
                        display: inline-block;
                        vertical-align: top;
                        width: 15rem;
                        height:800px;
                    }
                </style>

                <!--Products -->
                <div id="products" style="display: none">
                    <div class="container">
                        <div class="row">
                            <div class="col-sm-10">
                                <h5 style="color: black">List of products</h5>
                                <table class="table table-borderless" style="withdth: 90%; margin: 0 auto; color: black">
                                    <tr>
                                        <th>Product name</th>
                                        <th>Brand</th>
                                        <th>Price</th>
                                        <th>Amount</th>
                                        <th>Category</th>
                                        <th>OS</th>
                                        <th>Colour</th>
                                        <th>Weight</th>
                                        <th>
                                            <div class="btn-group mr-2" role="group" aria-label="Second group">
                                                <button type="button" class="btn btn-secondary btn-md" data-toggle="modal" data-target="#addProduct"> Add </button>
                                            </div>
                                        </th>
                                    </tr>
                                    <c:forEach items="${products}" var="product">
                                        <tr>
                                            <c:set var = "parameters" scope = "session" value = "${product.productParameteres}"/>
                                            <td>${product.productName}</td>
                                            <td>${parameters.brand}</td>
                                            <td>${product.productPrice}</td>
                                            <td>${product.amount}</td>
                                            <td>${product.productCategory}</td>
                                            <td>${parameters.operatingSystem}</td>
                                            <td>${parameters.colour}</td>
                                            <td>${parameters.weight}</td>
                                            <td>
                                                <div>
                                                    <button type="button" class="btn btn-secondary" onclick="changeProduct2(${product.id})"> Change </button>
                                                </div>
                                                <script type="text/javascript">
                                                    function changeProduct2(id) {
                                                        $("#" + id + "").toggle();
                                                    }
                                                </script>
                                                <form action="/editproduct" method="post" id="${product.id}" style="display: none">
                                                    <input type="hidden" name="productForEdit" value="${product.id}">
                                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                                    <div class="form-group">
                                                        <label for="name" style="color: black">Product name</label>
                                                        <input class="form-control form-control-md" type="text" id="name" name="name" value="${product.productName}"/>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="brand2" style="color: black">Brand</label>
                                                        <input class="form-control form-control-md" type="text" id="brand2" name="brand" value="${parameters.brand}"/>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="price" style="color: black">Price</label>
                                                        <input class="form-control form-control-md" type="number" id="price" name="price" value="${product.productPrice}"/>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="amount2" style="color: black">Amount</label>
                                                        <input class="form-control form-control-md" type="number" id="amount2" name="amount" value="${product.amount}"/>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="category2" style="color: black">Category</label>
                                                        <input class="form-control form-control-md" type="text" id="category2" name="category" value="${product.productCategory}"/>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="color" style="color: black">Color</label>
                                                        <input class="form-control form-control-md" type="text" id="color" name="color" value="${parameters.colour}"/>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="weight2" style="color: black">Weight</label>
                                                        <input class="form-control form-control-md" type="number" id="weight2" name="weight" value="${parameters.weight}"/>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="operatingSystem" style="color: black"> Operating system </label>
                                                        <input class="form-control form-control-md" type="text" id="operatingSystem" name="operatingSystem" value="${parameters.operatingSystem}"/>
                                                    </div>
                                                    <button onclick="form2_submit()" name="user_search" class="btn btn-secondary" data-dismiss="modal"> Submit </button>
                                                </form>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </table>

                                <!-- Modal window add product -->
                                <div class="modal fade" id="addProduct" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel9" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 style="color: black" class="modal-title" id="exampleModalLabel"> Enter product properties </h5>
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">
                                                <form method="post" action="/addproducts">
                                                    <strong style="color:black"> Enter product properties </strong>
                                                    <div class="form-group">
                                                        <label for="productName"> Product name </label>
                                                        <input class="form-control form-control-md" name="productName" type="text" class="form-control" id="productName" aria-describedby="emailHelp" required>
                                                    </div>
                                                    <div class="form-group">
                                                        <label  for="productPrice"> Product price </label>
                                                        <input  class="form-control form-control-md" name="productPrice" type="number" class="form-control" id="productPrice" aria-describedby="emailHelp" required>
                                                    </div>
                                                    <div>
                                                        <label for="category"> Product type </label>
                                                        <select class="custom-select mr-sm-2" name="category" id="category" required>
                                                            <option selected>Choose category</option>
                                                            <c:forEach items="${categories}" var="category">
                                                                <option value="${category}"> ${category} </option>
                                                            </c:forEach>
                                                        </select>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="amount">Amount</label>
                                                        <input class="form-control form-control-md" name="amount" type="number" class="form-control" id="amount" aria-describedby="emailHelp" required>
                                                    </div>
                                                    <strong style="color:black"> Enter product parameters </strong>
                                                    <div class="form-group">
                                                        <label for="colour"> Colour </label>
                                                        <input class="form-control form-control-md" name="colour" type="text" class="form-control" id="colour" aria-describedby="emailHelp" required>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="brand"> Brand </label>
                                                        <input class="form-control form-control-md" name="brand" type="text" class="form-control" id="brand" aria-describedby="emailHelp" required>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="weight"> Weight </label>
                                                        <input class="form-control form-control-md" name="weight" type="number" class="form-control" id="weight" aria-describedby="emailHelp" required>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="os"> OS </label>
                                                        <input class="form-control form-control-md" name="os" type="text" class="form-control" id="os" aria-describedby="emailHelp" required>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="image"> Image </label>
                                                        <input class="form-control form-control-md" name="image" type="text" class="form-control" id="image" aria-describedby="emailHelp" required>
                                                    </div>
                                                    <button type="submit" class="btn btn-secondary"> Add product </button>
                                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>

                            <!-- Categories -->
                            <div class="col-sm-2">
                                <h5 style="color: black">List of categories</h5>
                                <div id="categories" >
                                    <div class="container">
                                        <div class="row">
                                            <div class="col-sm">
                                                <table class="table table-borderless" style="margin: 0 auto; color: black">
                                                    <tr>
                                                        <th> Category name </th>
                                                        <th>
                                                            <div class="btn-group mr-2" role="group" aria-label="Second group">
                                                                <button type="button" class="btn btn-secondary btn-md" data-toggle="modal" data-target="#addCategory"> Add </button>
                                                            </div>
                                                        </th>
                                                    </tr>
                                                    <c:forEach items="${categories}" var="category">
                                                        <tr>
                                                            <td>${category}</td>
                                                            <td></td>
                                                        </tr>
                                                    </c:forEach>
                                                </table>

                                                <!-- Modal window add category -->
                                                <div class="modal fade" id="addCategory" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel9" aria-hidden="true">
                                                    <div class="modal-dialog" role="document">
                                                        <div class="modal-content">
                                                            <div class="modal-header">
                                                                <h5 style="color: black" class="modal-title" id="ex"> Enter category name </h5>
                                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                                    <span aria-hidden="true">&times;</span>
                                                                </button>
                                                            </div>
                                                            <div class="modal-body">
                                                                <form method="post" action="/addnewcategory">
                                                                    <strong style="color:black"> Enter category name </strong>
                                                                    <div class="form-group">
                                                                        <label for="categoryName"> Product name </label>
                                                                        <input class="form-control form-control-md" name="categoryName" type="text" class="form-control" id="categoryName" aria-describedby="emailHelp" required>
                                                                    </div>
                                                                    <button type="submit" class="btn btn-secondary"> Add </button>
                                                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                                                </form>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
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
                            <th>Phone</th>
                            <th>Address</th>
                        </tr>
                        <c:forEach items="${clients}" var="client">
                            <tr>
                                <td>${client.id}</td>
                                <td>${client.firstName}</td>
                                <td>${client.lastName}</td>
                                <td>${client.birthDate}</td>
                                <td>${client.email}</td>
                                <td>${client.phone}</td>
                                <td>
                                    <c:forEach items="${client.addressList}" var="address">
                                        <table>
                                            <tr> ${address} </tr>
                                        </table>
                                    </c:forEach>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>

                <!-- Shops -->
                <div id="shops" style="display: none">
                    <h3 style="color: black"> List of shops </h3>
                    <table class="table table-borderless" style="color: black">
                        <tr>
                            <th>Country</th>
                            <th>City</th>
                            <th>Post code</th>
                            <th>Street</th>
                            <th>House number</th>
                            <th>Phone</th>
                            <th></th>
                            <th>
                                <div class="btn-group mr-2" role="group" aria-label="Third group">
                                    <button type="button" class="btn btn-secondary btn-md" data-toggle="modal" data-target="#addShop"> Add shop </button>
                                </div>
                            </th>
                        </tr>
                        <c:forEach items="${shops}" var="shop">
                            <tr>
                                <td>${shop.country}</td>
                                <td>${shop.city}</td>
                                <td>${shop.postCode}</td>
                                <td>${shop.street}</td>
                                <td>${shop.houseNumber}</td>
                                <td>${shop.phoneNumber}</td>
                                <td></td>
                                <td>
                                    <div>
                                        <div class="btn-toolbar" role="toolbar">
                                            <div class="btn-group" role="group">
                                                <button type="button" class="btn btn-secondary" onclick="editShop(${shop.id})"> Edit </button>
                                            </div>
                                            <div class="btn-group" role="group">
                                            </div>
                                        </div>
                                    </div>

                                    <form action="/deleteshop" method="post" style="display: none" id="${shop.id}del">
                                        <input type="hidden" name="shopForDelete" value="${shop.id}">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                    </form>
                                    <script>
                                        function deleteShop(id) {
                                            $("#" + id + "del").submit();
                                        }
                                        function editShop(id) {
                                            $("#" + id).toggle();
                                        }
                                    </script>

                                    <!-- edit shop -->
                                    <div id="${shop.id}" style="display: none">
                                        <form action="/editshop" method="post" >
                                            <input type="hidden" name="shopForEdit" value="${shop.id}">
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                            <div class="form-group">
                                                <label for="country2" style="color: black">Country</label>
                                                <input class="form-control form-control-md" type="text" id="country2" name="country" value="${shop.country}"/>
                                            </div>
                                            <div class="form-group">
                                                <label for="city2" style="color: black">City</label>
                                                <input class="form-control form-control-md" type="text" id="city2" name="city" value="${shop.city}"/>
                                            </div>
                                            <div class="form-group">
                                                <label for="freakfield" style="color: black">Postcode</label>
                                                <input class="form-control form-control-md" type="number" id="freakfield" name="postcode" value="${shop.postCode}"/>
                                            </div>
                                            <div class="form-group">
                                                <label for="street2" style="color: black">Street</label>
                                                <input class="form-control form-control-md" type="text" id="street2" name="street" value="${shop.street}"/>
                                            </div>
                                            <div class="form-group">
                                                <label for="houseNumber2" style="color: black">House number</label>
                                                <input class="form-control form-control-md" type="number" id="houseNumber2" name="houseNumber" value="${shop.houseNumber}"/>
                                            </div>
                                            <div class="form-group">
                                                <label for="flatNumber" style="color: black"> Phone </label>
                                                <input class="form-control form-control-md" type="text" id="flatNumber" name="phone" value="${shop.phoneNumber}"/>
                                            </div>
                                            <button type="submit" class="btn btn-secondary"> Submit </button>
                                        </form>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>

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
    </div>
</div>
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>






















