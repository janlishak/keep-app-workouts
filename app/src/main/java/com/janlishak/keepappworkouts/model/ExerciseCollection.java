package com.janlishak.keepappworkouts.model;

import java.io.Serializable;

public class ExerciseCollection implements Serializable {
    private String name;

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
