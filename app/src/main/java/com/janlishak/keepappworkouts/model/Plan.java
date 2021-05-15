package com.janlishak.keepappworkouts.model;

public class Plan {
    String name;
    String description;

    public Plan(){};

    public Plan(String name, String title, String description) {
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
}