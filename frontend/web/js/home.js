function getNewEvents(){
    $.ajax({
        url: api + "Event/new",
        method: "GET",
        dataType: "json",
        success: function (data) {
            console.log(data);
        },
        error: function (data) {
            console.log(data);
        }
    });
}

getNewEvents();