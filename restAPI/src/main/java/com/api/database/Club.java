package com.api.database;

import jakarta.xml.bind.annotation.XmlRootElement;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;

@XmlRootElement
public class Club implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String description;
    private String place;
    private String logoPath;
    private String cost;
    private String category;
    private ArrayList<Chat> chat;
    private ArrayList<Event> meetings;

    public Club() {
    }

    public Club(String name, String description, String place, String logoPath, String cost, String category) {
        this.name = name;
        this.description = description;
        this.place = place;
        this.logoPath = logoPath;
        this.cost = cost;
        this.category = category;
    }

    public static Object getClubsByName(String name) {
        Connection conn = Database.getDBConnection();
        ArrayList<Club> clubs = new ArrayList<>();
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM club WHERE name = '" + name + "'");
            iterateResultSet(clubs, rs);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clubs;
    }

    public void addToDatabase() {
        Connection conn = Database.getDBConnection();
        try {
            Statement statement = conn.createStatement();
            this.id = statement.executeUpdate(
                    "INSERT INTO club (name, description, place, logo_path, cost, category) " +
                    "VALUES ('" + name + "', '" + description + "', '" + place + "', '" + logoPath + "', '" + cost + "', '" + category + "') " +
                            "RETURNING id");
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<Club> getList() {
        Connection conn = Database.getDBConnection();
        ArrayList<Club> clubs = new ArrayList<>();
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM club");
            iterateResultSet(clubs, rs);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clubs;
    }

    public static ArrayList<Club> getClubsByCategory(String category) {
        Connection conn = Database.getDBConnection();
        ArrayList<Club> clubs = new ArrayList<>();
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM club WHERE category = '" + category + "'");
            iterateResultSet(clubs, rs);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clubs;
    }

    private static void iterateResultSet(ArrayList<Club> clubs, ResultSet rs) throws SQLException {
        while (rs.next()) {
            Club club = new Club();
            club.id = rs.getInt("id");
            club.name = rs.getString("name");
            club.description = rs.getString("description");
            club.place = rs.getString("place");
            club.logoPath = rs.getString("logo_path");
            club.cost = rs.getString("cost");
            club.category = rs.getString("category");
            clubs.add(club);
        }
    }

    public static ArrayList<Club> getClubById(int id) {
        Connection conn = Database.getDBConnection();
        ArrayList<Club> clubs = new ArrayList<>();
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM club WHERE id = " + id);
            iterateResultSet(clubs, rs);
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return clubs;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setChat(ArrayList<Chat> chat) {
        this.chat = chat;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    public void setMeetings(ArrayList<Event> meetings) {
        this.meetings = meetings;
    }


    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getCost() {
        return cost;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public String getPlace() {
        return place;
    }
}
