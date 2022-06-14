function getClubs(){
    $.ajax({
        url: api + "Club",
        method: "GET",
        dataType: "json",
        success: function (data) {
            console.log(data);
            for (let i = 0; i < data.length; i++) {
                clubListFactory(data[i]);
            }
        },
        error: function (data) {
            console.log(data);
        }
    });
}

function clubListFactory(data){
    /* creates club elements in the html page in the div with id clubList */
    let club = document.createElement("div");
    club.className = "club row";
    club.id = "club-" + data.id;
    club.innerHTML = `<div class="club-name">${data.name}</div>
    <div class="club-description">${data.description}</div>`;
    document.getElementById("clubList").appendChild(club);
}

getClubs();