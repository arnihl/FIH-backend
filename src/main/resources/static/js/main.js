//tvö functions mouseenter, mouseleaves. 

$(document).ready(function() {

    var hoveredElement = null;

    // jQuery methods go here...
    // Birta umsoknir dropdown á hoveri
    $("#FIH-umsoknir-btn").hover(function() {
        if (hoveredElement) { noDisplayElement(hoveredElement); }
        $(this).css("background-color", "pink");
        $("#dropdown-umsoknir").removeClass("d-none").addClass("d-block");
        $("#FIH-nav").removeClass("FIH-nav").addClass("FIH-nav-vis");
        hoveredElement = "#dropdown-umsoknir";
        navVisible(hoveredElement);
    }, function() {
        $(this).css("background-color", "white");
    });
    // Birta um skólann dropdown á hoveri
    $("#FIH-umskolann-btn").hover(function() {
            if (hoveredElement) { noDisplayElement(hoveredElement); }
            $(this).css("background-color", "pink");
            $("#dropdown-umskolann").removeClass("d-none").addClass("d-block");
            $("#FIH-nav").removeClass("FIH-nav").addClass("FIH-nav-vis");
            hoveredElement = "#dropdown-umskolann";
            navVisible(hoveredElement);
        },
        function() {
            $(this).css("background-color", "white");
        });

    // Birta Á döfinni dropdown á hoveri
    $("#FIH-adofinni-btn").hover(function() {
            if (hoveredElement) { noDisplayElement(hoveredElement); }
            $(this).css("background-color", "pink");
            $("#dropdown-adofinni").removeClass("d-none").addClass("d-block");
            $("#FIH-nav").removeClass("FIH-nav").addClass("FIH-nav-vis");
            hoveredElement = "#dropdown-adofinni";
            navVisible(hoveredElement);
        },
        function() {
            $(this).css("background-color", "white");
        });

    // Birta Á döfinni dropdown á hoveri
    $("#FIH-namid-btn").hover(function() {
            if (hoveredElement) { noDisplayElement(hoveredElement); }
            $(this).css("background-color", "pink");
            $("#dropdown-namid").removeClass("d-none").addClass("d-block");
            $("#FIH-nav").removeClass("FIH-nav").addClass("FIH-nav-vis");
            hoveredElement = "#dropdown-namid";
            navVisible(hoveredElement);
        },
        function() {
            $(this).css("background-color", "white");
        });

    // Birta Hafa samband dropdown á hoveri
    $("#FIH-hafasamband-btn").hover(function() {
            if (hoveredElement) { noDisplayElement(hoveredElement); }
            $(this).css("background-color", "pink");
            $("#dropdown-hafasamband").removeClass("d-none").addClass("d-block");
            $("#FIH-nav").removeClass("FIH-nav").addClass("FIH-nav-vis");
            hoveredElement = "#dropdown-hafasamband";
            navVisible(hoveredElement);
        },
        function() {
            $(this).css("background-color", "white");

        });

    $("#FIH-algengarspurningar-btn").hover(function() {
            if (hoveredElement) { noDisplayElement(hoveredElement); }
            $(this).css("background-color", "pink");
            $("#dropdown-algengar-spurningar").removeClass("d-none").addClass("d-block");
            $("#FIH-nav").removeClass("FIH-nav").addClass("FIH-nav-vis");
            hoveredElement = "#dropdown-algengar-spurningar";
            navVisible(hoveredElement);
        },
        function() {
            $(this).css("background-color", "white");

        });

});

function navVisible(element) {
    $(".FIH-nav-vis").hover(function() {
            //ekkert
        },
        function() {
            $(this).removeClass("FIH-nav-vis").addClass("FIH-nav");

            noDisplayElement(element);
        });
}

function noDisplayElement(element) {
    if (element) {
        $(element).removeClass("d-block").addClass("d-none");
    }
}