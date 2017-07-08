$(document).ready(function () {
    $("#btn-load-data").click(function () {
        ajaxCall();
    });
});

function ajaxCall() {
    $.ajax({
        url: "/data",
        dataType: 'json',
        timeout: 30000,
        success: function (data) {
            console.log("SUCCESS: ", data);
            prepareData(data);
        },
        error: function (e) {
            console.log("ERROR: ", e);
            prepareData(e);
        },
        done: function (e) {
            console.log("DONE");
        }
    });
}

function prepareData(data) {
    var input = "<h4>Data from Dynatrace endpoint:</h4><pre>" + JSON.stringify(data.inputData) + "</pre>";
    var output = "<h4>Prime numbers:</h4><pre>" + JSON.stringify(data.outputData) + "</pre>";
    $('#inputData').html(input);
    $('#outputData').html(output);
}

