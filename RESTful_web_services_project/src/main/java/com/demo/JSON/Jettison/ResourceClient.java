package com.demo.JSON.Jettison;

import org.glassfish.jersey.jettison.JettisonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class ResourceClient {

    private Client client;

    private final static String HOST_NAME = "http://localhost:8080/";
    private final static String CONTEXT_ROOT_URI = "RESTful_web_services_project/services/JettisonResource";

    // we initiate the connection between client and server by creating a client instance
    public ResourceClient() {
        client = ClientBuilder.newBuilder()
                .register(JettisonFeature.class)
                .register(CustomContextResolver.class)
                .build();
    }

    public static void main(String[] args) {
        ResourceClient resourceClient = new ResourceClient();

        // the GET method that returns one User
        resourceClient.callGetMethod();

        // the GET method that returns a list of users
        resourceClient.callGetMethodForUserList();
    }

    public void callGetMethod() {
        System.out.println("-------------------- Get Method that returns one user --------------------");
        WebTarget target = client.target(HOST_NAME + CONTEXT_ROOT_URI).path("/getUser");
        User user = target.request(MediaType.APPLICATION_JSON).get(User.class);
        System.out.println("Response: " + user.toString());
    }

    public void callGetMethodForUserList(){
        System.out.println();
        System.out.println("-------------------- Get Method that returns a list of users --------------------");
        WebTarget target = client.target(HOST_NAME + CONTEXT_ROOT_URI).path("/getUsers");
        GenericType<List<User>> userListGenericType = new GenericType<List<User>>() {};
        List<User> userList = target.request(MediaType.APPLICATION_JSON).get(userListGenericType);
        System.out.println("Response: " + userList);
    }
}
