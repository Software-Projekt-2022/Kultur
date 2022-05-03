package com.api.database;

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

    public static Club getClubById(int id) {
        return null;
    }
}
