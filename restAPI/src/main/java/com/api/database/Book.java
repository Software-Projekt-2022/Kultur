package com.api.database;

import java.sql.Connection;
import java.sql.Date;
import java.time.LocalDateTime;

public class Book {

    private int id;
    private String name;
    private String description;
    private String author;
    private String publisher;
    private String category;
    private String language;
    private Date releaseDate;
    private BorrowStatus status;
    //private User borrowedBy;

    public Book() {
    }

    public Book(String name, String description, String author, String publisher, String category, String language, Date releaseDate, BorrowStatus status) {
        this.name = name;
        this.description = description;
        this.author = author;
        this.publisher = publisher;
        this.category = category;
        this.language = language;
        this.releaseDate = releaseDate;
        this.status = status;
    }

    public void addToDatabase() {
        Connection conn = Database.getDBConnection();
        try {
            this.id = conn.createStatement().executeUpdate(
                    "INSERT INTO book (name, description, author, publisher, category, language, release_date, status) " +
                    "VALUES ('" + name + "', '" + description + "', '" + author + "', '" + publisher + "', '" + category + "', '" + language + "', '" + releaseDate + "', '" + status + "') " +
                            "RETURNING id");

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateStatus(BorrowStatus status) {
        Connection conn = Database.getDBConnection();
        try {
            conn.createStatement().executeUpdate("UPDATE book SET status = '" + status.getId() + "' WHERE id = " + id);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Book getById(int id){
        Connection conn = Database.getDBConnection();
        try {
            Book book = new Book();
            book.id = id;
            book.name = conn.createStatement().executeQuery("SELECT name FROM book WHERE id = " + id).getString("name");
            book.description = conn.createStatement().executeQuery("SELECT description FROM book WHERE id = " + id).getString("description");
            book.author = conn.createStatement().executeQuery("SELECT author FROM book WHERE id = " + id).getString("author");
            book.publisher = conn.createStatement().executeQuery("SELECT publisher FROM book WHERE id = " + id).getString("publisher");
            book.category = conn.createStatement().executeQuery("SELECT category FROM book WHERE id = " + id).getString("category");
            book.language = conn.createStatement().executeQuery("SELECT language FROM book WHERE id = " + id).getString("language");
            book.releaseDate = conn.createStatement().executeQuery("SELECT release_date FROM book WHERE id = " + id).getDate("release_date");
            book.status = BorrowStatus.getById(conn.createStatement().executeQuery("SELECT status FROM book WHERE id = " + id).getInt("status"));
            conn.close();
            return book;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
