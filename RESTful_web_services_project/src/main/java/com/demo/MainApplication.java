package com.demo;

import com.demo.bean.BeanResource;
import com.demo.bean.UserBean;
import com.demo.httpMethods.DeleteResource;
import com.demo.httpMethods.GetResource;
import com.demo.httpMethods.PostResource;
import com.demo.httpMethods.PutResource;
import com.demo.parameterAnnotations.UserResource;
import com.demo.subresources.AddressResource;
import com.demo.subresources.ExampleResource;
import com.demo.subresources.OrderResource;
import org.glassfish.jersey.server.ResourceConfig;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

// class for deploying this RESTful web service using the abstract Application class

public class MainApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> ourSetOfRootResourceClasses = new HashSet<Class<?>>();
        ourSetOfRootResourceClasses.add(HelloWorldResource.class);
        ourSetOfRootResourceClasses.add(HelloWorldResourceWithVariableInTheURI.class);
        ourSetOfRootResourceClasses.add(HelloWorldResourceWithVariableInTheURI2.class);
        ourSetOfRootResourceClasses.add(HelloWorldResourceWithVariableWithRegularExpression.class);
        ourSetOfRootResourceClasses.add(GetResource.class);
        ourSetOfRootResourceClasses.add(PostResource.class);
        ourSetOfRootResourceClasses.add(PutResource.class);
        ourSetOfRootResourceClasses.add(DeleteResource.class);
        ourSetOfRootResourceClasses.add(BeanResource.class);
        ourSetOfRootResourceClasses.add(UserBean.class);
        ourSetOfRootResourceClasses.add(UserResource.class);
        ourSetOfRootResourceClasses.add(AddressResource.class);
        ourSetOfRootResourceClasses.add(ExampleResource.class);
        ourSetOfRootResourceClasses.add(OrderResource.class);
        return ourSetOfRootResourceClasses;
    }
}

// replace the above class with this one
// class for deploying this RESTful web service using the abstract ResourceConfig class

/*public class MainApplication extends ResourceConfig {
    public MainApplication() {
        packages("com.demo");
    }
}*/
