package com.demo.JSON.JSONP;

import org.glassfish.jersey.jsonp.JsonProcessingFeature;
import org.glassfish.jersey.server.ResourceConfig;

import javax.json.stream.JsonGenerator;

public class UsingResourceConfig  extends ResourceConfig {

    public UsingResourceConfig() {
        System.out.println("UsingResourceConfig");
        packages("com.demo.JSON.JSONP")
                .register(JsonProcessingFeature.class)
                .property(JsonGenerator.PRETTY_PRINTING, true);
    }
}
