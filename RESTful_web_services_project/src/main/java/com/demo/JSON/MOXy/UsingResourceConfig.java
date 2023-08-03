package com.demo.JSON.MOXy;

import org.glassfish.jersey.moxy.json.MoxyJsonConfig;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import java.util.HashMap;
import java.util.Map;

// this class is responsible for configuring the Jersey application: we configure to use MOXy as the JSON provider
public class UsingResourceConfig extends ResourceConfig {

    public UsingResourceConfig() {
        System.out.println("Using Resource Config");
        packages("com.demo.JSON.MOXy").register(new JsonMOXyConfigurationContextResolver());
    }

    @Provider
    final static class JsonMOXyConfigurationContextResolver implements ContextResolver<MoxyJsonConfig> {
        @Override
        public MoxyJsonConfig getContext(Class<?> objectType) {
            System.out.println("Using Resource Config - JsonMOXyConfigurationContextResolver");
            final MoxyJsonConfig config = new MoxyJsonConfig();

            Map<String, String> namespacePrefixMapper = new HashMap<String, String>(1);
            namespacePrefixMapper.put("http://www.w3.org/2001/XMLSchema-instance", "xsi");

            config.setNamespacePrefixMapper(namespacePrefixMapper);
            config.setNamespaceSeparator(':');

            return config;
        }
    }
}
