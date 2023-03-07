package com.demo.bean;

import javax.ws.rs.BeanParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

@Path("beanResource")
public class BeanResource {

    @GET
    @Path("/getUserBeanDetails/{id}")
    public String getUserById(@BeanParam UserBean userBean) {
        return "User Bean: " + userBean.toString();
    }

    @GET
    @Path("/getUserBeanNameAndAge")
    public String getUserByNameAndAge(@BeanParam UserBean userBean) {
        return "User Bean: " + userBean.getName() + ", " + userBean.getAge();
    }

    @POST
    @Path("/addUser/{id}")
    public String addUser(@BeanParam UserBean userBean) {
        return "User Bean: " + userBean.toString() + " has been added successfully.";
    }
}

// @BeanParam = is used to inject a bean parameter into a resource method
//              A bean annotated with @BeanParam allows you to group multiple parameters into a single bean & simplify your resource method signature
//  - instead of having a resource with several query params, path params, header params etc => you can create a Java bean class that contains all these parameters as properties
