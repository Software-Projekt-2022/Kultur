package com.api.restapi;

import com.api.database.Place;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/Place")
public class PlaceResource {

    @GET
    @Produces("application/json")
    public Response get() {
        Response.ResponseBuilder builder = Response.ok(Place.getList());
        return builder.build();
    }

    @Path("/id/{id}")
    @GET
    @Produces("application/json")
    public Response getSpecific(@PathParam("id") int id) {
        Response.ResponseBuilder builder = Response.ok(Place.getById(id));
        return builder.build();
    }

    @Path("/name/{name}")
    @GET
    @Produces("application/json")
    public Response getName(@PathParam("name") String name) {
        Response.ResponseBuilder builder = Response.ok(Place.getByName(name));
        return builder.build();
    }

    @Path("/category/{category}")
    @GET
    @Produces("application/json")
    public Response getCategory(@PathParam("category") String category) {
        Response.ResponseBuilder builder = Response.ok(Place.getByCategory(category));
        return builder.build();
    }

    @Path("/address/{address}")
    @GET
    @Produces("application/json")
    public Response getAddress(@PathParam("address") String address) {
        Response.ResponseBuilder builder = Response.ok(Place.getByAddress(address));
        return builder.build();
    }
}
