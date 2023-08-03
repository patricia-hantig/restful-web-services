package com.demo.JSON.JSONP;

import org.glassfish.jersey.jsonp.JsonProcessingFeature;

import javax.json.*;
import javax.json.stream.JsonGenerator;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

public class ResourceClient {

    private Client client;

    private final static String HOST_NAME = "http://localhost:8080/";
    private final static String CONTEXT_ROOT_URI = "RESTful_web_services_project/services/JSONPResource/";

    // we initiate the connection between client and server by creating a client instance
    public ResourceClient() {
        client = ClientBuilder.newBuilder()
                .register(JsonProcessingFeature.class)
                .property(JsonGenerator.PRETTY_PRINTING, true)
                .build();
    }

    public static void main(String[] args) {
        ResourceClient resourceClient = new ResourceClient();

        // the GET method
        resourceClient.callGetMethod();

        // the POST method
        resourceClient.callPostMethod();
    }

    public void callGetMethod() {
        System.out.println("-------------------- Get Method --------------------");
        WebTarget target = client.target(HOST_NAME + CONTEXT_ROOT_URI).path("getJsonArray");
        JsonArray jsonArray = target.request(MediaType.APPLICATION_JSON).get(JsonArray.class);
        System.out.println("Response Data: " + "\n" + formatJsonArray(jsonArray));
    }

    public void callPostMethod() {
        System.out.println();
        System.out.println("-------------------- Post Method --------------------");
        WebTarget target = client.target(HOST_NAME + CONTEXT_ROOT_URI).path("postJsonObject");
        JsonObject jsonObject = Json.createObjectBuilder()
                                    .add("1", "John")
                                    .add("2", "Williams")
                                    .add("3", "Sophie")
                                    .build();
        JsonObject responseJsonObject = target.request().post(Entity.entity(jsonObject, MediaType.APPLICATION_JSON), JsonObject.class);
        String jsonString = responseJsonObject.toString();
        System.out.println("Response Data: " + formatJsonString(jsonString));
    }

    // Setting the property JsonGenerator.PRETTY_PRINTING on the client instance will affect the serialization of JSON data
    // when making requests, but it doesn't automatically format the response received from the server

    // JsonGenerator.PRETTY_PRINTING controls how the JSON data is serialized, specifying whether it should be formatted with indentation and line breaks
    // However, when receiving the response, the client receives the raw JSON data, and it's up to the client code to format it for display purposes

    // format JsonArray to print the pretty-printed response
    private String formatJsonArray(JsonArray jsonArray) {
        StringWriter stringWriter = new StringWriter();
        Map<String, Boolean> config = new HashMap<>();
        config.put(JsonGenerator.PRETTY_PRINTING, true);

        JsonWriterFactory writerFactory = Json.createWriterFactory(config);
        try (JsonWriter jsonWriter = writerFactory.createWriter(stringWriter)) {
            jsonWriter.writeArray(jsonArray);
        }

        return stringWriter.toString();
    }

    // format JsonObject to print the pretty-printed response
    private String formatJsonString(String jsonString) {
        JsonReader reader = Json.createReader(new StringReader(jsonString));
        JsonObject jsonObject = reader.readObject();
        Map<String, Object> properties = new HashMap<>();
        properties.put(JsonGenerator.PRETTY_PRINTING, true);

        StringWriter writer = new StringWriter();
        try (JsonWriter jsonWriter = Json.createWriterFactory(properties).createWriter(writer)){
            jsonWriter.writeObject(jsonObject);
        }

        return writer.toString();
    }
}
