package com.demo.JSON.Jackson;

import org.codehaus.jackson.map.AnnotationIntrospector;
import org.codehaus.jackson.map.AnnotationIntrospector.Pair;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.introspect.JacksonAnnotationIntrospector;
import org.codehaus.jackson.xc.JaxbAnnotationIntrospector;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

// we create this custom ObjectMapper for creating a custom implementation of serialization/deserialization
@Provider
public class MyObjectMapperProvider implements ContextResolver<ObjectMapper> {

    // default Object Mapper will be used for other POJOs
    final ObjectMapper defaultObjectMapper;

    // user Object Mapper will be used for User POJO only
    final ObjectMapper userObjectMapper;

    // in the constructor we need to initialize the defaultObjectMapper and userObjectMapper
    public MyObjectMapperProvider() {
        defaultObjectMapper = createDefaultMapper();
        userObjectMapper = createUserObjectMapper();
    }

    // this method is responsible for providing the appropriate ObjectMapper instance based on the type of class being processed
    // and allows us to customize the serialization and deserialization for our application
    @Override
    public ObjectMapper getContext(Class<?> type) {
        System.out.println("getContext(Class<?> type: " + (type == User.class?"User":"Default") + ")");
        if (type == User.class)
            return userObjectMapper;
        else {
            return defaultObjectMapper;
        }
    }

    // this method helps in configuring the ObjectMapper to handle specific serialization and deserialization requirements
    // in this case: we wrap and unwrap root values and combine annotations from both JAXB and Jackson framework
    private static ObjectMapper createUserObjectMapper() {
        System.out.println("createUserObjectMapper");

        // we call createJaxbJacksonAnnotationIntrospector() method to create a custom introspection that combinesJAXB and Jackson
        // this means we can handle annotations from both frameworks
        Pair combinedIntrospector = createJaxbJacksonAnnotationIntrospector();

        ObjectMapper result = new ObjectMapper();

        // when an object is serialized to JSON, it will be wrapped in a root element
        result.configure(SerializationConfig.Feature.WRAP_ROOT_VALUE, true);
        // when JSON is deserialized into an object, the root element will be unwrapped
        result.configure(DeserializationConfig.Feature.UNWRAP_ROOT_VALUE, true);

        // the custom introspector created is set as the annotation introspector for both serialization and deserialization configurations
        // this means that both JAXB and Jackson annotations are properly recognized and processed during serialization and deserialization
        result.setSerializationConfig(result.getSerializationConfig().withAnnotationIntrospector(combinedIntrospector));
        result.setDeserializationConfig(result.getDeserializationConfig().withAnnotationIntrospector(combinedIntrospector));

        // if we want our response to be pretty-printed we add this
        result.configure(SerializationConfig.Feature.INDENT_OUTPUT, true);

        return result;
    }

    // this method creates a pair of annotation introspectors that transform JAXB annotations to JSON mapping
    private static Pair createJaxbJacksonAnnotationIntrospector() {
        System.out.println("createJaxbJacksonAnnotationIntrospector");
        AnnotationIntrospector jaxbIntrospector = new JaxbAnnotationIntrospector();
        AnnotationIntrospector jacksonIntrospector = new JacksonAnnotationIntrospector();

        return new Pair(jacksonIntrospector, jaxbIntrospector);
    }

    // this method is responsible for creating and configuring a DefaultObjectMapper - which will be used for any POJOs that doesn't require a specific custom configuration
    private ObjectMapper createDefaultMapper() {
        System.out.println("createDefaultMapper");

        ObjectMapper result = new ObjectMapper();
        // this feature INDENT_OUTPUT instructs the ObjectMapper to format the output JSON with indentation, making it more human-readable
        result.configure(SerializationConfig.Feature.INDENT_OUTPUT, true);

        return result;
    }

}
