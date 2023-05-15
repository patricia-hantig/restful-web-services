package com.demo.customEntityProvider;

import javax.ws.rs.Consumes;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyReader;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
@Consumes(Person.PERSON_MIME_TYPE)
public class PersonReader implements MessageBodyReader<Person> {

    // This entity provider is responsible for deserializing an HTTP request body of a specific media type (Person.PERSON_MIME_TYPE) into a Person object

    @Override
    public boolean isReadable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return Person.class.isAssignableFrom(aClass);
    }
    // This method is responsible for determining if the entity provider is capable of reading the given type of object
    // we check if the Person class is assignable from the aClass parameter and returns true if it is, indicating that this entity provider can handle the deserialization of Person objects

    // aClass = The class of the object to be read
    // type = The generic type of the object to be read
    // annotations = An array of annotations associated with the object
    // mediaType = The media type of the request body

    @Override
    public Person readFrom(Class<Person> personClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, String> multivaluedMap, InputStream inputStream) throws IOException, WebApplicationException {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
            return (Person) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
    // This method performs the actual deserialization of the request body into a Person object
    // we use an ObjectInputStream to read the Person object from the input stream and return it
    // If an exception occurs during deserialization or if the Person class is not found, it catches the exception and returns null

    // multivaluedMap = A map containing the request headers as key-value pairs
    // inputStream = An input stream representing the request body content

}

// @Provider annotation = indicates that this class is an entity provider and should be automatically discovered and registered by the JAX-RS runtime
