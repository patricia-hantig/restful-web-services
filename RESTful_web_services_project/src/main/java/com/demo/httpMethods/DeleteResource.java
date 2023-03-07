package com.demo.httpMethods;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

// example of a root-resource class for DELETE method

@Path("deleteResource")
public class DeleteResource {

    // method 1
    @DELETE
    @Path("{name}")
    public void delete(@PathParam("name") String name) {
        System.out.println("DELETE: " + name);
    }

    // method 2
    @DELETE
    @Path("/withQueryParam")
    public String deleteUser(@QueryParam("name") String name) {
        System.out.println("DELETE: " + name);
        System.out.println("Name: " + name);
        return "Delete " + name;
    }

    // method 3
    @DELETE
    @Path("/withQueryParamAndReturnResponse")
    public Response deleteResource(@QueryParam("id") int id) {
        // delete the resource with the specified ID
        System.out.println("DELETE: " + id);
        System.out.println("Id: " + id);

        return Response.status(Response.Status.OK)
                .entity("Deleting id " + id)
                .build();
    }
}

// @DELETE = is used for to delete a resource identified by a URI
// we have different methods based on what type of parameters we use and based on what we return

// @QueryParam  = is a annotation that can be used to extract query parameters from an HTTP request
//              = it's used to annotate a method parameter that should be mapped to a query parameter to the request URI