package com.demo.parameterAnnotations;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("userService")
public class UserResource {

    @GET
    @Path("{name}")
    public String getUserByName(@PathParam("name") String name) {
        return name;
    }

    @GET
    @Path("/queryParam")
    public String getUserByQueryParamName(@QueryParam("name") String param1) {
        return param1;
    }

    @GET
    @Path("/queryParamDefault")
    public String getUserWithDefaultAge(@QueryParam("name") String name, @DefaultValue("15") @QueryParam("age") int age) {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        return name + " " + age;
    }

    @GET
    @Path("/usingMatrixParam/getUserById/{userId}")
    public Response getUserById(@PathParam("userId") String userId, @MatrixParam("name") String name, @DefaultValue("20") @MatrixParam("age") int age) {
        return Response.status(200)
                .entity("Id: " + userId + " , name: " + name + " , age: " + age)
                .build();
    }

    @GET
    @Path("/getUserAgent")
    public String getUserDevice(@HeaderParam("user-agent") String userAgent, @HeaderParam("Content-Type") MediaType contentType) {
        return "User Agent: " + userAgent + " , Content-Type: " + contentType;
    }

    @GET
    @Path("/getCookie")
    public String getCookie(@DefaultValue("10") @CookieParam("sessionId") int sessionId) {
        return "The value of sessionId cookie is " + sessionId;
    }

    @GET
    @Path("/getCookies")
    public String getCookies(@DefaultValue("10") @CookieParam("sessionId") int sessionId, @DefaultValue("secondCookie_Default") @CookieParam("anotherCookie") String secondCookie) {
        return "The value of sessionId cookie is " + sessionId + " and the second cookie is " + secondCookie;
    }

    /*@GET
    @Path("/getInformationFromCookie")
    public String getInfoCookies(@CookieParam("user-agent") Cookie userAgentCookie) {
        if (userAgentCookie != null) {
            return "Name: " + userAgentCookie.getName() +
                    "Value: " + userAgentCookie.getValue() +
                    "Domain: " + userAgentCookie.getDomain() +
                    "Path: " + userAgentCookie.getPath() +
                    "Version: " + userAgentCookie.getVersion();
        } else {
            return "No User-Agent cookie found";
        }
    }*/

    @GET
    @Path("/getInformationFromCookie")
    public String getInfoCookies(@CookieParam("sessionID") Cookie existentCookie) {
        if (existentCookie != null) {
            return "Name: " + existentCookie.getName() + " " +
                    "Value: " + existentCookie.getValue() + " " +
                    "Domain: " + existentCookie.getDomain() + " " +
                    "Path: " + existentCookie.getPath() + " " +
                    "Version: " + existentCookie.getVersion();
        } else {
            return "No Existed cookie found";
        }
    }

    @GET
    @Path("/createNewCookie")
    public Response setCookie() {
        String cookieName = "sessionID";
        String cookieValue = "1234";
        String cookiePath = "/RESTful_web_services_project/services/userService";
        String cookieDomain = "localhost";
        int cookieVersion = 1;
        String cookieComment = "Comment";
        boolean cookieSecure = false;
        NewCookie cookie = new NewCookie(cookieName, cookieValue, cookiePath, cookieDomain, cookieVersion, cookieComment, NewCookie.DEFAULT_MAX_AGE, cookieSecure);
        return Response.ok("Cookie set").cookie(cookie).build();
    }

    @POST
    @Path("/addUser")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response addUser(@FormParam("name") String name, @FormParam("email") String email, @FormParam("password") String password) {
        return Response.status(Response.Status.CREATED)
                .entity("User was created successfully: " + name + " " + email + " " + password)
                .build();
    }

    @POST
    @Path("/addUserWithMultivaluedMap")
    public Response addUser2(MultivaluedMap<String, String> formData) {
        String firstName = formData.getFirst("name");
        String fullName = formData.get("name").toString();
        String email = formData.getFirst("email");
        String password = formData.getFirst("password");

        return Response.status(201)
                .entity("User was created successfully: " + firstName + " " + fullName + " " + email + " " + password)
                .build();
    }
}

// @PathParam("name") = is used to bind the value of the 'name' path parameter to the 'name' parameter of the getUserByName() method
// the name of the method parameter ('name' in this case) must match the name of the path parameter ({name} in the @Path annotation
// If the specified path parameter is not present in the request URI, the JAX-RS runtime will return a 404 (Not Found) response

// @QueryParam("name") = is used to bind the value of the 'name' query parameter to the 'name'' parameter of the getUserByQueryParamName() method
// the name of the method parameter ('name' in this case) DO NOT have to match the name of the query parameter (String param1)
//      => BUT the name of the @QueryParam annotations must match the names of the query parameters ( 'name' should be the key of the query param in Postman)
// If the specified query parameter is not present in the request URI, the JAX-RS runtime will set the value of the @QueryParam parameter to 'null'

// @DefaultValue = can be used with @PathParam, @QueryParam, @MatrixParam, @FormParam, @HeaderParam and @CookieParam
//              => when there is an optional parameter the best practice is to set a default value

// @MatrixParam = is a set of key-value pairs that appear in the path segment of a URI, separated by semicolons
// they are optional params -> if not set JAX-RS runtime will set the value to null

// @HeaderParam =  is used to extract values from HTTP headers in a request
// @HeaderParam("user-agent") - doesn't need to be declared because it's an auto-generated header
// - we can use the @DefaultValue annotation to specify a default value for a header parameter if the header is not present in the request

// @CookieParam = is used to inject the value of a cookie sent in an HTTP request into a resource method parameter

// @FormParam = is used to extract data from the HTTP request body which is encoded as 'application/x-www-form-urlencoded'
//              - we can inject single parameters into the resource method invocation
//              - this annotation is declared for each parameter

// MultivaluedMap - is similar to @FormParam annotation, but instead of declaring it for each param,
//                  using MultivaluedMap = we get all the form parameters as key-value pairs
//                  - the data is automatically decoded by JAX-RS implementation