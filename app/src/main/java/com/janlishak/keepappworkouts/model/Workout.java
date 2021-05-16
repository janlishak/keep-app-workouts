package com.janlishak.keepappworkouts.model;

import java.io.Serializable;

public class Workout implements Serializable {
    private String name;
    private String id;

    public Workout(){}
    public Workout(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
