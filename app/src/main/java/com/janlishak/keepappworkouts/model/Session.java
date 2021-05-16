package com.janlishak.keepappworkouts.model;

import java.io.Serializable;

public class Session implements Serializable {
    private String id;
    private String name;

    public Session(String name){
        this.name = name;
    };

    public Session(){};

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

    @Override
    public String toString() { return "Session{" + "name='" + name + '\'' + '}'; }
}
