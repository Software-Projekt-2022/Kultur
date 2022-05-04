package com.api.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Club {

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
            this.id = conn.createStatement().executeUpdate(
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

    public static Club getClubById(int id) {
        Connection conn = Database.getDBConnection();
        Club club = null;
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM club WHERE id = " + id);
            while (rs.next()) {
                club = new Club();
                club.id = rs.getInt("id");
                club.name = rs.getString("name");
                club.description = rs.getString("description");
                club.place = rs.getString("place");
                club.logoPath = rs.getString("logo_path");
                club.cost = rs.getString("cost");
                club.category = rs.getString("category");
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return club;
    }
}
