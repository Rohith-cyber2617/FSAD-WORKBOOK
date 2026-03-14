package com.klu.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Student {

    private int id;
    private String name;
    private String gender;

    @Autowired
    private Certification certification;

    public Student() {
        this.id = 101;
        this.name = "Avinash";
        this.gender = "Male";
    }

    public void display() {
        System.out.println("Student Details");
        System.out.println("Id: " + id);
        System.out.println("Name: " + name);
        System.out.println("Gender: " + gender);

        System.out.println("Certification Details");
        System.out.println("Certification Id: " + certification.getId());
        System.out.println("Certification Name: " + certification.getName());
        System.out.println("Date Of Completion: " + certification.getDateOfCompletion());
    }
}

