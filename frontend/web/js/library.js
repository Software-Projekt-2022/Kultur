function getBooks(){
    $.ajax({
        url: api + "Book",
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

function getNewBooks(){
    $.ajax({
        url: api + "Book/new",
        method: "GET",
        dataType: "json",
        success: function (data) {
            console.log(data);
            for (let i = 0; i < data.length; i++) {
                bookListFactory(data[i], "newBooks");
            }
        },
        error: function (data) {
            console.log(data);
        }
    });
}

function bookListFactory(data, id = "bookList"){
    /* creates book elements in the html page in the div with id bookList */
    let book = document.createElement("div");
    book.className = "book row";
    book.id = "book-" + data.id;
    book.innerHTML = `<div class="book-name">${data.name}</div>
    <div class="book-description">${data.description}</div>`;
    let list = document.getElementById(id);
    list.innerHTML = "";
    list.appendChild(book);
}

$.ajax({
    url: api + "Book",
    method: "POST",
    dataType: "json",
    headers: {
        'Content-Type': 'application/json'
    },
    data: '{\
        "id": 0,\
        "title": "Es",\
        "author": "Steven King kong",\
        "description": "Es is toll",\
        "status": "Neu",\
        "publisher": "Verlag XY",\
        "category": "Horror",\
        "language": "DE",\
        "releaseDate": "2022-06-28T12:21:40.953Z",\
        "borrowedBy": {\
          "id": 0\
        }\
      }',
    success: function (data) {
        console.log(data);
    },
    error: function (data) {
        console.log(data);
    }
});

getBooks();
getNewBooks();