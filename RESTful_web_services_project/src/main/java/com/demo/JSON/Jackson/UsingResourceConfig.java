package com.demo.JSON.Jackson;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class UsingResourceConfig extends ResourceConfig {

    public UsingResourceConfig() {
        System.out.println("UsingResourceConfig for Jackson");
        packages("com.demo.JSON.Jackson")
                .register(JacksonFeature.class)
                .register(MyObjectMapperProvider.class);
    }
}
