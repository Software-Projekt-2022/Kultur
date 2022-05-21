package com.api.restapi;

import com.api.database.Book;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/Book")
public class BookResource {


    @GET
    @Produces("application/json")
    public Response get() {
        Response.ResponseBuilder builder = Response.ok(Book.getList());
        return builder.build();
    }

    @Path("/id/{id}")
    @GET
    @Produces("application/json")
    public Response getSpecific(@PathParam("id") int id) {
        Response.ResponseBuilder builder = Response.ok(Book.getById(id));
        return builder.build();
    }

    @Path("/title/{title}")
    @GET
    @Produces("application/json")
    public Response getTitle(@PathParam("title") String title) {
        Response.ResponseBuilder builder = Response.ok(Book.getBooksByTitle(title));
        return builder.build();
    }

    @Path("/author/{author}")
    @GET
    @Produces("application/json")
    public Response getAuthor(@PathParam("author") String author) {
        Response.ResponseBuilder builder = Response.ok(Book.getBooksByAuthor(author));
        return builder.build();
    }

    @Path("/category/{category}")
    @GET
    @Produces("application/json")
    public Response getCategory(@PathParam("category") String category) {
        Response.ResponseBuilder builder = Response.ok(Book.getBooksByCategory(category));
        return builder.build();
    }

    @Path("/publisher/{publisher}")
    @GET
    @Produces("application/json")
    public Response getPublisher(@PathParam("publisher") String publisher) {
        Response.ResponseBuilder builder = Response.ok(Book.getBooksByPublisher(publisher));
        return builder.build();
    }

    @Path("/year/{year}")
    @GET
    @Produces("application/json")
    public Response getYear(@PathParam("year") int year) {
        Response.ResponseBuilder builder = Response.ok(Book.getBooksByYear(year));
        return builder.build();
    }

    @Path("/language/{language}")
    @GET
    @Produces("application/json")
    public Response getLanguage(@PathParam("language") String language) {
        Response.ResponseBuilder builder = Response.ok(Book.getBooksByLanguage(language));
        return builder.build();
    }

    @Path("/new")
    @GET
    @Produces("application/json")
    public Response getNew() {
        Response.ResponseBuilder builder = Response.ok(Book.getNew());
        return builder.build();
    }


}