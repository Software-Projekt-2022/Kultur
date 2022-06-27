package com.api.kultur.Event;

import com.google.gson.JsonObject;

public class NewUserEvent extends Event {

    public NewUserEvent() {
        super("new_user");
    }

    @Override
    public JsonObject addProperty() {
        JsonObject content = new JsonObject();
        content.addProperty("message", "Hello World!");
        return content;
    }
}
