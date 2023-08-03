package com.demo.JSON.MOXy;

import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class ResourceClient {

    private Client client;

    private final static String HOST_NAME = "http://localhost:8080/";
    private final static String CONTEXT_ROOT_URI = "RESTful_web_services_project/services/MOXy/userResource/";

    // we initiate the connection between client and server by creating a client instance
    public ResourceClient() {
        client = ClientBuilder.newBuilder()
                // option 1 : if we use ResourceConfig and register a custom JsonMoxyConfigurationContextResolver

                // The line bellow that registers MOXy feature can be omitted if FEATURE_AUTO_DISCOVERY_DISABLE is not disabled.
                // By default, FEATURE_AUTO_DISCOVERY_DISABLE is enabled, meaning that MOXy is automatically discovered and registered as the JSON provider
                .register(MoxyJsonFeature.class)
                .register(UsingResourceConfig.JsonMOXyConfigurationContextResolver.class)

                // option 2 : if we want to use Application - that directly registers MOXyJsonProvider
                // comment option 1 if you want to test option 2
                /*.register(MoxyJsonFeature.class)
                .register(UsingApplication.class)*/
                .build();
    }

    public static void main(String[] args) {
        ResourceClient resourceClient = new ResourceClient();
        
        // the GET method
        resourceClient.callGetMethod();
        
        // the POST method
        resourceClient.callPostMethod();
    }

    private void callGetMethod() {
        System.out.println("-------------------- Get Method --------------------");
        WebTarget target = client.target(HOST_NAME + CONTEXT_ROOT_URI).path("getUser");
        User user = target.request(MediaType.APPLICATION_JSON).get(User.class);
        System.out.println("Response Data - id is: " + user.getId() + " and name is: " + user.getName());
        System.out.println("User is: " + user.toString());
    }

    private void callPostMethod() {
        System.out.println();
        System.out.println("-------------------- Post Method --------------------");
        WebTarget target = client.target(HOST_NAME + CONTEXT_ROOT_URI).path("postUser");
        User userBean = target.request(MediaType.APPLICATION_JSON).post(Entity.entity(new User(200, "Mike"), MediaType.APPLICATION_JSON), User.class);
        System.out.println("Response Data - id is: " + userBean.getId() + " and name is: " + userBean.getName());
        System.out.println("User is: " + userBean.toString());
    }
}
