package com.janlishak.keepappworkouts.model;

public class Session {
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
}
