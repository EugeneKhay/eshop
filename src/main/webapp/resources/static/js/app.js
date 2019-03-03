var app = angular.module('app', ['ngRoute','ngResource']);
app.config(function($routeProvider){
    $routeProvider
        .when('/products',{
            templateUrl: 'resources/static/views/products.html',
            controller: 'productsController'
        })
        .when('/orders',{
            templateUrl: 'resources/static/views/orders.html',
            controller: 'ordersController'
        })
        .when('/contacts',{
            templateUrl: 'resources/static/views/contacts.html',
            controller: 'contactsController'
        })
        .otherwise(
            { redirectTo: '/'}
        );
});