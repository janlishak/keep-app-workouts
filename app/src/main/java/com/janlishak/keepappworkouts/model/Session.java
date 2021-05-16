package com.janlishak.keepappworkouts.model;

import java.io.Serializable;

public class Session implements Serializable {
    String name;

    public Session(){};

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
