package com.api.restapi;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/Club")
public class ClubResource {


    @GET
    @Produces("application/json")
    public String get() {
        return "Hello, World!";
    }

    @GET
    @Produces("application/json")
    public Response getSpecific(int id) {

        Response.ResponseBuilder builder = Response.ok("Hello, World!");
        return builder.build();
    }
}