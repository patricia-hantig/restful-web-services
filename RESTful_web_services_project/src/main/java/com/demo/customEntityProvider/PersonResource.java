package com.demo.customEntityProvider;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("personResource")
public class PersonResource {

    @POST
    @Consumes(value = Person.PERSON_MIME_TYPE)
    @Path("/addPerson")
    public String setPerson(Person person) {
        return "Id: " + person.getId() + ", Name: " + person.getName();
    }

}
