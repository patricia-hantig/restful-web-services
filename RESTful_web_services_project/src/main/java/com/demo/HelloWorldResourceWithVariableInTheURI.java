package com.demo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

// example of a root-resource class with variable on the class level

@Path("helloWorld/{name}")
public class HelloWorldResourceWithVariableInTheURI {

    @GET
    @Produces("text/plain")
    public String sayHello(@PathParam("name") String name) {
        return "Hello, " + name;
    }
}

// {name} = is the variable embedded in the URI
// @PathParam("name") = is used to access the variable's value
// @PathParam = is used on the method parameter 'name' of a request method to get the value of the 'name' variable


// POSTMAN call: http://localhost:8080/RESTful_web_services_project/services/helloWorld/Patri