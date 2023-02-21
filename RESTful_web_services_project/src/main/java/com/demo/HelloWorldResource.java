package com.demo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("helloWorld")
public class HelloWorldResource {   // this is a JAX-RS resource = annotated POJO

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String greet() {
        return "Hello World!!!";
    }   // this is a resource method that handles HTTP requests for the URI paths bound to the resource
}

// "helloWorld" is a resource URI
