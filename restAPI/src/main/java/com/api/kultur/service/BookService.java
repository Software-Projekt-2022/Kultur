package com.api.kultur.service;

import com.api.kultur.model.Book;
import com.api.kultur.model.User;
import com.api.kultur.repositories.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class BookService{
    private final BookRepository bookRepository;
    private final UserService userService;

    /**
     * Get all books
     * @return List of books
     */
    public List<Book> getList() {
        return bookRepository.findAll();
    }

    /**
     * creates a new book and checks if borrow status exists, if not it creates a new one
     * @param book the book to create
     * @return the created and saved book
     */
    public Book create(Book book) {
        User user = book.getBorrowedBy();
        if (user.getId() != null && user.getId() > 0) {
            User user1 = userService.getById(user.getId());
            if (user.compareTo(user1) != 0) {
                user = userService.create(user);
            }
        } else {
            user = userService.create(user);
        }
        book.setBorrowedBy(user);
        return bookRepository.save(book);
    }

    /**
     * gets a book by id
     * @param id id of the book
     * @return book with the given id
     */
    public Book getById(int id) {
        Optional<Book> book = bookRepository.findById(id);
        return book.orElse(null);
    }

    /**
     * finds all books with given title
     * @param title title of the book
     * @return list of books with given title
     */
    public Book getByTitle(String title) {
        Optional<Book> book = bookRepository.findByTitle(title);
        return book.orElse(null);
    }

    /**
     * finds all books with given author
     * @param author author of the book
     * @return list of books with given author
     */
    public List<Book> getByAuthor(String author) {
        Optional<List<Book>> book = bookRepository.findByAuthor(author);
        return book.orElse(null);
    }

    /**
     * returns all books ordered by release date descending
     * @return List<Book> all books ordered by release date descending
     */
    public List<Book> getNew() {
        return bookRepository.findByReleaseDateDesc();
    }
}
