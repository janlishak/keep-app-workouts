package com.janlishak.keepappworkouts.model;

public class SessionExercise extends Exercise{
    private Integer sets;
    private Integer reps;
    private String rest;
    private boolean completed;

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public SessionExercise(){}

    public SessionExercise(String name, String description, Integer sets, Integer reps, String rest) {
        super(name, description);
        this.sets = sets;
        this.reps = reps;
        this.rest = rest;
    }

    public SessionExercise(String name, String description, Integer sets, Integer reps, String rest, boolean completed){
        super(name, description);
        this.sets = sets;
        this.reps = reps;
        this.rest = rest;
        this.completed = completed;
    }

    public SessionExercise(Exercise exercise, Integer sets, Integer reps, String rest) {
        super(exercise.getName(), exercise.getDescription());
        this.reps = reps;
        this.sets = sets;
        this.rest = rest;
    }


    public Integer getSets() {
        return sets;
    }

    public void setSets(Integer sets) {
        this.sets = sets;
    }

    public Integer getReps() {
        return reps;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public String getRest() {
        return rest;
    }

    public void setRest(String rest) {
        this.rest = rest;
    }
}
