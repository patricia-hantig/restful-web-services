package com.demo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

// example of a root-resource class with variable with regular expression

@Path("helloWorld3")
public class HelloWorldResourceWithVariableWithRegularExpression {

    @GET
    @Produces("text/plain")
    @Path("{name: ([a-zA-Z])*}")
    public String sayHello(@PathParam("name") String name) {
        return "Hello, " + name;
    }
}

// {name: ([a-zA-Z)*} = {"variable-name":"[regular-expression]"}
// the regular-expression will allow only users with letters in their name, ex John is ok, John123 should not be ok


// POSTMAN call: http://localhost:8080/RESTful_web_services_project/services/helloWorld3/Patri
//      wrong call: http://localhost:8080/RESTful_web_services_project/services/helloWorld3/Patri123