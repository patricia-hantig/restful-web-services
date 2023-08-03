package com.demo.JSON.Jackson;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/JacksonResource")
public class JacksonResource {

    @GET
    @Path("/getUser")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser() {
        return new User(1, "John");
    }

    @GET
    @Path("/getUserBean")
    @Produces(MediaType.APPLICATION_JSON)
    public UserBean getUserBean() {
        return new UserBean(1, "Mike");
    }
}
