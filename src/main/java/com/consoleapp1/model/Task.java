package com.consoleapp1.model;

public class Task {
    int id;
    String name;
    String assigned;
    int priority;
    String description;
    boolean completed;

    public Task(int id, String name, String assigned, int priority, String description, boolean completed) {
        this.id = id;
        this.name = name;
        this.assigned = assigned;
        this.priority = priority;
        this.description = description;
        this.completed = completed;
    }

    public Task() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAssigned() {
        return assigned;
    }

    public void setAssigned(String assigned) {
        this.assigned = assigned;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id '" + id + '\'' +
                ", name " + name + '\'' +
                ", assigned" + assigned +
                ", priority '" + priority + '\'' +
                ", description='" + description + '\'' +
                ", completed " + completed +
                '}';


    }

}