package com.demo.JSON.Jackson;

import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

public class ResourceClient {

    private Client client;

    private final static String HOST_NAME = "http://localhost:8080/";
    private final static String CONTEXT_ROOT_URI = "RESTful_web_services_project/services/JacksonResource";

    // we initiate the connection between client and server by creating a client instance
    public ResourceClient() {
        client = ClientBuilder.newBuilder()
                .register(JacksonFeature.class)
                .register(MyObjectMapperProvider.class)
                .build();
    }

    public static void main(String[] args) {
        ResourceClient resourceClient = new ResourceClient();

        // the GET method
        resourceClient.callGetMethod();

        // the GET method 2
        resourceClient.callGetMethod2();
    }

    public void callGetMethod() {
        System.out.println("-------------------- Get Method --------------------");
        WebTarget target = client.target(HOST_NAME + CONTEXT_ROOT_URI).path("/getUser");
        User user = target.request(MediaType.APPLICATION_JSON).get(User.class);
        System.out.println("Response Data - id is: " + user.getId() + " and name is: " + user.getName());
        System.out.println("User is: " + user.toString());

        System.out.println();
        System.out.println("Response Data pretty printed:");
        try {
            System.out.println(formatJson(user));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void callGetMethod2() {
        System.out.println();
        System.out.println("-------------------- Get Method 2 --------------------");
        WebTarget target = client.target(HOST_NAME + CONTEXT_ROOT_URI).path("/getUserBean");
        UserBean userBean = target.request(MediaType.APPLICATION_JSON).get(UserBean.class);
        System.out.println("Response Data - id is: " + userBean.getId() + " and name is: " + userBean.getName());
        System.out.println("User is: " + userBean.toString());

        System.out.println();
        System.out.println("Response Data pretty printed:");
        try {
            System.out.println(formatJson(userBean));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Setting the property SerializationConfig.Feature.INDENT_OUTPUT to true on the client instance will affect the serialization of JSON data
    // when making requests, but it doesn't automatically format the response received from the server

    // SerializationConfig.Feature.INDENT_OUTPUT controls how the JSON data is serialized, specifying whether it should be formatted with indentation and line breaks
    // However, when receiving the response, the client receives the raw JSON data, and it's up to the client code to format it for display purposes

    private static String formatJson(User user) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        SerializationConfig config = mapper.getSerializationConfig();
        config.set(SerializationConfig.Feature.INDENT_OUTPUT, true);

        return mapper.writeValueAsString(user);
    }

    private static String formatJson(UserBean user) throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        SerializationConfig config = mapper.getSerializationConfig();
        config.set(SerializationConfig.Feature.INDENT_OUTPUT, true);

        return mapper.writeValueAsString(user);
    }
}
