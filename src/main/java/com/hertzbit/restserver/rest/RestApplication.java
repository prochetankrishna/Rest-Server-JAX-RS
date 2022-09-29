package com.hertzbit.restserver.rest;

import com.hertzbit.restserver.rest.service.StudentResource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/api")
public class RestApplication extends Application {

    private Set<Object> singletons = new HashSet<>();
    private Set<Class<?>> empty = new HashSet<>();

    public RestApplication() {
        this.singletons.add(new StudentResource());
    }

    @Override
    public Set<Class<?>> getClasses() {
        return empty;
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }
}
