package com.demo.subresources;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("exampleResource")
public class ExampleResource {

    @GET
    public String getUser() {
        return "User";
    }

    @GET
    @Path("/getUsername")
    public String getUsername() {
        return "Username";
    }

    @GET
    @Path("/subResource")
    public Response usingQueryParam(
            @DefaultValue("0") @QueryParam("id") String id,
            @DefaultValue("No Name") @QueryParam("name") String name,
            @DefaultValue("0") @QueryParam("age") String age,
            @HeaderParam("user-agent") String userAgent) {
        return Response
                .status(200)
                .entity("Id: " + id + ", Name: " + name + ", Age: " + age
                        + ", User-Agent: " + userAgent).build();
    }

    @Path("/getAddress")
    public AddressResource getAddress() {
        return new AddressResource();
    }

    @Path("{userId}/orders")
    public OrderResource getOrderResource(@PathParam("userId") String userId) {
        return new OrderResource(userId);
    }

    @GET
    @Path("{userId}")
    public Response getUser2(@PathParam("userId") String userId) {
        return Response.status(200)
                .entity("User")
                .build();
    }

}

// resource methods: getUser(), getUsername(), usingQueryParam(), getAddress(), getOrderResource(), getUser2()
// sub-resource methods: getUsername(), usingQueryParam(), getAddress(), getOrderResource(), getUser2()
// sub-resource locator methods: getAddress(), getOrderResource()

// root-resource class: ExampleResource
// sub-resource class: AddressResource, OrderResource
