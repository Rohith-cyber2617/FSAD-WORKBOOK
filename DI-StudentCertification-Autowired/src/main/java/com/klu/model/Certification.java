package com.klu.model;

import org.springframework.stereotype.Component;

@Component
public class Certification {

    private int id;
    private String name;
    private String dateOfCompletion;

    public Certification() {
        this.id = 301;
        this.name = "Java Programming";
        this.dateOfCompletion = "20-01-2025";
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDateOfCompletion() {
        return dateOfCompletion;
    }
}

