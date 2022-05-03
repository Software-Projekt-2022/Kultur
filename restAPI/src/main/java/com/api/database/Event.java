package com.api.database;

import java.time.LocalDateTime;

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
}

