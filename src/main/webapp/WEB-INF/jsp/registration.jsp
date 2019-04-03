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
    <title>Registration</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="../resources/static/css/app.css">
</head>
<body style="background-image: url(/resources/static/images/background.jpg)">

<h3 style="color: aliceblue; margin: 30px; margin-top: 10px">Registration</h3>

<h5 style="color: aliceblue; padding-top: 15px; margin: 30px">Please, fill in the form:</h5>

<div>
    <form method="post" action="/registration" style="width: 35%; margin: 0 auto">
        <div class="form-group" style="padding-top: 30px">
            <label style="color: aliceblue" for="firstName">First name</label>
            <input style="background: transparent; color: aliceblue" class="form-control form-control-md" name="firstName" type="text" class="form-control" id="firstName" aria-describedby="emailHelp" placeholder="Name">
        </div>
        <div class="form-group">
            <label style="color: aliceblue" for="lastName">Last name</label>
            <input style="background: transparent; color: aliceblue" class="form-control form-control-md" name="lastName" type="text" class="form-control" id="lastName" placeholder="Last name">
        </div>
        <div class="form-group">
            <label style="color: aliceblue" for="birthDate">Birth date</label>
            <input style="background: transparent; color: aliceblue" class="form-control form-control-md" name="birthDate" type="date" class="form-control" id="birthDate" placeholder="YYYY MM DD">
        </div>
        <div class="form-group">
            <label style="color: aliceblue" for="email">Email</label>
            <input style="background: transparent; color: aliceblue" class="form-control form-control-md" name="email" type="text"  id="email" placeholder="example@email.com">
        </div>
        <div class="form-group">
            <label style="color: aliceblue" for="password">Password</label>
            <input style="background: transparent; color: aliceblue" class="form-control form-control-md" name="password" type="text"  id="password" placeholder="Password">
        </div>
        <div class="form-group">
            <label style="color: aliceblue" for="country">Country</label>
            <input style="background: transparent; color: aliceblue" class="form-control form-control-md" name="country" type="text"  id="country" placeholder="Country">
        </div>
        <div class="form-group">
            <label style="color: aliceblue" for="city">City</label>
            <input style="background: transparent; color: aliceblue" class="form-control form-control-md" name="city" type="text"  id="city" placeholder="City">
        </div>
        <div class="form-group">
            <label style="color: aliceblue" for="postcode">Post code</label>
            <input style="background: transparent; color: aliceblue" class="form-control form-control-md" name="postcode" type="number"  id="postcode" placeholder="Post code">
        </div>
        <div class="form-group">
            <label style="color: aliceblue" for="street">Street</label>
            <input style="background: transparent; color: aliceblue" class="form-control form-control-md" name="street" type="text"  id="street" placeholder="Street">
        </div>
        <div class="form-group">
            <label style="color: aliceblue" for="house">House</label>
            <input style="background: transparent; color: aliceblue" class="form-control form-control-md" name="house" type="number"  id="house" placeholder="House">
        </div>
        <div class="form-group">
            <label style="color: aliceblue" for="flat">Flat</label>
            <input style="background: transparent; color: aliceblue" class="form-control form-control-md" name="flat" type="number"  id="flat" placeholder="Flat">
        </div>

        <button type="submit" class="btn btn-secondary">Submit</button>
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

    </form>

</div>
</body>
</html>
