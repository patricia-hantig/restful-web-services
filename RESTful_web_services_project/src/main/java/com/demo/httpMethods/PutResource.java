package com.demo.httpMethods;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

// example of a root-resource class for PUT method

@Path("putResource")
public class PutResource {

    // method 1
    @PUT
    @Path("/simpleExample")
    public void updateUser(String userData) {
        System.out.println("PUT: ");
        System.out.println("User data:" + userData);
    }

    // method 2
    @PUT
    @Path("/withFormParam")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String put(@FormParam("name") @DefaultValue("default") String name) {
        System.out.println("PUT: ");
        System.out.println("Name: " + name);
        return "Hello, " + name;
    }
}

// @PUT = is used for update capabilities

// method 1 : PUT request has a payload associated with it that is stored in the 'userData' variable
//          - in PostMan: body -> raw form

// method 2 : we can use the annotation @FormParam
//          @FormParam annotation in JAX-RS is used to bind a parameter in a resource method to a form parameter in the HTTP request body
//  in order to use @FormParam annotation correctly:
//      - the content type of the request must be: application/x-www-form-urlencoded
//      - use @Consumes annotation to specify the content type of the request
//      - make sure that @FormParam annotation is used to annotate a parameter in your resource method (the parameter name should correspond to the name of the form parameter in the request body)
//      - OPTIONAL you can use the @DefaultValue annotation to specify a default value