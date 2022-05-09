package com.api.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Event {

    private int id;
    private String name;
    private String description;
    private LocalDateTime date;
    private String place;
    private String category;
    private int max_people;
    private Chat chat;

    public Event() {
    }

    public Event(String name, String description, LocalDateTime date, String place, String category, int max_people) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.place = place;
        this.category = category;
        this.max_people = max_people;
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
    public LocalDateTime getDate() {
        return date;
    }
    public Chat getChat() {
        return chat;
    }
    public int getMaxPeople() {
        return max_people;
    }

    public int addToDatabase() {
        Connection conn = Database.getDBConnection();
        try {
            this.id = conn.createStatement().executeUpdate(
                    "INSERT INTO event (name, description, date, place, category, max_people) " +
                    "VALUES ('" + name + "', '" + description + "', '" + date + "', '" + place + "', '" + category + "', '" + max_people + "') " +
                            "RETURNING id");

            conn.close();
        } catch (Exception e) {
            return -1;
        }
        return 0;
    }

    public static Event getEventById(int id) {
        Connection conn = Database.getDBConnection();
        try {
            Event event = new Event();
            event.id = id;
            event.name = conn.createStatement().executeQuery("SELECT name FROM event WHERE id = " + id).getString("name");
            event.description = conn.createStatement().executeQuery("SELECT description FROM event WHERE id = " + id).getString("description");
            event.date = conn.createStatement().executeQuery("SELECT date FROM event WHERE id = " + id).getTimestamp("date").toLocalDateTime();
            event.place = conn.createStatement().executeQuery("SELECT place FROM event WHERE id = " + id).getString("place");
            event.category = conn.createStatement().executeQuery("SELECT category FROM event WHERE id = " + id).getString("category");
            event.max_people = conn.createStatement().executeQuery("SELECT max_people FROM event WHERE id = " + id).getInt("max_people");
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
            event.date = rs.getTimestamp("date").toLocalDateTime();
            event.description = rs.getString("description");
            event.date = rs.getTimestamp("date").toLocalDateTime();
            event.place = rs.getString("place");
            event.category = rs.getString("category");
            event.max_people = rs.getInt("max_people");
            events.add(event);
        }
    }
}

