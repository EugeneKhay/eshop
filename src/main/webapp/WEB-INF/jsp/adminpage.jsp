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
    <title>Admin page</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="../resources/static/css/app.css"></head>
<body>

<p><a href="/orders">View orders</a></p>
<p><a href="/statistics">Statistics</a></p>
<p><a href="/viewproducts">View products</a></p>
<p><a href="/viewclients">View clients</a></p>

    <h3>Add new product</h3>

    <form method="post" action="/addproducts">
        <p><b>Please, enter product properties</b></p>
        <p><input type="text" name="productName" placeholder="productName"></p>
        <p><input type="text" name="productPrice" placeholder="productPrice"></p>


        <p><select class="custom-select mr-sm-2" name="category" id="category">
            <option selected>Choose...</option>
            <option value="PHONE">PHONE</option>
            <option value="TV_VIDEO">TV_VIDEO</option>
            <option value="AUDIO">AUDIO</option>
            <option value="LAPTOP">LAPTOP</option>
            <option value="TABLET">TABLET</option>
        </select></p>

        <p><input type="number" name="amount" placeholder="amount"></p>
        <p><b>Please, enter your product parameteres:</b></p>
        <p><input type="text" name="colour" placeholder="colour"></p>
        <p><input type="text" name="brand" placeholder="brand"></p>
        <p><button type="submit" value="OK">Add</button></p>
    </form>

<div>
    <h3>List of products</h3>
    <table class="table table-borderless table-hover" width="80%">
        <tr>
            <th>Product ID</th>
            <th>Product name</th>
            <th>Price</th>
            <th>Amount</th>
            <th>Category</th>
            <th>Colour</th>
            <th>Brand</th>
        </tr>

        <c:forEach items="${products}" var="product">
        <tr>
            <td>${product.id}</td>
            <td>${product.productName}</td>
            <td>${product.productPrice}</td>
            <td>${product.amount}</td>
            <td>${product.category}</td>
            <c:set var = "parameters" scope = "session" value = "${product.productParameteres}"/>
            <td>${parameters.colour}</td>
            <td>${parameters.brand}</td>
        </tr>
        </c:forEach>
    </table>
</div>

<div>
    <h3>List of clients</h3>
    <table class="table table-borderless table-hover" width="80%">
        <tr>
            <th>ID</th>
            <th>First name</th>
            <th>Last name</th>
            <th>Date of birth</th>
            <th>Email</th>
            <th>Password</th>
            <th>Country</th>
            <th>City</th>
            <th>Post code</th>
            <th>Street</th>
            <th>Hose number</th>
            <th>Flat number</th>
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
            <td>${address.country}</td>
            <td>${address.city}</td>
            <td>${address.postCode}</td>
            <td>${address.street}</td>
            <td>${address.houseNumber}</td>
            <td>${address.flatNumber}</td>
        </tr>
    </c:forEach>
    </table>
</div>



</body>
</html>
