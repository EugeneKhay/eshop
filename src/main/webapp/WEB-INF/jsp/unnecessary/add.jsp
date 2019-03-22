<%--
  Created by IntelliJ IDEA.
  User: evgenijhajmovskij
  Date: 2019-03-03
  Time: 14:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new Client</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="../resources/static/css/app.css">
</head>
<body>
<h1>Add new client</h1>
<div>
    <form method="post" action="/add" style="width: 65%">
        <div class="form-group">
            <label for="firstName">First name</label>
            <input class="form-control form-control-lg" name="firstName" type="text" class="form-control" id="firstName" aria-describedby="emailHelp" placeholder="Name">
        </div>
        <div class="form-group">
            <label for="lastName">Last name</label>
            <input class="form-control form-control-lg" name="lastName" type="text" class="form-control" id="lastName" placeholder="Last name">
        </div>
        <div class="form-group">
            <label for="birthDate">Birth date</label>
            <input class="form-control form-control-lg" name="birthDate" type="text" class="form-control" id="birthDate" placeholder="YYYY MM DD">
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input class="form-control form-control-lg" name="email" type="text"  id="email" placeholder="example@email.com">
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input class="form-control form-control-lg" name="password" type="text"  id="password" placeholder="Password">
        </div>
        <div class="form-group">
            <label for="country">Country</label>
            <input class="form-control form-control-lg" name="country" type="text"  id="country" placeholder="Country">
        </div>
        <div class="form-group">
            <label for="city">City</label>
            <input class="form-control form-control-lg" name="city" type="text"  id="city" placeholder="City">
        </div>
        <div class="form-group">
            <label for="postcode">Post code</label>
            <input class="form-control form-control-lg" name="postcode" type="number"  id="postcode" placeholder="Post code">
        </div>
        <div class="form-group">
            <label for="street">Street</label>
            <input class="form-control form-control-lg" name="street" type="text"  id="street" placeholder="Street">
        </div>
        <div class="form-group">
            <label for="house">House</label>
            <input class="form-control form-control-lg" name="house" type="number"  id="house" placeholder="House">
        </div>
        <div class="form-group">
            <label for="flat">Flat</label>
            <input class="form-control form-control-lg" name="flat" type="number"  id="flat" placeholder="Flat">
        </div>

        <button type="submit" class="btn btn-primary">Submit</button>
    </form>

</div>
</body>
</html>
