package com.demo.JSON.Jettison;

import org.glassfish.jersey.jettison.JettisonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class UsingResourceConfig extends ResourceConfig {

    public UsingResourceConfig() {
        System.out.println("UsingResourceConfig for Jettison");
        packages("com.demo.JSON.Jettison")
                .register(JettisonFeature.class)
                .register(CustomContextResolver.class);
    }
}
