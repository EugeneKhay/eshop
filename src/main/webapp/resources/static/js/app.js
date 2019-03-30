function stats() {
    $("#stats").css({"display" : "block"});
    $("#orders").css({"display" : "none"});
    $("#products").css({"display" : "none"});
    $("#clients").css({"display" : "none"});
}
function orders() {
    $("#orders").css({"display" : "block"});
    $("#stats").css({"display" : "none"});
    $("#products").css({"display" : "none"});
    $("#clients").css({"display" : "none"});
}
function products() {
    $("#products").css({"display" : "block"});
    $("#stats").css({"display" : "none"});
    $("#orders").css({"display" : "none"});
    $("#clients").css({"display" : "none"});
}
function clients() {
    $("#clients").css({"display" : "block"});
    $("#products").css({"display" : "none"});
    $("#stats").css({"display" : "none"});
    $("#orders").css({"display" : "none"});
}

function form_submit() {
    document.getElementById("search_form").submit();
}

function edit_submit() {
    document.getElementById("edited_order").submit();
}

function deleteItem() {
    $.ajax({
        url : '/delete',
        type: 'GET',
        dataType: 'json',
        data : ({
            delete: ${product.id}
        }),
        success: function (result) {
            $('#totalPrice').text(result);
            $('#deleteItem-${product.id}').css({"display" : "none"});
        }
    });
}

function increase() {
    $.ajax({
        url : '/editOrderPlus',
        type: 'GET',
        dataType: 'json',
        data : ({
            editOrderPlus: ${product.id}
        }),
        success: function (result) {
            $('#totalPrice').text(result[0]);
            $('#amount').text(result[1]);
        }
    });
}

function decrease() {
    $.ajax({
        url : '/editOrderMinus',
        type: 'GET',
        dataType: 'json',
        data : ({
            editOrderMinus: ${product.id}
        }),
        success: function (result) {
            $('#totalPrice').text(result[0]);
            $('#amount').text(result[1]);
        }
    });
}