package com.demo.subresources;

import javax.ws.rs.GET;
import javax.ws.rs.core.Response;

// this is a sub-resource class
public class OrderResource {

    private String userId;

    public OrderResource(String userId) {
        this.userId = userId;
    }

    @GET
    public Response getOrders() {
        return Response.status(200)
                .entity("Orders")
                .build();
    }
}

// getOrders() is a resource method
