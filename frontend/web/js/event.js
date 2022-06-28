function getEvents(){
    $.ajax({
        url: api + "Event",
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

$.ajax({
    url: api + "Event",
    method: "POST",
    dataType: "json",
    headers: {
        'Content-Type': 'application/json'
    },
    data: '{\
"id": 1,\
"place": {\
"id":0,\
"longitude": 52.52,\
"latitude": 8.93\
},\
"date": "2022-06-28T12:11:04.479Z",\
"name": "Familienfest",\
"description": "richtig viel Spaß",\
"category": "Folksfest",\
"maxPeople": 5000\
}',
    success: function (data) {
        console.log(data);
    },
    error: function (data) {
        console.log(data);
    }
});

$.ajax({
    url: api + "Event",
    method: "POST",
    dataType: "json",
    headers: {
        'Content-Type': 'application/json'
    },
    data: '{\
"id": 2,\
"place": {\
"id":0,\
"longitude": 52.296,\
"latitude": 8.905\
},\
"date": "2022-06-28T12:11:04.479Z",\
"name": "Vorstandssitzung",\
"description": "richtig viel Spaß",\
"category": "Sitzung",\
"maxPeople": 30\
}',
    success: function (data) {
        console.log(data);
    },
    error: function (data) {
        console.log(data);
    }
});

getEvents();