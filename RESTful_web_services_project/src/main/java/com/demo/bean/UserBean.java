package com.demo.bean;

import javax.ws.rs.*;

public class UserBean {

    @PathParam("id")
    private String id;

    @MatrixParam("name")
    private String name;

    @MatrixParam("age")
    private String age;

    @DefaultValue("No address provided")
    @QueryParam("address")
    private String address;

    @HeaderParam("user-agent")
    private String userAgent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", address='" + address + '\'' +
                ", userAgent='" + userAgent + '\'' +
                '}';
    }
}
