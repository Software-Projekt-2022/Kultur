package com.api.kultur.controller;

import com.api.kultur.model.Book;
import com.api.kultur.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/Book")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookController {
    public final BookService bookService;

    @GetMapping(path = "", name = "getList", produces = "application/json")
    public List<Book> getList() {
        return bookService.getList();
    }

    @PostMapping(path = "", name = "create", consumes = "application/json", produces = "application/json")
    public Book create(@RequestBody Book book) {
        return bookService.create(book);
    }

    @GetMapping(path = "id/{id}", name = "getById", produces = "application/json")
    public Book getById(@PathVariable int id) {
        return bookService.getById(id);
    }

    @GetMapping(path = "title/{title}", name = "getByTitle", produces = "application/json")
    public Book getByTitle(@PathVariable String title) {
        return bookService.getByTitle(title);
    }

    @GetMapping(path = "author/{author}", name = "getByAuthor", produces = "application/json")
    public List<Book> getByAuthor(@PathVariable String author) {
        return bookService.getByAuthor(author);
    }

    @GetMapping(path = "new", name = "getNew", produces = "application/json")
    public List<Book> getNew() {
        return bookService.getNew();
    }
}
