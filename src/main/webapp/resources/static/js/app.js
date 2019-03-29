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

