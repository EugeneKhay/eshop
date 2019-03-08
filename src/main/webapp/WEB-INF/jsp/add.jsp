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
</head>
<body>
<h1>Add new client</h1>
<div>
    <!--<form method="post" action="/add">
        <p>Please, enter your data:</p>
        <p><input type="text" name="firstName" placeholder="firstName"></p>
        <p><input type="text" name="lastName" placeholder="lastName"></p>
        <p><input type="text" name="birthDate" placeholder="YYYY MM DD"></p>
        <p><input type="text" name="email" placeholder="email"></p>
        <p><input type="text" name="password" placeholder="password"></p>

        <p>Please, enter your address:</p>
        <p><input type="text" name="country" placeholder="country"></p>
        <p><input type="text" name="city" placeholder="city"></p>
        <p><input type="number" name="postcode" placeholder="post code"></p>
        <p><input type="text" name="street" placeholder="street"></p>
        <p><input type="number" name="house" placeholder="house"></p>
        <p><input type="number" name="flat" placeholder="flat"></p>

        <p><button type="submit" value="OK">Add</button></p>
    </form>-->

    <form method="post" action="/add">
        <div class="form-group">
            <label for="firstName">First name</label>
            <input class="form-control form-control-lg" type="text" class="form-control" id="firstName" aria-describedby="emailHelp" placeholder="Name">
        </div>
        <div class="form-group">
            <label for="lastName">Last name</label>
            <input class="form-control form-control-lg" type="text" class="form-control" id="lastName" placeholder="Last name">
        </div>
        <div class="form-group">
            <label for="birthDate">Birth date</label>
            <input class="form-control form-control-lg" type="text" class="form-control" id="birthDate" placeholder="YYYY MM DD">
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input class="form-control form-control-lg" type="text"  id="email" placeholder="example@email.com">
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <input class="form-control form-control-lg" type="text"  id="password" placeholder="Password">
        </div>
        <div class="form-group">
            <label for="country">Country</label>
            <input class="form-control form-control-lg" type="text"  id="country" placeholder="Country">
        </div>
        <div class="form-group">
            <label for="city">City</label>
            <input class="form-control form-control-lg" type="text"  id="city" placeholder="City">
        </div>
        <div class="form-group">
            <label for="postcode">Post code</label>
            <input class="form-control form-control-lg" type="number"  id="postcode" placeholder="Post code">
        </div>
        <div class="form-group">
            <label for="street">Street</label>
            <input class="form-control form-control-lg" type="text"  id="street" placeholder="Street">
        </div>
        <div class="form-group">
            <label for="house">House</label>
            <input class="form-control form-control-lg" type="number"  id="house" placeholder="House">
        </div>
        <div class="form-group">
            <label for="flat">Flat</label>
            <input class="form-control form-control-lg" type="number"  id="flat" placeholder="Flat">
        </div>

        <button type="submit" class="btn btn-primary">Submit</button>
    </form>

</div>
</body>
</html>
