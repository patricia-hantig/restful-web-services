package com.demo.customEntityProvider;

import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@Provider
@Produces(Person.PERSON_MIME_TYPE)
public class PersonWriter implements MessageBodyWriter<Person> {

    // This entity provider is responsible for serializing a Person object into an HTTP response body of a specific media type (Person.PERSON_MIME_TYPE)

    @Override
    public boolean isWriteable(Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return Person.class.isAssignableFrom(aClass);
    }
    // This method is responsible for determining if the entity provider is capable of serializing the given type of object
    // we check if the Person class is assignable from the type parameter and return true if it is, indicating that this entity provider can handle the serialization of Person objects
    // aClass = The class of the object to be written
    // type = The generic type of the object to be written
    // annotations = An array of annotations associated with the object
    // mediaType = The media type of the request body

    @Override
    public long getSize(Person person, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }
    // This method returns the size in bytes of the serialized representation of the Person object
    // As of JAX-RS 2.0, the method has been deprecated and the size value is ignored by JAX-RS runtime as of JAX-RS 2.0
    // All MessageBodyWriter implementations are advised to return -1 from the method

    @Override
    public void writeTo(Person person, Class<?> aClass, Type type, Annotation[] annotations, MediaType mediaType, MultivaluedMap<String, Object> multivaluedMap, OutputStream outputStream) throws IOException, WebApplicationException {
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(person);
    }
    // This method performs the actual serialization of the Person object into the response body
    // The implementation in this example uses an ObjectOutputStream to write the Person object to the output stream

    // person = the Person object to be serialized
    // multivaluedMap = A map containing the request headers as key-value pairs
    // outputStream = An output stream representing the response body content
}

// @Provider annotation = indicates that this class is an entity provider and should be automatically discovered and registered by the JAX-RS runtime.