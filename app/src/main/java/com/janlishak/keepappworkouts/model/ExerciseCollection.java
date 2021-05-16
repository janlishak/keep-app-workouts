package com.janlishak.keepappworkouts.model;

import java.io.Serializable;

public class ExerciseCollection implements Serializable {
    private String id;
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ExerciseCollection(){}

    public ExerciseCollection(String name) {
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
        return "ExerciseCollection{" +
                "name='" + name + '\'' +
                '}';
    }
}
