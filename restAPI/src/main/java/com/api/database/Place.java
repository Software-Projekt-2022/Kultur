package com.api.database;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Place implements Serializable {

    private static final long serialVersionUID = 1L;

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
                    "INSERT INTO coordinates (latitude, longitude) " +
                    "VALUES ('" + latitude + "', '" + longitude + "') " +
                            "RETURNING coordinates_id");

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

    public void setId(int id) {
        this.id = id;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setRatingCount(String ratingCount) {
        this.ratingCount = ratingCount;
    }

    public void setRatingAvg(String ratingAvg) {
        this.ratingAvg = ratingAvg;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCategory() {
        return category;
    }

    public String getDescription() {
        return description;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getCountry() {
        return country;
    }

    public String getHours() {
        return hours;
    }

    public String getImage() {
        return image;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getPhone() {
        return phone;
    }

    public String getPrice() {
        return price;
    }

    public String getState() {
        return state;
    }

    public String getRating() {
        return rating;
    }

    public String getRatingAvg() {
        return ratingAvg;
    }

    public String getWebsite() {
        return website;
    }

    public String getRatingCount() {
        return ratingCount;
    }

    public String getZip() {
        return zip;
    }
}
