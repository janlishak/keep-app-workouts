package com.janlishak.keepappworkouts.model;

public class Session {
    int id;
    String name;

    public Session(String name) {
        this.id = -1;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
