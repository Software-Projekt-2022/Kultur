var map = L.map('map').setView([52.29644,8.90491], 19);

L.tileLayer('https://api.mapbox.com/styles/v1/{id}/tiles/{z}/{x}/{y}?access_token=pk.eyJ1IjoibWFwYm94IiwiYSI6ImNpejY4NXVycTA2emYycXBndHRqcmZ3N3gifQ.rJcFIG214AriISLbB6B5aw', {
    maxZoom: 18,
    attribution: 'Map data &copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors, ' +
			'Imagery © <a href="https://www.mapbox.com/">Mapbox</a>',
    id: 'mapbox/streets-v11',
    tileSize: 512,
    zoomOffset: -1
}).addTo(map);

L.marker([52.29644,8.90491]).addTo(map)
    .bindPopup('Da sind wir!')
    .openPopup();

L.marker([52.296,8.905]).addTo(map)
    .bindPopup('Wichtiger Ort')
    .openPopup();

function getPlaces(){
    /* makes an ajax request to api and gets relevant places */
    $.ajax({
        url: api + "Place",
        method: "GET",
        dataType: "json",
        success: function (data) {
            console.log(data);
            for (let i = 0; i < data.length; i++) {
                L.marker([data[i].latitude,data[i].longitude]).addTo(map)
                    .bindPopup(data[i].name)
                    .openPopup();
            }
        },
        error: function (data) {
            console.log(data);
        }
    });
}