function stats() {
    $("#stats").css({"display" : "block"});
    $("#orders").css({"display" : "none"});
    $("#products").css({"display" : "none"});
    $("#clients").css({"display" : "none"});
    $("#shops").css({"display" : "none"});
}
function orders() {
    $("#orders").css({"display" : "block"});
    $("#stats").css({"display" : "none"});
    $("#products").css({"display" : "none"});
    $("#clients").css({"display" : "none"});
    $("#shops").css({"display" : "none"});
}
function products() {
    $("#products").css({"display" : "block"});
    $("#stats").css({"display" : "none"});
    $("#orders").css({"display" : "none"});
    $("#clients").css({"display" : "none"});
    $("#shops").css({"display" : "none"});
}
function clients() {
    $("#clients").css({"display" : "block"});
    $("#products").css({"display" : "none"});
    $("#stats").css({"display" : "none"});
    $("#orders").css({"display" : "none"});
    $("#shops").css({"display" : "none"});
}
function shops() {
    $("#shops").css({"display" : "block"});
    $("#products").css({"display" : "none"});
    $("#stats").css({"display" : "none"});
    $("#clients").css({"display" : "none"});
    $("#orders").css({"display" : "none"});
}
// function changeProduct() {
//     $("#edited_data_order").css({"display" : "block"});
// }






function form_submit() {
    document.getElementById("search_form").submit();
}

// function edit_submit() {
//     document.getElementById("edited_order").submit();
// }

function time_submit() {
    document.getElementById("time_form").submit();
}

function form2_submit() {
    document.getElementById("edited_data_order").submit();
}

// function form3_submit() {
//     document.getElementById("add_adr").submit();
// }

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

function add(id) {
    $.ajax({
        url : '/basket',
        type: 'GET',
        dataType: 'json',
        data : ({
            item: id
        }),
        success: function (result) {
            $('#basketCount').text(result);
            $('#basketCount2').text(result);
        }
    });
}

var footer = $('.footer'),
    pageContainer = $('.page-container'),
    fixClass = 'navbar-fixed-bottom';

function stickyFooter() {
    var windowHeight  = $(window).height(),
        contentHeight = pageContainer.height(),
        footerHeight  = footer.height();
    footer.removeClass(fixClass);
    if (contentHeight <= windowHeight-footerHeight){
        footer.addClass(fixClass);
    }
}
stickyFooter();
$(window).resize(function () {
    stickyFooter();
});

