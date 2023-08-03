package com.demo.JSON.MOXy;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/MOXy/userResource")
public class UserResource {

    @GET
    @Path("/getUser")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser() {
        return new User(100, "John");
    }

    @POST
    @Path("/postUser")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public User postUser(User user) {
        return user;
    }
}
