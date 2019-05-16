<!DOCTYPE html>
<%@ page import="com.eshop.domain.Basket" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>E-shop</title>
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" type="text/css" href="/resources/static/css/style.css"/>
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>
<body>

<div class="wrapper">

    <div class="wrapperForFooter">

<div class="header">
    <div class="row" id="productsTitle">
        <div class="col-sm-2">
            <h3>${pageName}</h3>
        </div>
        <div class="col-sm-3">
            <nav aria-label="breadcrumb">
                <ol class="breadcrumb" style="background-color: transparent">
                    <li class="breadcrumb-item"><a href="/">Home</a></li>
                    <li class="breadcrumb-item active" aria-current="page">${pageName}</li>
                </ol>
            </nav>
        </div>
        <div class="col-sm"></div>
        <div class="col-sm-1">
            <sec:authorize access="isAuthenticated()">
                <strong style="color: black"> Account: <a style="color: black" href="/personal"> <sec:authentication property="principal.firstName" /> </a> </strong>
            </sec:authorize>
        </div>
        <div class="col-sm-1">
            <sec:authorize access="isAuthenticated()">
                <button type="button" class="btn btn-secondary btn-sm"><a style="color: aliceblue" href="/logout"/> Logout </button>
            </sec:authorize>
        </div>
    </div>

    <!-- Nav -->
    <div class="row">
        <div class="col-sm-1" style="margin-top: 60px">
        </div>
        <div class="col-sm">
            <ul class="nav justify-content-center">
                <c:forEach items="${categories}" var="category">
                    <li class="nav-item">
                        <a class="nav-link" href="/${category}">${category}</a>
                    </li>
                </c:forEach>
            </ul>
        </div>

        <!-- Basket -->
        <div class="col-sm-1">
            <button type="button" class="btn btn-primary-outline">
                <a href="/basket" style="color: aliceblue">
                    <span><img style="width: 45px; height: 30px" src="/resources/static/images/basket2.png" alt="Basket"/> </span>
                    <span class="badge badge-light" id="basketCount"><%= ((Basket) session.getAttribute("shop_basket")).getProductsInBasket().values().stream().reduce((s1, s2) -> s1 + s2).orElse(0) %></span>
                </a>
            </button>
        </div>

        <!-- Sign In-->
        <div class="col-sm-1">
            <button type="button" class="btn btn-primary-outline btn-md" data-toggle="modal" data-target="#exampleModal">
                <span><img style="width: 45px; height: 45px" src="/resources/static/images/sign_in2.png" alt="Sign in"/> </span>
            </button>
        </div>

        <!-- Modal window -->
        <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Please, sign in</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <c:url var="loginUrl" value="/login" />
                        <form action="${loginUrl}" method="post" id="search_form">
                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                            <c:if test="${param.error != null}">
                                <p>
                                    Invalid username and password.
                                </p>
                            </c:if>
                            <div class="form-group">
                                <label for="username">Username</label>
                                <input type="text" id="username" name="username"/>
                            </div>
                            <div class="form-group">
                                <label for="password">Password</label>
                                <input type="password" id="password" name="password"/>
                            </div>
                            <button onclick="form_submit()" name="user_search" class="btn btn-secondary" data-dismiss="modal">Log in</button>
                            <a href="/registration" class="btn btn-secondary btn-md active" role="button" aria-pressed="true">Registration</a>

                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

    <div class="container content">
    <!-- Search -->
    <div class="row" style="margin-bottom: 100px; margin-top: 20px">
        <div class="col-sm-3">
            <c:url var="searching" value="${requestScope['javax.servlet.forward.request_uri']}" />
            <form method="post" action="${searching}">
                <h5>Filter</h5>
                <div class="form-check form-group" >
                    <input class="form-check-input" type="checkbox" name="search_type1" value="Price" id="defaultCheck1">
                    <label class="form-check-label" for="defaultCheck1">Price</label>
                    <input style="display: none" class="form-control form-control-md" id="search_input11" type="number" name="search_res11" placeholder="min" >
                    <input style="display: none" class="form-control form-control-md" id="search_input12" type="number" name="search_res12" placeholder="max" >
                </div>
                <div class="form-check form-group">
                    <input class="form-check-input" type="checkbox" name="search_type2" value="Brand" id="defaultCheck2">
                    <label class="form-check-label" for="defaultCheck2">Brand</label>
                    <select class="custom-select mr-sm-2" name="search_res2" id="search_input2" style="width: auto; display: none">
                        <option selected></option>
                        <option >Apple</option>
                        <option >Samsung</option>
                        <option >LG</option>
                        <option >HP</option>
                        <option >Dell</option>
                        <option >Microsoft</option>
                        <option >Huawei</option>
                        <option >Xiaomi</option>
                        <option >Canon</option>
                        <option >Google</option>
                        <option >Canon</option>
                        <option >Nikon</option>
                    </select>
                </div>
                <div class="form-check form-group">
                    <input class="form-check-input" type="checkbox" name="search_type3" value="Colour" id="defaultCheck3">
                    <label class="form-check-label" for="defaultCheck3">Colour</label>
                    <select class="custom-select mr-sm-2" name="search_res3" id="search_input3" style="width: auto; display: none">
                        <option selected></option>
                        <option >black</option>
                        <option >white</option>
                        <option >silver</option>
                        <option >grey</option>
                        <option >gold</option>
                        <option >blue</option>
                        <option >green</option>
                    </select>
                </div>
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                <div class="input-group-append">
                    <button class="btn btn-secondary" type="submit">Find/Clear</button>
                </div>
            </form>
        </div>

        <!-- Cards -->
        <div class="col-sm">
            <div class="row" id="output">
                <c:forEach items="${items}" var="item">
                        <div class="card">
                                <div id="carouselExampleControls${item.id}" class="carousel slide" data-ride="carousel">
                                    <div class="carousel-inner">
                                        <c:url var="imageUrl" value="${item.imagePath}" />
                                        <div class="carousel-item active" data-interval="100000">
                                            <img src="${imageUrl}/1.jpg" class="d-block w-100" alt="1">
                                        </div>
                                        <div class="carousel-item" data-interval="100000">
                                            <img src="${imageUrl}/2.jpg" class="d-block w-100" alt="2">
                                        </div>
                                        <div class="carousel-item" data-interval="100000">
                                            <img src="${imageUrl}/3.jpg" class="d-block w-100" alt="3">
                                        </div>
                                    </div>
                                        <a class="carousel-control-prev" href="#carouselExampleControls${item.id}" role="button" data-slide="prev">
                                            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                            <span class="sr-only">Previous</span>
                                        </a>
                                        <a class="carousel-control-next" href="#carouselExampleControls${item.id}" role="button" data-slide="next">
                                            <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                            <span class="sr-only">Next</span>
                                        </a>
                                </div>
                                <div class="card-body">
                                    <h5 class="card-title">${item.productName}</h5>
                                    <p class="card-text">${item.productPrice} ₽</p>
                                    <c:set var = "params" scope = "session" value = "${item.productParameteres}"/>
                                    <ul class="list-group list-group-flush">
                                        <li class="list-group-item" >${params.brand}</li>
                                        <li class="list-group-item" >OS: ${params.operatingSystem}</li>
                                        <li class="list-group-item" >Colour: ${params.colour}</li>
                                        <li class="list-group-item" >Weight: ${params.weight} gr.</li>
                                    </ul>
                                    <input  type="hidden" name="item" value=${item.id}>
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                    <input type="hidden" name="page" value="${requestScope['javax.servlet.forward.request_uri']}"/>
                                    <button onclick="add(${item.id})" type="submit" class="btn btn-secondary"> Buy </button>
                                </div>
                        </div>
                </c:forEach>
            </div>
        </div>
    </div>

    </div>

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
</div>

<script>
    $("#defaultCheck1").change(function(){
        if(this.checked){
            $("#search_input11").css({"display" : "block"});
            $("#search_input12").css({"display" : "block"});
        }else{
            $("#search_input11").css({"display" : "none"});
            $("#search_input12").css({"display" : "none"});
        }
    });
    $("#defaultCheck2").change(function(){
        if(this.checked){
            $("#search_input2").css({"display" : "block"});
        }else{
            $("#search_input2").css({"display" : "none"});
        }
    });
    $("#defaultCheck3").change(function(){
        if(this.checked){
            $("#search_input3").css({"display" : "block"});
        }else{
            $("#search_input3").css({"display" : "none"});
        }
    });
</script>


<script type="text/javascript" src="../resources/static/js/app2.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>