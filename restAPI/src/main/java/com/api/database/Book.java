package com.api.database;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Book implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String description;
    private String author;
    private String publisher;
    private String category;
    private String language;
    private String releaseDate;
    private BorrowStatus status;
    //private User borrowedBy;

    public Book() {
    }

    public Book(String title, String description, String author, String publisher, String category, String language, String releaseDate, BorrowStatus status) {
        this.title = title;
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
            Statement statement = conn.createStatement();
            ResultSet rs = statement.executeQuery(
                    "INSERT INTO book (title, description, author, publisher, category, language, releaseDate, status) " +
                    "VALUES ('" + title + "', '" + description + "', '" + author + "', '" + publisher + "', '" + category + "', '" + language + "', '" + LocalDateTime.parse(releaseDate) + "', '" + status + "') " +
                            "RETURNING id");
            if (rs.next()) {
                this.id = rs.getInt("id");
            }
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * sets the status of the book to @param status
     * @param status the status to set the book to
     */
    public void updateStatus(BorrowStatus status) {
        this.status = status;
        Connection conn = Database.getDBConnection();
        try {
            conn.createStatement().executeUpdate("UPDATE book SET status = '" + status.getId() + "' WHERE id = " + id);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Book> getBooksByStatus(BorrowStatus status) {
        ArrayList<Book> books = new ArrayList<>();
        Connection conn = Database.getDBConnection();
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM book WHERE status = " + status.getId());
            iterateResultSet(books, rs);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    private static void iterateResultSet(ArrayList<Book> books, ResultSet rs) throws SQLException {
        while (rs.next()) {
            Book book = new Book();
            book.id = rs.getInt("id");
            book.title = rs.getString("title");
            book.description = rs.getString("description");
            book.author = rs.getString("author");
            book.publisher = rs.getString("publisher");
            book.category = rs.getString("category");
            book.language = rs.getString("language");
            book.releaseDate = rs.getDate("releaseDate").toString();
            book.status = BorrowStatus.getById(rs.getInt("status"));
            books.add(book);
        }
    }

    public static ArrayList<Book> getBooksByAuthor(String author) {
        ArrayList<Book> books = new ArrayList<>();
        Connection conn = Database.getDBConnection();
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM book WHERE author = '" + author + "'");
            iterateResultSet(books, rs);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    public static ArrayList<Book> getBooksByPublisher(String publisher) {
        ArrayList<Book> books = new ArrayList<>();
        Connection conn = Database.getDBConnection();
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM book WHERE publisher = '" + publisher + "'");
            iterateResultSet(books, rs);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    public static ArrayList<Book> getBooksByCategory(String category) {
        ArrayList<Book> books = new ArrayList<>();
        Connection conn = Database.getDBConnection();
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM book WHERE category = '" + category + "'");
            iterateResultSet(books, rs);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    public static ArrayList<Book> getBooksByTitle(String title) {
        ArrayList<Book> books = new ArrayList<>();
        Connection conn = Database.getDBConnection();
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM book WHERE title = '" + title + "'");
            iterateResultSet(books, rs);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    public static ArrayList<Book> getBooksByLanguage(String language) {
        ArrayList<Book> books = new ArrayList<>();
        Connection conn = Database.getDBConnection();
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM book WHERE language = '" + language + "'");
            iterateResultSet(books, rs);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    public static ArrayList<Book> getBooksByReleaseDate(Date releaseDate) {
        ArrayList<Book> books = new ArrayList<>();
        Connection conn = Database.getDBConnection();
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM book WHERE releaseDate = '" + releaseDate + "'");
            iterateResultSet(books, rs);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    public static ArrayList<Book> getBooksByYear(int year) {
        ArrayList<Book> books = new ArrayList<>();
        Connection conn = Database.getDBConnection();
        Timestamp ts = new Timestamp(year, 1, 1, 0, 0, 0, 0);
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM book WHERE date_trunc('year',releaseDate) = '" + ts + "'");
            iterateResultSet(books, rs);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }


    /**
     * @param id the id of the book to be returned
     * @return book if found, null otherwise
     */
    public static Book getById(int id){
        Connection conn = Database.getDBConnection();
        try {
            Book book = new Book();
            book.id = id;
            book.title = conn.createStatement().executeQuery("SELECT title FROM book WHERE id = " + id).getString("name");
            book.description = conn.createStatement().executeQuery("SELECT description FROM book WHERE id = " + id).getString("description");
            book.author = conn.createStatement().executeQuery("SELECT author FROM book WHERE id = " + id).getString("author");
            book.publisher = conn.createStatement().executeQuery("SELECT publisher FROM book WHERE id = " + id).getString("publisher");
            book.category = conn.createStatement().executeQuery("SELECT category FROM book WHERE id = " + id).getString("category");
            book.language = conn.createStatement().executeQuery("SELECT language FROM book WHERE id = " + id).getString("language");
            book.releaseDate = conn.createStatement().executeQuery("SELECT releaseDate FROM book WHERE id = " + id).getDate("releaseDate").toString();
            book.status = BorrowStatus.getById(conn.createStatement().executeQuery("SELECT status FROM book WHERE id = " + id).getInt("status"));
            conn.close();
            return book;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * gets a list of books added in the last week
     * @return list of books
     */
    public static ArrayList<Book> getNew(){
        ArrayList<Book> books = new ArrayList<>();
        Connection conn = Database.getDBConnection();
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM book WHERE date_trunc('week',releaseDate) = date_trunc('week',now())");
            iterateResultSet(books, rs);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return books;
    }

    /**
     * fetches all books from the database
     * @return an arraylist of all books
     */
    public static ArrayList<Book> getList(){
        Connection conn = Database.getDBConnection();
        try {
            ArrayList<Book> books = new ArrayList<>();
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM book");
            iterateResultSet(books, rs);
            conn.close();
            System.out.println("Books fetched");
            System.out.println(books.get(0).title);
            return books;
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setStatus(BorrowStatus status) {
        this.status = status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getLanguage() {
        return language;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getReleaseDate() {
        return releaseDate;
    }
}
