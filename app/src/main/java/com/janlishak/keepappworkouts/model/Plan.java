package com.janlishak.keepappworkouts.model;

import java.io.Serializable;

public class Plan implements Serializable {
    String name;
    String description;

    public Plan(){};

    public Plan(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Plan(String name, String description, String text) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Plan{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}