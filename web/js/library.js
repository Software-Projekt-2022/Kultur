function getBooks(){
    $.ajax({
        url: api + "Book",
        method: "GET",
        dataType: "json",
        success: function (data) {
            console.log(data);
            for (let i = 0; i < data.length; i++) {
                //bookListFactory(data[i]);
            }
        },
        error: function (data) {
            console.log(data);
        }
    });
}

function getNewBooks(){
    $.ajax({
        url: api + "Book/new",
        method: "GET",
        dataType: "json",
        success: function (data) {
            console.log(data);
            for (let i = 0; i < data.length; i++) {
                bookListFactory(data[i]);
            }
        },
        error: function (data) {
            console.log(data);
        }
    });
}

function bookListFactory(data){
    /* creates book elements in the html page in the div with id bookList */
    let book = document.createElement("div");
    book.className = "book row";
    book.id = "book-" + data.id;
    book.innerHTML = `<div class="book-name">${data.name}</div>
    <div class="book-description">${data.description}</div>`;
    document.getElementById("bookList").appendChild(book);
}