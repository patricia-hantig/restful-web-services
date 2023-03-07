package com.demo.httpMethods;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

// example of a root-resource class for GET method

@Path("getResource")
public class GetResource {

    @GET
    public String getUser() {
        System.out.println("GET request");
        return "Hello User";
    }

    @GET
    @Path("{id}")
    public Response getResourceById(@PathParam("id") int id) {
        return Response.status(200)
                .entity("Id: " + id)
                .build();
    }

    @GET
    @Path("/queryParam")
    public Response getResourceByQueryParams(@QueryParam("nameOfResource") String param1, @QueryParam("numberOfResource") int param2) {
        return Response.ok()
                .entity("Resource name " + param1 + " and resource number " + param2)
                .build();
    }
}

// @GET = is a request method designator defined by JAX-RS
// - The behavior of a resource is determined by the HTTP method to which the resource would respond
