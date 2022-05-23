package com.api.database;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Event implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private String date;
    private String place;
    private String category;
    private int maxPeople;
    private Chat chat;


    public Event() {
    }

    public Event(String name, String description, String date, String place, String category, int max_people) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.place = place;
        this.category = category;
        this.maxPeople = max_people;
    }

    public int addToDatabase() {
        Connection conn = Database.getDBConnection();
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(
                    "INSERT INTO event (name, description, date, place, category, max_people) " +
                    "VALUES ('" + name + "', '" + description + "', '" + LocalDateTime.parse(date) + "', '" + place + "', '" + category + "', '" + maxPeople + "') " +
                            "RETURNING id");
            if (rs.next()) {
                this.id = rs.getInt("id");
            }
            conn.close();
            return this.id;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1;
    }

    public static Event getEventById(int id) {
        Connection conn = Database.getDBConnection();
        try {
            Event event = new Event();
            event.id = id;
            event.name = conn.createStatement().executeQuery("SELECT name FROM event WHERE id = " + id).getString("name");
            event.description = conn.createStatement().executeQuery("SELECT description FROM event WHERE id = " + id).getString("description");
            event.date = conn.createStatement().executeQuery("SELECT date FROM event WHERE id = " + id).getTimestamp("date").toString();
            event.place = conn.createStatement().executeQuery("SELECT place FROM event WHERE id = " + id).getString("place");
            event.category = conn.createStatement().executeQuery("SELECT category FROM event WHERE id = " + id).getString("category");
            event.maxPeople = conn.createStatement().executeQuery("SELECT max_people FROM event WHERE id = " + id).getInt("max_people");
            conn.close();
            return event;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Event> getEventsByCategory(String category) {
        Connection conn = Database.getDBConnection();
        ArrayList<Event> events = new ArrayList<>();
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM event WHERE category = '" + category + "'");
            iterateResultSet(events, rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return events;
    }

    public static ArrayList<Event> getEventsByDate(LocalDateTime date) {
        Connection conn = Database.getDBConnection();
        ArrayList<Event> events = new ArrayList<>();
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM event WHERE date = '" + date + "'");
            iterateResultSet(events, rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return events;
    }

    public static ArrayList<Event> getEventsByPlace(String place) {
        Connection conn = Database.getDBConnection();
        ArrayList<Event> events = new ArrayList<>();
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM event WHERE place = '" + place + "'");
            iterateResultSet(events, rs);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return events;
    }

    public static ArrayList<Event> getList() {
        Connection conn = Database.getDBConnection();
        ArrayList<Event> events = new ArrayList<>();
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM event");
            iterateResultSet(events, rs);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return events;
    }

    private static void iterateResultSet(ArrayList<Event> events, ResultSet rs) throws SQLException {
        while (rs.next()) {
            Event event = new Event();
            event.id = rs.getInt("id");
            event.name = rs.getString("name");
            event.date = rs.getTimestamp("date").toString();
            event.description = rs.getString("description");
            event.place = rs.getString("place");
            event.category = rs.getString("category");
            event.maxPeople = rs.getInt("max_people");
            events.add(event);
        }
    }

    public void setId(int id) {
        this.id = id;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setChat(Chat chat) {
        this.chat = chat;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setMaxPeople(int maxPeople) {
        this.maxPeople = maxPeople;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPlace(String place) {
        this.place = place;
    }

    public String getId() {
        return String.valueOf(id);
    }
    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String getCategory() {
        return category;
    }
    public String getDate() {
        return date;
    }
    public Chat getChat() {
        return chat;
    }
    public int getMaxPeople() {
        return maxPeople;
    }
}

