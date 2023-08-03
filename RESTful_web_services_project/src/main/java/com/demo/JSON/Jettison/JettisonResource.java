package com.demo.JSON.Jettison;
;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.*;

@Path("/JettisonResource")
public class JettisonResource {

    @GET
    @Path("/getUser")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUser() {
        return new User(1, "Mike");
    }

    @GET
    @Path("/getUsers")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<User> getUsers() {
        List<User> userList = new ArrayList<>();
        userList.add(new User(1, "John"));
        userList.add(new User(2, "Williams"));
        userList.add(new User(3, "Suzanne"));
        System.out.println("UserList: " + userList);
        return userList;
    }

}
