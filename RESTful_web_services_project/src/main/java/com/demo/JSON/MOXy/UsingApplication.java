package com.demo.JSON.MOXy;

import org.eclipse.persistence.jaxb.rs.MOXyJsonProvider;

import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

public class UsingApplication extends Application {
    @Override
    public Set<Class<?>> getClasses() {
        System.out.println("getClasses method");
        Set<Class<?>> s = new HashSet<Class<?>>();
        s.add(UserResource.class);
        return s;
    }

    @Override
    public Set<Object> getSingletons() {
        System.out.println("getSingletons method");
        MOXyJsonProvider moxyJsonProvider = new MOXyJsonProvider();
        moxyJsonProvider.setWrapperAsArrayName(true);
        HashSet<Object> set = new HashSet<Object>(1);
        set.add(moxyJsonProvider);
        return set;
    }
}
