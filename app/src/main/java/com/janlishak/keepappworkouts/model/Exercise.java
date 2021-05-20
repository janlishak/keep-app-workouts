package com.janlishak.keepappworkouts.model;

import java.io.Serializable;

public class Exercise implements Serializable {
    String id;
    String name;
    String description;
    String imageLink;
    String imageDeleteHash;

    public Exercise() { }

    public String getImageDeleteHash() { return imageDeleteHash; }

    public void setImageDeleteHash(String imageDeleteHash) { this.imageDeleteHash = imageDeleteHash; }

    public String getImageLink() { return imageLink; }

    public void setImageLink(String imageLink) { this.imageLink = imageLink; }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Exercise(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "Exercise{" + "name='" + name + '\'' + ", description='" + description + '\'' + '}';
    }
}
