<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>My account</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="/resources/static/css/style.css">
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="//ajax.aspnetcdn.com/ajax/jquery.ui/1.10.3/jquery-ui.min.js"></script>
</head>
<body>
<div class="wrapper">

    <div class="wrapperForFooter">

        <div class="header">

            <!-- Title -->
            <div class="row" id="personalTitle">
                <div class="col-sm-2">
                    <h3> My account </h3>
                </div>

                <div class="col-sm-3">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb" style="background-color: transparent">
                            <li class="breadcrumb-item"><a href="/"> Home </a></li>
                            <li class="breadcrumb-item active" aria-current="page"> My account </li>
                        </ol>
                    </nav>
                </div>
                <div class="col-sm"></div>
                <div class="col-sm-1">
                    <sec:authorize access="isAuthenticated()">
                        <button type="button" class="btn btn-secondary">
                            <a style="color: aliceblue" href="/logout">Logout</a>
                        </button>
                    </sec:authorize>
                </div>
            </div>

            <!-- Nav -->
            <div class="row">

                <div class="col-sm-8"></div>

            </div>

        </div>

        <br>

        <div class="container content">

            <!-- Personal data -->
            <div class="row">
            <h4> Personal data</h4>
            <table style="width: 100%; color: black; margin: 0 auto" class="table table-borderless">
            <tr style="background: transparent; color: black">
                <th>First name</th>
                <th>Last name</th>
                <th>Date of birth</th>
                <th>Email</th>
                <th>Phone</th>
                <th>
                        <div class="col-sm-1">
                             <button type="button" class="btn btn-secondary btn-md" data-toggle="modal" data-target="#personal"> Edit </button>
                        </div>
                        <!-- Modal window edit personal -->
                    <div class="modal fade" id="personal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel"> Edit data </h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <form action="/edit" method="post" id="edited_data">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                    <div class="form-group">
                                        <label for="first">First name</label>
                                        <input class="form-control form-control-md" type="text" id="first" name="first" value="${client.firstName}"/>
                                    </div>
                                    <div class="form-group">
                                        <label for="last">Last name</label>
                                        <input class="form-control form-control-md" type="text" id="last" name="last" value="${client.lastName}"/>
                                    </div>
                                    <div class="form-group">
                                        <label for="dateOfBirth">Date of birth</label>
                                        <input class="form-control form-control-md" type="text" id="dateOfBirth" name="dateOfBirth" value="${client.birthDate}"/>
                                    </div>
                                    <div class="form-group">
                                        <label for="email">Email</label>
                                        <input class="form-control form-control-md" type="text" id="email" name="email" value="${client.email}"/>
                                    </div>
                                    <div class="form-group">
                                        <label for="phone">Phone</label>
                                        <input class="form-control form-control-md" type="text" id="phone" name="phone" value="${client.phone}"/>
                                    </div>
                                    <div class="form-group">
                                        <label for="password">Password</label>
                                        <input class="form-control form-control-md" type="text" id="password" name="password"/>
                                    </div>
                                    <input type="hidden" name="clientForEdit" value="${client.id}">
                                    <button onclick="pers_submit()" type="submit" name="user_search" class="btn btn-secondary" data-dismiss="modal"> Submit </button>
                                    <a href="/registration" class="btn btn-secondary btn-md active" role="button" aria-pressed="true"> New account</a>
                                </form>
                                <script type="text/javascript">
                                    function pers_submit() {
                                        document.getElementById("edited_data").submit();
                                    }
                                </script>
                            </div>
                        </div>
                    </div>
                </div>
                </th>
            </tr>
            <tr>
                <td>${client.firstName}</td>
                <td>${client.lastName}</td>
                <td>${client.birthDate}</td>
                <td>${client.email}</td>
                <td>${client.phone}</td>
                <td></td>
            </tr>
            </table>
            </div>

            <br>

            <!-- Addresses -->
            <div class="row">
                <h4> Addresses</h4>
                <table style="width: 100%; color: black; margin: 0 auto" class="table table-borderless">
                    <tr style="background: transparent; color: black">
                        <th>Country</th>
                        <th>City</th>
                        <th>Post code</th>
                        <th>Street</th>
                        <th>House number</th>
                        <th>Flat number</th>
                        <th>
                            <div class="col-sm-1">
                                <button type="button" class="btn btn-secondary btn-md" data-toggle="modal" data-target="#addnewaddr"> Add </button>
                            </div>

                            <!-- Modal window for add new address -->
                            <div class="modal fade" id="addnewaddr" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
                                    <div class="modal-dialog" role="document">
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <h5 class="modal-title" id="exampleModalLabel2"> Add new address </h5>
                                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                                    <span aria-hidden="true">&times;</span>
                                                </button>
                                            </div>
                                            <div class="modal-body">

                                                <form action="/addaddress" method="post" id="add_adr">
                                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                                    <div class="form-group">
                                                        <label for="country2" style="color: black">Country</label>
                                                        <input class="form-control form-control-md" type="text" id="country2" name="country"/>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="city2" style="color: black">City</label>
                                                        <input class="form-control form-control-md" type="text" id="city2" name="city" />
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="postcode2" style="color: black">Postcode</label>
                                                        <input class="form-control form-control-md" type="number" id="postcode2" name="postcode" min="0"/>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="street2" style="color: black">Street</label>
                                                        <input class="form-control form-control-md" type="text" id="street2" name="street" />
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="houseNumber2" style="color: black">House number</label>
                                                        <input class="form-control form-control-md" type="number" id="houseNumber2" name="houseNumber" min="0"/>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="flatNumber2" style="color: black">Flat number</label>
                                                        <input class="form-control form-control-md" type="number" id="flatNumber2" name="flatNumber" min="0"/>
                                                    </div>
                                                    <button onclick="form4_submit()" name="user_search" class="btn btn-secondary" data-dismiss="modal"> Add </button>
                                                    <script>
                                                        function form4_submit() {
                                                            document.getElementById("add_adr").submit();
                                                        };
                                                    </script>
                                                </form>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                        </th>
                    </tr>
                    <c:forEach items="${client.addressList}" var="address">
                        <tr>
                            <td>${address.country}</td>
                            <td>${address.city}</td>
                            <td>${address.postCode}</td>
                            <td>${address.street}</td>
                            <td>${address.houseNumber}</td>
                            <td>${address.flatNumber}</td>
                            <td>
                                <div>
                                    <div class="btn-toolbar" role="toolbar">
                                        <div class="btn-group mr-2" role="group">
                                            <button type="button" class="btn btn-secondary" onclick="changeAddress(${address.id})"> Change </button>
                                        </div>
                                        <div class="btn-group" role="group">
                                            <button type="button" class="btn btn-secondary" onclick="deleteform(${address.id})"> Delete </button>
                                        </div>
                                    </div>
                                </div>
                                <form action="/deleteaddress" method="post" style="display: none" id="${address.id}del">
                                        <input type="hidden" name="addressForDelete" value="${address.id}">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                    </form>
                                <script>
                                        function deleteform(id) {
                                            $("#" + id + "del").submit();
                                        }
                                    </script>
                                <script type="text/javascript">
                                        function changeAddress(id) {
                                            $("#" + id + "").toggle();
                                        }
                                    </script>


                                <div class="row" id="${address.id}" style="display: none">
                                    <br>
                                    <form action="/editaddress" method="post" >
                                        <input type="hidden" name="addressForEdit" value="${address.id}">
                                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                        <div class="form-group">
                                            <label for="country" style="color: black">Country</label>
                                            <input class="form-control form-control-md" type="text" id="country" name="country" value="${address.country}"/>
                                        </div>
                                        <div class="form-group">
                                            <label for="city" style="color: black">City</label>
                                            <input class="form-control form-control-md" type="text" id="city" name="city" value="${address.city}"/>
                                        </div>
                                        <div class="form-group">
                                            <label for="freakfield" style="color: black">Postcode</label>
                                            <input class="form-control form-control-md" type="number" id="freakfield" name="postcode" value="${address.postCode}" min="0"/>
                                        </div>
                                        <div class="form-group">
                                            <label for="street" style="color: black">Street</label>
                                            <input class="form-control form-control-md" type="text" id="street" name="street" value="${address.street}"/>
                                        </div>
                                        <div class="form-group">
                                            <label for="houseNumber" style="color: black">House number</label>
                                            <input class="form-control form-control-md" type="number" id="houseNumber" name="houseNumber" value="${address.houseNumber}" min="0"/>
                                        </div>
                                        <div class="form-group">
                                            <label for="flatNumber" style="color: black">Flat number</label>
                                            <input class="form-control form-control-md" type="number" id="flatNumber" name="flatNumber" value="${address.flatNumber}" min="0"/>
                                        </div>
                                        <button type="submit" name="user_search" class="btn btn-secondary" data-dismiss="modal"> Submit </button>
                                    </form>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
            </div>

            <br>

            <!-- Orders -->
            <div class="row">
                <div class="cardparent">
                    <div class="cardchild">
                <h4> Orders</h4>
                <table style="color: black; margin: 0 auto" class="table table-borderless">
                <tr>
                    <th>№</th>
                    <th>Date of order</th>
                    <th>Product name</th>
                    <th>Pcs.</th>
                    <th>Product price</th>
                    <th>Total sum</th>
                    <th>Colour</th>
                    <th>Brand</th>
                    <th>Payment method</th>
                    <th>Delivery method</th>
                    <th>Delivery address</th>
                    <th>Self pickup address</th>
                    <th>Payment status</th>
                    <th>Order status</th>
                </tr>

                <c:set var = "orders" scope = "session" value = "${client.orders}"/>
                <c:forEach items="${orders}" var="order">
                    <tr>
                        <td>
                             ${order.id}
                        </td>
                        <td>
                             ${order.dateOfOrder}
                        </td>
                        <td>
                            <c:forEach items="${order.orderProducts}" var="order_product">
                                <c:set var = "product" value = "${order_product.product}"/>
                                <table style="width: 100px">
                                    <tr>${product.productName}</tr>
                                </table>
                            </c:forEach>
                        </td>
                        <td>
                            <c:forEach items="${order.orderProducts}" var="order_product">
                                <c:set var = "product" value = "${order_product.product}"/>
                                <table style="width: 100px">
                                    <tr>${order_product.amount}</tr>
                                </table>
                            </c:forEach>
                        </td>
                        <td>
                            <c:forEach items="${order.orderProducts}" var="order_product">
                                <c:set var = "product" value = "${order_product.product}"/>
                                <table>
                                    <tr>${product.productPrice}</tr>
                                </table>
                            </c:forEach>
                        </td>
                        <td>
                             ${order.sumOfOrder}
                        </td>
                        <td>
                            <c:forEach items="${order.orderProducts}" var="order_product">
                                <c:set var = "product" value = "${order_product.product}"/>
                                <c:set var = "parameters" scope = "session" value = "${product.productParameteres}"/>
                                <table>
                                    <tr>${parameters.colour}</tr>
                                </table>
                            </c:forEach>
                        </td>
                        <td>
                            <c:forEach items="${order.orderProducts}" var="order_product">
                                <c:set var = "product" value = "${order_product.product}"/>
                                <c:set var = "parameters" scope = "session" value = "${product.productParameteres}"/>
                                <table>
                                    <tr>${parameters.brand}</tr>
                                </table>
                            </c:forEach>
                        </td>
                        <td>${order.paymentMethod}</td>
                        <td>${order.deliveryMethod}</td>
                        <td>${order.addressForDelivery}</td>
                        <td>${order.addressForSelfCollect}</td>
                        <td>${order.paymentStatus}</td>
                        <td>${order.orderStatus}</td>
                    </tr>
                </c:forEach>
                </table>
                </div>
                </div>
            </div>

            <style>
                .cardparent {
                    width: 1200px;
                    height:600px;
                    overflow: hidden;
                    overflow-x: scroll;
                    white-space:nowrap;
                }
                .cardchild {
                    display: inline-block;
                    vertical-align: top;
                    width: 15rem;
                    height:600px;
                }
            </style>

        </div>

        <br>
        <br>
        <br>
        <br>
        <br>

        <!-- Footer -->
        <div class="footer row" style="color: black">
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
</body>
<script type="text/javascript" src="../resources/static/js/app2.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>