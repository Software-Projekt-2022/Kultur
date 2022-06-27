package com.api.kultur.Event;


import com.google.gson.JsonObject;

public class DailyCulturalEvents extends Event {

    public DailyCulturalEvents() {
        super("daily_cultural_events");
    }

    @Override
    public JsonObject addProperty() {
        JsonObject content = new JsonObject();
        content.addProperty("message", "Hello World!");
        return content;
    }
}
