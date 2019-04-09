
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <%--<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/hiq@2.6.0/dist/hiq.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">--%>
    <link rel="stylesheet" type="text/css" href="../resources/static/css/style.css"/>
</head>
<body>
    <div class="container">
        <div class="row" id="regTitle">
            <h3>Registration</h3>
        </div>

        <div class="row" id="basketNav">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb">
                    <li class="breadcrumb-item"><a href="/">Home</a></li>
                    <li class="breadcrumb-item"><a href="/phone">Products</a></li>
                    <li class="breadcrumb-item active" aria-current="page">Registration</li>
                </ol>
            </nav>
        </div>


        <div class="row" id="fill">

        <div class="container">
            <form method="post" action="/registration">
                <h1> Please, fill in the form: </h1>
                <div class="form-group">
                    <label for="required-input">First name</label>
                    <input class="form-control form-control-md" type="text" id="required-input" name="firstName" required>
                </div>
                <div class="form-group">
                    <label for="required-input2">Last name</label>
                    <input class="form-control form-control-md" type="text" id="required-input2" name="lastName" required>
                </div>
                <div class="form-group">
                    <label for="required-input3">Date of birth</label>
                    <input class="form-control form-control-md" type="date" id="required-input3" name="birthDate" required>
                </div>
                <div class="form-group">
                    <label for="valid-email">E-mail</label>
                    <input class="form-control form-control-md" type="email" id="valid-email" name="email" required>
                </div>
                <div class="form-group">
                    <label for="required-input4">Password</label>
                    <input class="form-control form-control-md" type="text" id="required-input4" name="password" required>
                </div>
                <div class="form-group">
                    <label for="required-input5">Country</label>
                    <input class="form-control form-control-md" type="text" id="required-input5" name="country" required>
                </div>
                <div class="form-group">
                    <label for="required-input6">City</label>
                    <input class="form-control form-control-md" type="text" id="required-input6" name="city" required>
                </div>
                <div class="form-group">
                    <label for="required-input7">Post code</label>
                    <input class="form-control form-control-md" type="number" id="required-input7" name="postcode" required>
                </div>
                <div class="form-group">
                    <label for="required-input8">Street</label>
                    <input class="form-control form-control-md" type="text" id="required-input8" name="street" required>
                </div>
                <div class="form-group">
                    <label for="required-input9">House</label>
                    <input class="form-control form-control-md" type="number" id="required-input9" name="house" required>
                </div>
                <div class="form-group">
                    <label for="required-input10">Flat</label>
                    <input class="form-control form-control-md" type="number" id="required-input10" name="flat" required>
                </div>
                <button type="submit" class="btn btn-secondary">Submit</button>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </div>
        </div>



        <div>
            <br>
            <br>
            <br>
            <br>
            <br>
        </div>

        <div class="footer" >
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

</body>
</html>











<%--<form method="post" action="/registration" id="regForm">--%>

<%--<div class="form-group">--%>
<%--<label for="firstName">First name</label>--%>
<%--<input class="form-control form-control-md" name="firstName" type="text" class="form-control" id="firstName" aria-describedby="emailHelp" placeholder="Name">--%>
<%--</div>--%>
<%--<div class="form-group">--%>
<%--<label for="lastName">Last name</label>--%>
<%--<input class="form-control form-control-md" name="lastName" type="text" class="form-control" id="lastName" placeholder="Last name">--%>
<%--</div>--%>
<%--<div class="form-group">--%>
<%--<label  for="birthDate">Birth date</label>--%>
<%--<input style="background: transparent; color: black" class="form-control form-control-md" name="birthDate" type="date" class="form-control" id="birthDate" placeholder="YYYY MM DD">--%>
<%--</div>--%>
<%--<div class="form-group">--%>
<%--<label style="color: black" for="email">Email</label>--%>
<%--<input style="background: transparent; color: black" class="form-control form-control-md" name="email" type="text"  id="email" placeholder="example@email.com">--%>
<%--</div>--%>
<%--<div class="form-group">--%>
<%--<label style="color: black" for="password">Password</label>--%>
<%--<input style="background: transparent; color: black" class="form-control form-control-md" name="password" type="text"  id="password" placeholder="Password">--%>
<%--</div>--%>
<%--<div class="form-group">--%>
<%--<label style="color: black" for="country">Country</label>--%>
<%--<input style="background: transparent; color: black" class="form-control form-control-md" name="country" type="text"  id="country" placeholder="Country">--%>
<%--</div>--%>
<%--<div class="form-group">--%>
<%--<label style="color: black" for="city">City</label>--%>
<%--<input style="background: transparent; color: black" class="form-control form-control-md" name="city" type="text"  id="city" placeholder="City">--%>
<%--</div>--%>
<%--<div class="form-group">--%>
<%--<label style="color: black" for="postcode">Post code</label>--%>
<%--<input style="background: transparent; color: black" class="form-control form-control-md" name="postcode" type="number"  id="postcode" placeholder="Post code">--%>
<%--</div>--%>
<%--<div class="form-group">--%>
<%--<label style="color: black" for="street">Street</label>--%>
<%--<input style="background: transparent; color: black" class="form-control form-control-md" name="street" type="text"  id="street" placeholder="Street">--%>
<%--</div>--%>
<%--<div class="form-group">--%>
<%--<label style="color: black" for="house">House</label>--%>
<%--<input style="background: transparent; color: black" class="form-control form-control-md" name="house" type="number"  id="house" placeholder="House">--%>
<%--</div>--%>
<%--<div class="form-group">--%>
<%--<label style="color: black" for="flat">Flat</label>--%>
<%--<input style="background: transparent; color: black" class="form-control form-control-md" name="flat" type="number"  id="flat" placeholder="Flat">--%>
<%--</div>--%>
<%--<button type="submit" class="btn btn-secondary">Submit</button>--%>
<%--<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>--%>
<%--</form>--%>













