package com.janlishak.keepappworkouts.model;

import java.io.Serializable;

public class Session implements Serializable {
    String id;
    String name;

    public Session(){};

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Session(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Session{" +
                "name='" + name + '\'' +
                '}';
    }
}
