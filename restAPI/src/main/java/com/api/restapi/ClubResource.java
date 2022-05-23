package com.api.restapi;

import com.api.database.Club;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

@Path("/Club")
public class ClubResource {

    @GET
    @Produces("application/json")
    public Response get() {
        ArrayList<Club> list = Club.getList();
        Response.ResponseBuilder builder = Response.ok(list);
        return builder.build();
    }

    @Path("/id/{id}")
    @GET
    @Produces("application/json")
    public Response getSpecific(@PathParam("id") int id) {

        Response.ResponseBuilder builder = Response.ok(Club.getClubById(id));
        return builder.build();
    }

    @Path("/name/{name}")
    @GET
    @Produces("application/json")
    public Response getName(@PathParam("name") String name) {
        Response.ResponseBuilder builder = Response.ok(Club.getClubsByName(name));
        return builder.build();
    }

    @Path("/category/{category}")
    @GET
    @Produces("application/json")
    public Response getCategory(@PathParam("category") String category) {
        Response.ResponseBuilder builder = Response.ok(Club.getClubsByCategory(category));
        return builder.build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response post(Club club) {
        club.addToDatabase();
        Response.ResponseBuilder rb = Response.ok(club);
        return rb.build();
    }
}