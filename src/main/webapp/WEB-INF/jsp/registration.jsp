<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="../resources/static/css/style.css"/>
</head>
<body>

<div class="wrapper">

    <div class="wrapperForFooter">

        <div class="header">

            <div class="row" id="regTitle">
                <div class="col-sm-2">
                    <h3>Registration</h3>
                </div>
                <div class="col-sm">
                    <nav aria-label="breadcrumb">
                        <ol class="breadcrumb" style="background-color: transparent">
                            <li class="breadcrumb-item"><a href="/">Home</a></li>
                            <li class="breadcrumb-item active" aria-current="page">Registration</li>
                        </ol>
                    </nav>
                </div>
            </div>
        </div>

        <br>
        <br>
        <br>


        <div class="container content">
            <form method="post" action="/registration" style="width: 50%">
                <h3> Please, fill in the form: </h3>
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
                    <label for="valid-email2">Phone</label>
                    <input class="form-control form-control-md" type="text" id="valid-email2" name="phone" required>
                </div>
                <div class="form-group">
                    <label for="required-input4">Password</label>
                    <input class="form-control form-control-md" type="text" id="required-input4" name="password" required>
                </div>
                <button type="submit" class="btn btn-secondary">Submit</button>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
            </form>
        </div>

        <br>
        <br>

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
</div>
</body>
</html>













