package com.demo.JSON.JSONP;

import javax.json.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/JSONPResource")
public class JSONPResource {

    @GET
    @Path("/getJsonArray")
    @Produces(MediaType.APPLICATION_JSON)
    public JsonArray getAll() {
        JsonObjectBuilder documentBuilder = Json.createObjectBuilder();
        documentBuilder.add("1", "John");
        documentBuilder.add("2", "Williams");
        documentBuilder.add("3", "Sophie");

        JsonObject document = documentBuilder.build();
        JsonArrayBuilder arrayBuilder = Json.createArrayBuilder();
        arrayBuilder.add(document);
        System.out.println("JsonArray: " + document);

        return arrayBuilder.build();
    }

    @POST
    @Path("/postJsonObject")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject printJsonObject(JsonObject jsonObject) {
        System.out.println("JsonObject: " + jsonObject);
        return jsonObject;
    }

}
