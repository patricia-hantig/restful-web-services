package com.demo.JSON.Jettison;

import org.glassfish.jersey.jettison.JettisonConfig;
import org.glassfish.jersey.jettison.JettisonJaxbContext;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

// this is optional: it's used when we need to customize the serialization/deserialization
// by providing a custom ContextResolver we ensure that the User class is serialized and deserialized using the Jettison provider & it corresponding JAXBContext

// the default providers and context resolvers provided by JAX-RS and its implementations are sufficient for handling most scenarios
@Provider
public class CustomContextResolver implements ContextResolver<JAXBContext> {

    private final JAXBContext context;
    private final Set<Class<?>> types;
    private final Class<?>[] classTypes = {User.class};

    public CustomContextResolver() throws JAXBException {
        this.types = new HashSet<Class<?>>(Arrays.asList(classTypes));
        this.context = new JettisonJaxbContext(JettisonConfig.DEFAULT, classTypes);
    }

    @Override
    public JAXBContext getContext(Class<?> objectType) {
        return (types.contains(objectType)) ? context : null;
    }
}
