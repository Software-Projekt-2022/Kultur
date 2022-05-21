package com.api.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Place {

    private int id;
    private String name;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String country;
    private String phone;
    private String website;
    private String latitude;
    private String longitude;
    private String category;
    private String image;
    private String description;
    private String hours;
    private String price;
    private String rating;
    private String ratingCount;
    private String ratingAvg;

    public Place() {
    }

    public Place(String name, String address, String city, String state, String zip, String country, String phone, String website, String latitude, String longitude, String category, String image, String description, String hours, String price, String rating, String ratingCount, String ratingAvg) {
        this.name = name;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.country = country;
        this.phone = phone;
        this.website = website;
        this.latitude = latitude;
        this.longitude = longitude;
        this.category = category;
        this.image = image;
        this.description = description;
        this.hours = hours;
        this.price = price;
        this.rating = rating;
        this.ratingCount = ratingCount;
        this.ratingAvg = ratingAvg;
    }

    public void addToDatabase() {
        Connection conn = Database.getDBConnection();
        try {
            this.id = conn.createStatement().executeUpdate(
                    "INSERT INTO place (name, address, city, state, zip, country, phone, website, latitude, longitude, category, image, description, hours, price, rating, rating_count, rating_avg) " +
                    "VALUES ('" + name + "', '" + address + "', '" + city + "', '" + state + "', '" + zip + "', '" + country + "', '" + phone + "', '" + website + "', '" + latitude + "', '" + longitude + "', '" + category + "', '" + image + "', '" + description + "', '" + hours + "', '" + price + "', '" + rating + "', '" + ratingCount + "', '" + ratingAvg + "') " +
                            "RETURNING id");

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * gets all places from the database
     * @return an arraylist of all places
     */
    public static ArrayList<Place> getList() {
        ArrayList<Place> places = new ArrayList<>();
        Connection conn = Database.getDBConnection();
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM place");
            while (rs.next()) {
                places.add(new Place(rs.getString("name"), rs.getString("address"), rs.getString("city"), rs.getString("state"), rs.getString("zip"), rs.getString("country"), rs.getString("phone"), rs.getString("website"), rs.getString("latitude"), rs.getString("longitude"), rs.getString("category"), rs.getString("image"), rs.getString("description"), rs.getString("hours"), rs.getString("price"), rs.getString("rating"), rs.getString("rating_count"), rs.getString("rating_avg")));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return places;
    }

    /**
     * gets a place from the database by id
     * @param id the id of the place
     * @return the place
     */
    public static Place getById(int id) {
        Place place = null;
        Connection conn = Database.getDBConnection();
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM place WHERE id = " + id);
            while (rs.next()) {
                place = new Place(rs.getString("name"), rs.getString("address"), rs.getString("city"), rs.getString("state"), rs.getString("zip"), rs.getString("country"), rs.getString("phone"), rs.getString("website"), rs.getString("latitude"), rs.getString("longitude"), rs.getString("category"), rs.getString("image"), rs.getString("description"), rs.getString("hours"), rs.getString("price"), rs.getString("rating"), rs.getString("rating_count"), rs.getString("rating_avg"));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return place;
    }

    /**
     * gets an ArrayList of places from the database by name
     * @param name the name of the place
     * @return the place
     */
    public static ArrayList<Place> getByName(String name) {
        ArrayList<Place> places = new ArrayList<>();
        Connection conn = Database.getDBConnection();
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM place WHERE name = '" + name + "'");
            while (rs.next()) {
                places.add(new Place(rs.getString("name"), rs.getString("address"), rs.getString("city"), rs.getString("state"), rs.getString("zip"), rs.getString("country"), rs.getString("phone"), rs.getString("website"), rs.getString("latitude"), rs.getString("longitude"), rs.getString("category"), rs.getString("image"), rs.getString("description"), rs.getString("hours"), rs.getString("price"), rs.getString("rating"), rs.getString("rating_count"), rs.getString("rating_avg")));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return places;
    }

    /**
     * gets a place from the database by address
     * @param address the address of the place
     * @return the place
     */
    public static Place getByAddress(String address) {
        Place place = null;
        Connection conn = Database.getDBConnection();
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM place WHERE address = '" + address + "'");
            while (rs.next()) {
                place = new Place(rs.getString("name"), rs.getString("address"), rs.getString("city"), rs.getString("state"), rs.getString("zip"), rs.getString("country"), rs.getString("phone"), rs.getString("website"), rs.getString("latitude"), rs.getString("longitude"), rs.getString("category"), rs.getString("image"), rs.getString("description"), rs.getString("hours"), rs.getString("price"), rs.getString("rating"), rs.getString("rating_count"), rs.getString("rating_avg"));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return place;
    }

    /**
     * gets a place from the database by city
     * @param city the city of the place
     * @return the place
     */
    public static Place getByCity(String city) {
        Place place = null;
        Connection conn = Database.getDBConnection();
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM place WHERE city = '" + city + "'");
            while (rs.next()) {
                place = new Place(rs.getString("name"), rs.getString("address"), rs.getString("city"), rs.getString("state"), rs.getString("zip"), rs.getString("country"), rs.getString("phone"), rs.getString("website"), rs.getString("latitude"), rs.getString("longitude"), rs.getString("category"), rs.getString("image"), rs.getString("description"), rs.getString("hours"), rs.getString("price"), rs.getString("rating"), rs.getString("rating_count"), rs.getString("rating_avg"));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return place;
    }

    /**
     * gets an ArrayList with all places from the database by openingHours
     * @param openingHours the hours of the place
     * @return the place
     */
    public static ArrayList<Place> getByOpeningHours(String openingHours) {
        ArrayList<Place> places = new ArrayList<>();
        Connection conn = Database.getDBConnection();
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM place WHERE hours = '" + openingHours + "'");
            while (rs.next()) {
                places.add(new Place(rs.getString("name"), rs.getString("address"), rs.getString("city"), rs.getString("state"), rs.getString("zip"), rs.getString("country"), rs.getString("phone"), rs.getString("website"), rs.getString("latitude"), rs.getString("longitude"), rs.getString("category"), rs.getString("image"), rs.getString("description"), rs.getString("hours"), rs.getString("price"), rs.getString("rating"), rs.getString("rating_count"), rs.getString("rating_avg")));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return places;
    }

    /**
     * gets an ArrayList with all places from the database by category
     * @param category the category of the place
     * @return the place
     */
    public static ArrayList<Place> getByCategory(String category) {
        ArrayList<Place> places = new ArrayList<>();
        Connection conn = Database.getDBConnection();
        try {
            ResultSet rs = conn.createStatement().executeQuery("SELECT * FROM place WHERE category = '" + category + "'");
            while (rs.next()) {
                places.add(new Place(rs.getString("name"), rs.getString("address"), rs.getString("city"), rs.getString("state"), rs.getString("zip"), rs.getString("country"), rs.getString("phone"), rs.getString("website"), rs.getString("latitude"), rs.getString("longitude"), rs.getString("category"), rs.getString("image"), rs.getString("description"), rs.getString("hours"), rs.getString("price"), rs.getString("rating"), rs.getString("rating_count"), rs.getString("rating_avg")));
            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return places;
    }
}
