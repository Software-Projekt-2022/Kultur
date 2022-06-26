package com.api.kultur.repositories;


import com.api.kultur.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Integer> {


    @Query(value = "SELECT b FROM Book b WHERE b.title = :title")
    Optional<Book> findByTitle(String title);

    @Query(value = "SELECT b FROM Book b WHERE b.author = :author")
    Optional<List<Book>> findByAuthor(String author);

    @Query(value = "SELECT b FROM Book b WHERE b.releaseDate >= :releaseDate")
    List<Book> findByReleaseDate(String releaseDate);

    @Query(value = "SELECT b FROM Book b WHERE b.releaseDate BETWEEN :startDate AND :endDate")
    List<Book> findByReleaseDateBetween(int startDate, int endDate);

    @Query(value = "SELECT b FROM Book b ORDER BY b.releaseDate DESC")
    List<Book> findByReleaseDateDesc();
}
