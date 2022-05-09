package com.api.restapi;

import com.api.database.Event;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.ArrayList;

@Path("/Event")
public class EventResource {

    @GET
    @Produces("application/json")
    public Response get() {
        ArrayList<Event> list = Event.getList();
        Response.ResponseBuilder builder = Response.ok(list);
        return builder.build();
    }

    @Path("/id/{id}")
    @GET
    @Produces("application/json")
    public Response getSpecific(@PathParam("id") int id) {
        Response.ResponseBuilder builder = Response.ok(Event.getEventById(id));
        return builder.build();
    }

    @Path("/category/{category}")
    @GET
    @Produces("application/json")
    public Response getCategory(@PathParam("category") String category) {
        Response.ResponseBuilder builder = Response.ok(Event.getEventsByCategory(category));
        return builder.build();
    }

    @Path("/date/{date}")
    @GET
    @Produces("application/json")
    public Response getDate(@PathParam("date") String date) {
        Response.ResponseBuilder builder = Response.ok(Event.getEventsByDate(LocalDateTime.parse(date)));
        return builder.build();
    }

    @Path("/place/{place}")
    @GET
    @Produces("application/json")
    public Response getPlace(@PathParam("place") String place) {
        Response.ResponseBuilder builder = Response.ok(Event.getEventsByPlace(place));
        return builder.build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response addEvent(Event event) {
        Response.ResponseBuilder builder;
        if (event.addToDatabase() == 0)
            builder = Response.ok("added to Database");
        else {
            builder = Response.serverError();
        }
        return builder.build();
    }
}