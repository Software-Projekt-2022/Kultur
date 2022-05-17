package com.api.restapi;

import com.api.database.Club;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/Club")
public class ClubResource {

    @GET
    @Produces("application/json")
    public Response get() {
        Response.ResponseBuilder builder = Response.ok(Club.getList());
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
}