
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>My account</title>
    <%--<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">--%>
    <%--<link rel="stylesheet" type="text/css" href="../resources/static/css/style.css"/>--%>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="/resources/static/css/app.css">
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="//ajax.aspnetcdn.com/ajax/jquery.ui/1.10.3/jquery-ui.min.js"></script>
</head>

<body style="background-image: url(/resources/static/images/background.jpg)">

<h3 style="margin: 30px; color: aliceblue; margin-top: 10px">My account</h3>

<div class="container" style="margin-top: 30px">

    <div class="row">
        <div class="col-sm-3">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="/"> Home </a></li>
                    <li class="breadcrumb-item active" aria-current="page"> My account </li>
                </ol>
            </nav>
        </div>
        <div class="col-sm-8">

        </div>
        <div class="col-sm-1">
            <sec:authorize access="isAuthenticated()">
                <%--<a style="color: aliceblue; padding-left: 30px" href="/logout"> Logout </a>--%>
                <button type="button" class="btn btn-secondary">
                    <a style="color: aliceblue" href="/logout">Logout</a>
                </button>
            </sec:authorize>
        </div>
    </div>

    <div class="row">
        <table style="width: 65%; color: aliceblue; margin: 0 auto" class="table table-borderless">
        <tr style="background: transparent; color: aliceblue">
            <th>First name</th>
            <th>Last name</th>
            <th>Date of birth</th>
            <th>Email</th>
            <th>Password</th>
            <th>Country</th>
            <th>City</th>
            <th>Post code</th>
            <th>Street</th>
            <th>House number</th>
            <th>Flat number</th>
        </tr>
        <tr style="background: transparent; color: aliceblue">
            <td>${client.firstName}</td>
            <td>${client.lastName}</td>
            <td>${client.birthDate}</td>
            <td>${client.email}</td>
            <td>${client.password}</td>
            <c:set var = "address" scope = "session" value = "${client.address}"/>
            <td>${address.country}</td>
            <td>${address.city}</td>
            <td>${address.postCode}</td>
            <td>${address.street}</td>
            <td>${address.houseNumber}</td>
            <td>${address.flatNumber}</td>
            <td>
                <div class="col-sm-1">
                    <button type="button" class="btn btn-secondary btn-md" data-toggle="modal" data-target="#personal"> Edit </button>
                </div>
            </td>

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
                                <p>
                                    <label for="first">First name</label>
                                    <input type="text" id="first" name="first" value="${client.firstName}"/>
                                </p>
                                <p>
                                    <label for="last">Last name</label>
                                    <input type="text" id="last" name="last" value="${client.lastName}"/>
                                </p>
                                <p>
                                    <label for="dateOfBirth">Date of birth</label>
                                    <input type="text" id="dateOfBirth" name="dateOfBirth" value="${client.birthDate}"/>
                                </p>
                                <p>
                                    <label for="email">Email</label>
                                    <input type="text" id="email" name="email" value="${client.email}"/>
                                </p>
                                <p>
                                    <label for="password">Password</label>
                                    <input type="text" id="password" name="password" value="${client.password}"/>
                                </p>
                                <c:set var = "address" scope = "session" value = "${client.address}"/>
                                <p>
                                    <label for="country">Country</label>
                                    <input type="text" id="country" name="country" value="${address.country}"/>
                                </p>
                                <p>
                                    <label for="city">City</label>
                                    <input type="text" id="city" name="city" value="${address.city}"/>
                                </p>
                                <p>
                                    <label for="postcode">Post code</label>
                                    <input type="number" id="postcode" name="postcode" value="${address.postCode}"/>
                                </p>
                                <p>
                                    <label for="street">Street</label>
                                    <input type="text" id="street" name="street" value="${address.street}"/>
                                </p>
                                <p>
                                    <label for="houseNumber">House number</label>
                                    <input type="number" id="houseNumber" name="houseNumber" value="${address.houseNumber}"/>
                                </p>
                                <p>
                                    <label for="flatNumber">Flat number</label>
                                    <input type="number" id="flatNumber" name="flatNumber" value="${address.flatNumber}"/>
                                </p>

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
        </tr>
    </table>
    </div>

    <h3 style="margin: 30px; color: aliceblue">My orders</h3>
    <div class="row">

    <table style="width: 80%; color: aliceblue; margin: 0 auto" class="table table-borderless">
        <tr>
            <th>Number of order</th>
            <th>Date of order</th>
            <th>Product name</th>
            <th>Price</th>
            <th>Colour</th>
            <th>Brand</th>
            <th>Payment method</th>
            <th>Delivery method</th>
            <th>Payment status</th>
            <th>Order status</th>
        </tr>

        <c:set var = "orders" scope = "session" value = "${client.orders}"/>
        <c:forEach items="${orders}" var="order">
            <tr>
                <td>${order.id}</td>
                <td style="width: 100px">${order.dateOfOrder}</td>
                <td>
                        <c:forEach items="${order.productsInOrder}" var="product">
                            <table style="width: 100px">
                                <tr>${product.productName}</tr>
                            </table>
                        </c:forEach>
                </td>
                <td>
                    <c:forEach items="${order.productsInOrder}" var="product">
                            <table>
                                <tr>${product.productPrice}</tr>
                            </table>
                        </c:forEach>
                </td>
                <td>
                        <c:forEach items="${order.productsInOrder}" var="product">
                            <c:set var = "parameters" scope = "session" value = "${product.productParameteres}"/>
                            <table>
                                <tr>${parameters.colour}</tr>
                            </table>
                        </c:forEach>
                </td>
                <td>
                        <c:forEach items="${order.productsInOrder}" var="product">
                            <c:set var = "parameters" scope = "session" value = "${product.productParameteres}"/>
                            <table>
                                <tr>${parameters.brand}</tr>
                            </table>
                        </c:forEach>
                </td>
                <td>${order.paymentMethod}</td>
                <td>${order.deliveryMethod}</td>
                <td>${order.paymentStatus}</td>
                <td>${order.orderStatus}</td>

            </tr>
            </c:forEach>
        </table>
    </div>

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
<script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>
