package com.api.restapi;

import com.api.database.Chat;
import com.api.database.Message;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/Chat")
public class ChatResource {


    @GET
    @Produces("application/json")
    public Response get() {
        Response.ResponseBuilder builder = Response.ok(Chat.getList());
        return builder.build();
    }

    @Path("/id/{id}")
    @GET
    @Produces("application/json")
    public Response getSpecific(@PathParam("id") int id) {
        Response.ResponseBuilder builder = Response.ok(Chat.getById(id));
        return builder.build();
    }

    @Path("/messages/{chatId}")
    @GET
    @Produces("application/json")
    public Response getMessages(@PathParam("chatId") int chatId) {
        Response.ResponseBuilder builder = Response.ok(Message.getMessagesByChatId(chatId));
        return builder.build();
    }
}