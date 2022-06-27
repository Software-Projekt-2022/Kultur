package com.api.kultur.Event;

import com.google.gson.JsonObject;

import java.time.Instant;

public abstract class Event {

    final String event_id = MessageBus.MICROSERVICE_PREFIX + System.currentTimeMillis();
    final String event_type;
    final String event_origin = MessageBus.MICROSERVICE_NAME;
    final String event_time = Instant.now().toString();

    public Event(String event_type) {
        this.event_type = event_type;
    }

    abstract JsonObject addProperty();

    @Override
    public String toString()
    {
        JsonObject root = new JsonObject();
        root.addProperty("event_id", event_id);
        root.addProperty("event_type", event_type);
        root.addProperty("event_origin", event_origin);
        root.addProperty("event_time", event_time);
        JsonObject content = addProperty();
        root.add("content", content);
        return root.toString();
    }
}
