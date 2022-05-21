package com.api.Event;

import com.google.gson.JsonObject;

public class AdminMessageBroadcastEvent extends Event {

    public AdminMessageBroadcastEvent() {
        super("admin_message_broadcast");
    }

    @Override
    public JsonObject addProperty() {
        JsonObject content = new JsonObject();
        content.addProperty("message", "Hello World!");
        return content;
    }
}
