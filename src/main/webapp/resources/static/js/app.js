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

    function deleteItem(id) {
        $.ajax({
            url : '/delete',
            type: 'GET',
            dataType: 'json',
            data : ({
                delete: id
            }),
            success: function (result) {
                $('#totalPrice').text(result);
                $('#product-'+ id + 'del').css({"display" : "none"});
            }
        });
    }

    function increase(id) {
        $.ajax({
            url : '/editOrderPlus',
            type: 'GET',
            dataType: 'json',
            data : ({
                editOrderPlus: id
            }),
            success: function (result) {
                $('#totalPrice').text(result[0]);
                $('#amount'+id+'edit').text(result[1]);
            }
        });
    }
    function decrease(id) {
        $.ajax({
            url : '/editOrderMinus',
            type: 'GET',
            dataType: 'json',
            data : ({
                editOrderMinus: id
            }),
            success: function (result) {
                $('#totalPrice').text(result[0]);
                $('#amount'+id+'edit').text(result[1]);
            }
        });
    }
