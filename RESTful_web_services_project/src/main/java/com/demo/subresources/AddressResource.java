package com.demo.subresources;

import javax.ws.rs.GET;
import javax.ws.rs.core.Response;

// this is a sub-resource class
public class AddressResource {

    @GET
    public Response getAddress() {
        return Response.status(200)
                .entity("Address")
                .build();
    }
}

// getAddress() is a resource method
