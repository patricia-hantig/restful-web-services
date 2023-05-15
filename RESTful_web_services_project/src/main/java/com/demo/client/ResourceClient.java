package com.demo.client;

import com.demo.customEntityProvider.Person;
import com.demo.customEntityProvider.PersonReader;
import com.demo.customEntityProvider.PersonWriter;

import javax.ws.rs.client.*;
import javax.ws.rs.core.*;

// This class demonstrates how to consume a RESTful web service with HTTP methods and *Param annotations
public class ResourceClient {

    private Client client;

    private final static String HOST_NAME = "http://localhost:8080/";
    private final static String CONTEXT_ROOT_URI = "RESTful_web_services_project/services/";

    // we initiate the connection between client and server by creating a client instance
    public ResourceClient() {
        client = ClientBuilder.newClient();
    }

    public static void main(String[] args) {
        ResourceClient resourceClient = new ResourceClient();

        // the GET method
        resourceClient.callGetMethod();

        // the POST method
        resourceClient.callPostMethod();
        resourceClient.callPostMethodWithFormParam();
        resourceClient.callPostMethodWithFormParamAndConsume();
        resourceClient.callPostMethodWithReturnResponse();
        resourceClient.callPostMethodWithMultivaluedMap();
        resourceClient.callPostMethodWithMultivaluedMapAndReturnResponse();

        // the PUT method
        resourceClient.callPutMethod();
        resourceClient.callPutMethodWithFormParam();

        // the DELETE method
        resourceClient.callDeleteMethod();
        resourceClient.callDeleteMethodWithQueryParam();

        // the Path parameter
        resourceClient.callGetMethodUsingPathParam();
        resourceClient.callGetMethodWithPathParam2();

        // the query parameter
        resourceClient.callGetMethodUsingQueryParam();
        resourceClient.callGetMethodUsingQueryParam2();
        resourceClient.callGetMethodUsingQueryParamAndDefaultValue();
        resourceClient.callDeleteMethodUsingQueryParam();

        // the cookie parameter
        resourceClient.callGetMethodToGetCookieValues();
        resourceClient.callGetMethodToGetCookieValues2();

        // the matrix parameter
        resourceClient.callGetMethodUsingMatrixParam();
        resourceClient.callGetMethodUsingMatrixParamWithDefaultValue();

        // the bean parameter
        resourceClient.callGetMethodUsingBeanParam();
        resourceClient.callGetMethodUsingBeanParam2();
        resourceClient.callPostMethodUsingBeanParam();

        // the @Produces annotation
        resourceClient.callGetMethodUsingProducesAnnotation();
        resourceClient.callGetMethodUsingProducesAnnotation2();

        // the @Consumes annotation
        resourceClient.callPostMethodUsingConsumesAnnotation();
        resourceClient.callPostMethodUsingConsumesAnnotation2();

        // all the request until here were made directly!!!
        // making a request using Invocation.Builder
        resourceClient.callGetMethodUsingInvocationBuilder();
        resourceClient.callPostMethodUsingInvocationBuilder();

        // custom Entity Provider method
        resourceClient.callMyObject();
    }

    public void callGetMethod() {
        System.out.println("-------------------- Get Method --------------------");

        //WebTarget target = client.target(HOST_NAME + CONTEXT_ROOT_URI + "getResource");
        // we can use path() method instead of concatenating Strings

        WebTarget target = client.target(HOST_NAME + CONTEXT_ROOT_URI).path("getResource");
        String responseData = target.request().get(String.class);
        System.out.println("Response Data: " + responseData);
    }

    public void callPostMethod() {
        System.out.println();
        System.out.println("-------------------- Post Method --------------------");
        WebTarget target = client.target(HOST_NAME + CONTEXT_ROOT_URI).path("postResource/simpleExample");

        String rawBody = "First user";

        Response response = target.request().post(Entity.entity(rawBody, MediaType.TEXT_PLAIN));
        String responseData = response.readEntity(String.class);

        // this is an alternative of the above 2 lines:
        // String responseData = target.request().post(Entity.entity(rawBody, MediaType.TEXT_PLAIN), String.class);

        System.out.println("Response Data: " + responseData);
    }

    public void callPostMethodWithFormParam() {
        System.out.println("-------------------- Post Method with Form Param --------------------");
        WebTarget target = client.target(HOST_NAME + CONTEXT_ROOT_URI).path("postResource/withFormParam");

        MultivaluedMap<String, String> postForm = new MultivaluedHashMap<String, String>();
        postForm.add("id", "1");
        postForm.add("name", "User 1");

        String responseData = target.request().post(Entity.form(postForm), String.class);

        System.out.println("Response Data: " + responseData);
    }

    public void callPostMethodWithFormParamAndConsume() {
        System.out.println("-------------------- Post Method with Form Param And Consume --------------------");
        WebTarget target = client.target(HOST_NAME + CONTEXT_ROOT_URI).path("postResource/withFormParamAndConsume");

        MultivaluedMap<String, String> postForm = new MultivaluedHashMap<String, String>();
        postForm.add("id", "2");
        postForm.add("name", "User 2");

        String responseData = target.request().post(Entity.form(postForm), String.class);

        System.out.println("Response Data: " + responseData);
    }

    public void callPostMethodWithReturnResponse() {
        System.out.println("-------------------- Post Method with ReturnResponse --------------------");
        WebTarget target = client.target(HOST_NAME + CONTEXT_ROOT_URI).path("postResource/withReturnResponse");

        MultivaluedMap<String, String> postForm = new MultivaluedHashMap<String, String>();
        postForm.add("name", "User 4");

        String responseData = target.request().post(Entity.form(postForm), String.class);

        System.out.println("Response Data: " + responseData);
    }

    public void callPostMethodWithMultivaluedMap() {
        System.out.println("-------------------- Post Method with MultivaluedMap --------------------");
        WebTarget target = client.target(HOST_NAME + CONTEXT_ROOT_URI).path("postResource/simpleExampleWithMultivaluedMap");

        MultivaluedMap<String, String> postForm = new MultivaluedHashMap<String, String>();
        postForm.add("formData", "User 5");

        String responseData = target.request().post((Entity.form(postForm)), String.class);

        System.out.println("Response Data: " + responseData);
    }

    public void callPostMethodWithMultivaluedMapAndReturnResponse() {
        System.out.println("-------------------- Post Method with MultivaluedMapAndReturnResponse --------------------");
        WebTarget target = client.target(HOST_NAME + CONTEXT_ROOT_URI).path("postResource/withMultivaluedMapAndReturnResponse");

        MultivaluedMap<String, String> postForm = new MultivaluedHashMap<String, String>();
        postForm.add("param1", "User 6");
        postForm.add("param2", "dogs");
        postForm.add("param2", "cats");
        postForm.add("param2", "birds");
        postForm.add("param3", "22");

        String responseData = target.request().post((Entity.form(postForm)), String.class);

        System.out.println("Response Data: " + responseData);
    }

    public void callPutMethod() {
        System.out.println();
        System.out.println("-------------------- Put Method --------------------");
        WebTarget target = client.target(HOST_NAME + CONTEXT_ROOT_URI).path("putResource/simpleExample");

        String rawBody = "User 1";
        String responseData = target.request().put(Entity.entity(rawBody, MediaType.TEXT_PLAIN), String.class);

        System.out.println("Response Data: " + responseData);
    }

    public void callPutMethodWithFormParam() {
        System.out.println("-------------------- Put Method with Form Param --------------------");
        WebTarget target = client.target(HOST_NAME + CONTEXT_ROOT_URI).path("putResource/withFormParam");

        MultivaluedMap<String, String> postForm = new MultivaluedHashMap<String, String>();
        postForm.add("name", "User 2");

        String responseData = target.request().put(Entity.form(postForm), String.class);
        System.out.println("Response Data: " + responseData);
    }

    public void callDeleteMethod(){
        System.out.println();
        System.out.println("-------------------- Delete Method --------------------");
        WebTarget target = client.target(HOST_NAME + CONTEXT_ROOT_URI + "deleteResource/John%20Doe?name=John%20Doe");
        String responseData = target.request().delete(String.class);
        System.out.println("Response Data: " + responseData);
    }

    public void callDeleteMethodWithQueryParam() {
        System.out.println("-------------------- Delete Method With Query Param --------------------");
        WebTarget target = client.target(HOST_NAME + CONTEXT_ROOT_URI).path("deleteResource/withQueryParam").queryParam("name", "User1");

        String responseData = target.request().delete(String.class);
        System.out.println("Response Data: " + responseData);
    }

    public void callGetMethodUsingPathParam() {
        System.out.println();
        System.out.println("-------------------- Path Param --------------------");
        System.out.println("-------------------- Get Method With Path Param --------------------");
        WebTarget target = client.target(HOST_NAME + CONTEXT_ROOT_URI).path("userService");
        String responseData = target.path("{name}").resolveTemplate("name", "User 738").request().get(String.class);
        System.out.println("Response Data: " + responseData);
    }

    public void callGetMethodWithPathParam2() {
        System.out.println("-------------------- Get Method With Path Param 2 --------------------");
        WebTarget target = client.target(HOST_NAME + CONTEXT_ROOT_URI).path("getResource/{id}").resolveTemplate("id", "30");
        String responseData = target.request().get(String.class);
        System.out.println("Response Data: " + responseData);
    }

    public void callGetMethodUsingQueryParam() {
        System.out.println();
        System.out.println("-------------------- Query Param --------------------");
        System.out.println("-------------------- Get Method With Query Param --------------------");
        WebTarget target = client.target(HOST_NAME + CONTEXT_ROOT_URI).path("userService/queryParam").queryParam("name", "NewUser");
        String responseData = target.request().get(String.class);
        System.out.println("Response Data: " + responseData);
    }

    public void callGetMethodUsingQueryParam2() {
        System.out.println("-------------------- Get Method With Query Param 2 --------------------");
        WebTarget target = client.target(HOST_NAME + CONTEXT_ROOT_URI).path("getResource/queryParam").queryParam("nameOfResource", "GetResource").queryParam("numberOfResource", "1");
        String responseData = target.request().get(String.class);
        System.out.println("Response Data: " + responseData);
    }

    public void callGetMethodUsingQueryParamAndDefaultValue() {
        System.out.println("-------------------- Get Method With Query Param and Default Value --------------------");
        //WebTarget target = client.target(HOST_NAME + CONTEXT_ROOT_URI).path("userService/queryParamDefault").queryParam("name", "User1").queryParam("age", "30");
        WebTarget target = client.target(HOST_NAME + CONTEXT_ROOT_URI).path("userService/queryParamDefault").queryParam("name", "User1");
        String responseData = target.request().get(String.class);
        System.out.println("Response Data: " + responseData);
    }

    public void callDeleteMethodUsingQueryParam() {
        System.out.println("-------------------- Delete Method With Query Param --------------------");
        WebTarget target = client.target(HOST_NAME + CONTEXT_ROOT_URI).path("deleteResource/withQueryParam").queryParam("name", "User1");
        String responseData = target.request().delete(String.class);
        System.out.println("Response Data: " + responseData);
    }

    public void callGetMethodToGetCookieValues() {
        System.out.println();
        System.out.println("-------------------- Cookie Param --------------------");
        System.out.println("-------------------- Get Method With Cookie Param --------------------");
        WebTarget target = client.target(HOST_NAME + CONTEXT_ROOT_URI).path("userService/getCookie");

        Cookie cookie = new Cookie("sessionId", "1234");
        String responseData = target.request().cookie(cookie).get(String.class);
        System.out.println("Response Data: " + responseData);
    }

    public void callGetMethodToGetCookieValues2() {
        System.out.println("-------------------- Get Method With 2 Cookie Params and one param has defaultValue --------------------");
        WebTarget target = client.target(HOST_NAME + CONTEXT_ROOT_URI).path("userService/getCookies");

        Cookie cookie = new Cookie("anotherCookie", "abcd");
        String responseData = target.request().cookie(cookie).get(String.class);

        // if we want to override the default value use the next code:
        /* Cookie cookie = new Cookie("sessionId", "1234");
        Cookie cookie2 = new Cookie("anotherCookie", "abcd");
        String responseData = target.request().cookie(cookie).cookie(cookie2).get(String.class);*/

        System.out.println("Response Data: " + responseData);
    }

    public void callGetMethodUsingMatrixParam() {
        System.out.println();
        System.out.println("-------------------- Matrix Param --------------------");
        System.out.println("-------------------- Get Method With Matrix Param --------------------");
        WebTarget target = client.target(HOST_NAME + CONTEXT_ROOT_URI).path("userService/usingMatrixParam/getUserById");
        String responseData = target.path("{userId}").resolveTemplate("userId", "User3922")
                                    .matrixParam("name","John")
                                    .matrixParam("age", "30")
                                    .request().get(String.class);
        System.out.println("Response Data: " + responseData);
    }

    public void callGetMethodUsingMatrixParamWithDefaultValue() {
        System.out.println("-------------------- Get Method With Matrix Param with DefaultValue --------------------");
        WebTarget target = client.target(HOST_NAME + CONTEXT_ROOT_URI).path("userService/usingMatrixParam/getUserById");
        String responseData = target.path("{userId}").resolveTemplate("userId", "User3922")
                .matrixParam("name","John")
                .request().get(String.class);
        System.out.println("Response Data: " + responseData);
    }

    public void callGetMethodUsingBeanParam() {
        System.out.println();
        System.out.println("-------------------- Bean Param --------------------");
        System.out.println("-------------------- Get Method With Bean Param --------------------");
        WebTarget target = client.target(HOST_NAME + CONTEXT_ROOT_URI).path("beanResource/getUserBeanDetails");
        String responseData = target.path("{id}").resolveTemplate("id", "1")
                                    .matrixParam("name", "John")
                                    .matrixParam("age", "30")
                                    .queryParam("address", "Canada")
                                    .request().get(String.class);
        System.out.println("Response Data: " + responseData);
    }

    public void callGetMethodUsingBeanParam2() {
        System.out.println("-------------------- Get Method With Bean Param 2 --------------------");
        WebTarget target = client.target(HOST_NAME + CONTEXT_ROOT_URI).path("beanResource/getUserBeanNameAndAge");
        String responseData = target.matrixParam("name", "John")
                                    .matrixParam("age", "30")
                                    .request().get(String.class);
        System.out.println("Response Data: " + responseData);
    }

    public void callPostMethodUsingBeanParam() {
        System.out.println("-------------------- Post Method With Bean Param --------------------");
        WebTarget target = client.target(HOST_NAME + CONTEXT_ROOT_URI).path("beanResource/addUser");
        MultivaluedMap<String, String> postForm = new MultivaluedHashMap<String, String>();
        String responseData = target.path("{id}").resolveTemplate("id", "1")
                                    .matrixParam("name", "John")
                                    .matrixParam("age", "30")
                                    .queryParam("address", "Vancouver")
                                    .request().post(Entity.form(postForm), String.class);
        System.out.println("Response Data: " + responseData);
    }

    public void callGetMethodUsingProducesAnnotation() {
        System.out.println();
        System.out.println("-------------------- @Produces Annotation --------------------");
        System.out.println("-------------------- Get Method Using @Produces --------------------");
        WebTarget target = client.target(HOST_NAME + CONTEXT_ROOT_URI).path("helloWorld");
        String responseData = target.path("{name}").resolveTemplate("name", "Patri").request(MediaType.TEXT_PLAIN).get(String.class);
        System.out.println("Response Data: " + responseData);
    }

    public void callGetMethodUsingProducesAnnotation2() {
        System.out.println("-------------------- Get Method Using @Produces and xml User --------------------");
        WebTarget target = client.target(HOST_NAME + CONTEXT_ROOT_URI).path("userService/getUserList");
        String responseData = target.request(MediaType.APPLICATION_XML).get(String.class);
        System.out.println("Response Data: " + responseData);
    }

    public void callPostMethodUsingConsumesAnnotation() {
        System.out.println();
        System.out.println("-------------------- @Consumes Annotation --------------------");
        System.out.println("-------------------- Post Method Using @Consumes --------------------");
        WebTarget target = client.target(HOST_NAME + CONTEXT_ROOT_URI).path("postResource/withFormParamAndConsume");

        MultivaluedMap<String, String> postForm = new MultivaluedHashMap<String, String>();
        postForm.add("id", "2");
        postForm.add("name", "John");
        String responseData = target.request().accept(MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(Entity.form(postForm), String.class);
        System.out.println("Response Data: " + responseData);
    }

    public void callPostMethodUsingConsumesAnnotation2() {
        System.out.println("-------------------- Post Method Using @Consumes 2 --------------------");
        WebTarget target = client.target(HOST_NAME + CONTEXT_ROOT_URI).path("postResource/withMultivaluedMapAndReturnResponse");

        MultivaluedMap<String, String> postForm = new MultivaluedHashMap<String, String>();
        postForm.add("param1", "User 6");
        postForm.add("param2", "dogs");
        postForm.add("param2", "cats");
        postForm.add("param2", "birds");
        postForm.add("param3", "22");
        String responseData = target.request().accept(MediaType.APPLICATION_FORM_URLENCODED_TYPE).post(Entity.form(postForm), String.class);
        System.out.println("Response Data: " + responseData);
    }

    public void callGetMethodUsingInvocationBuilder() {
        System.out.println();
        System.out.println("-------------------- Invocation.Builder --------------------");
        System.out.println("-------------------- Get Method Using Invocation.Builder --------------------");
        WebTarget target = client.target(HOST_NAME + CONTEXT_ROOT_URI).path("userService/getUserAgent");
        Invocation.Builder builder = target.request();
        builder.header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON);
        Invocation invocation = builder.buildGet();
        String responseData = invocation.invoke(String.class);
        System.out.println("Response Data: " + responseData);
    }

    public void callPostMethodUsingInvocationBuilder() {
        System.out.println("-------------------- Post Method Using Invocation.Builder --------------------");
        WebTarget target = client.target(HOST_NAME + CONTEXT_ROOT_URI).path("userService/addUser");

        Form form = new Form();
        form.param("name", "John Doe");
        form.param("email", "johndoe@example.com");
        form.param("password", "secret");

        Invocation.Builder builder = target.request(MediaType.APPLICATION_JSON);
        Invocation invocation = builder.buildPost(Entity.form(form));
        Response response = invocation.invoke();

        System.out.println("Status: " + response.getStatus());
        System.out.println("Response Data: " + response.readEntity(String.class));
    }

    public void callMyObject() {
        System.out.println();
        System.out.println("-------------------- Custom Object Method --------------------");
        client.register(PersonReader.class).register(PersonWriter.class);
        WebTarget target = client.target(HOST_NAME + CONTEXT_ROOT_URI).path("personResource/addPerson");
        String responseData = target.request().post(Entity.entity(new Person(1, "John"), Person.PERSON_MIME_TYPE), String.class);
        System.out.println("Response Data: " + responseData);
    }
}
