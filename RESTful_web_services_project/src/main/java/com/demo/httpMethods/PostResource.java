package com.demo.httpMethods;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import java.util.List;

// example of a root-resource class for POST method

@Path("postResource")
public class PostResource {

    // method 1
    @POST
    @Path("/simpleExample")
    public String addUser(String userData) {
        System.out.println("POST(method 1): ");
        System.out.println("User data: " + userData);
        return userData;
    }

    // method 2
    @POST
    @Path("/withFormParam")
    public String addUser(@FormParam("id") String id, @FormParam("name") String name) {
        System.out.println("POST(method 2): ");
        System.out.println("Id: " + id);
        System.out.println("Name: " + name);
        return "Hello, " + name + " with the id " + id;
    }

    // method 3
    @POST
    @Path("/withFormParamAndConsume")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String postUser(@FormParam("id") String id, @FormParam("name") String name) {
        System.out.println("POST(method 3): ");
        System.out.println("Id: " + id);
        System.out.println("Name: " + name);
        return "Hello, " + name + " with the id " + id;
    }

    // method 4
    @POST
    @Path("/withFormParamAndConsumeAndReturn")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String post(@FormParam("id") String id, @FormParam("name") String name) {
        System.out.println("POST(method 4): ");
        System.out.println("Id: " + id);
        System.out.println("Name: " + name);
        return "Hello, " + name + " with the id " + id;
    }

    // method 5
    @POST
    @Path("/withReturnResponse")
    public Response postForm(@FormParam("name") String name) {
        System.out.println("POST(method 5): ");
        System.out.println("Name: " + name);
        return Response.status(200)
                .entity("Hello, " + name)
                .build();
    }

    // method 6
    @POST
    @Path("/simpleExampleWithMultivaluedMap")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String addUser(MultivaluedMap<String, String> formData) {
        System.out.println("POST(method 6): ");
        System.out.println("Form Data: " + formData);
        return "User " + formData.getFirst("formData") + " added successfully";
    }

    // method 7
    @POST
    @Path("/withMultivaluedMapAndReturnResponse")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response handleForm(MultivaluedMap<String, String> formData) {
        String param1 = formData.getFirst("param1");
        List<String> param2 = formData.get("param2");
        String param3 = formData.getFirst("param3");

        System.out.println("POST(method 7): ");
        System.out.println("First param to UpperCase: " + param1.toUpperCase());
        System.out.println("Second param to list: " + param2.toString());
        System.out.println("Third param: " + param3);
        return Response.status(Response.Status.OK)
                .entity("Hello user: " + param1 + " who likes: " + param2 + " and is " + param3 + " years old")
                .build();
    }

    @POST
    @Path("/withHeaderParam")
    public Response createResource(@HeaderParam("Content-Type") String contentType, String content) {
        // Create the resource using the specified header value and content...
        return Response.status(200)
                .entity("Content-Type: " + contentType + " and request body: " + content)
                .build();
    }
}

// @POST = is used for creation of new resources
// we have different methods based on what type of parameters we use and based on what we return

// @FormParam annotation is declared for each parameter
// MultivaluedMap   - contains all the form parameters as key-value pairs
//                  - has several methods: getFirst() - to get the first value of a param
//                                          get() - to get all the values of a param as list
