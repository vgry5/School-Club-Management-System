package com.example.sms;

import java.util.ArrayList;

public class club {
    //Encapsulation
    private  String name;
    private String description;
    private Staff advisor;
    private int no_students;
    private ArrayList<event> events = new ArrayList<>();

    //Constructor
    public club (String name,String description,Staff advisor,int no_students){
        this.name = name;
        this.description = description;
        this.advisor = advisor;
        this.no_students = no_students;
    }

    public club (String name,String description,Staff advisor){
        this.name = name;
        this.description = description;
        this.advisor = advisor;
    }

    //Getters and Setters(Encapsulation)

    public  String getName() {
        return name;
    }
    public String setName(String name) {
        this.name = name;
        return name;
    }
    public String getAdvisorUsername() {
        return advisor.getUsername();
    }
    public String getDescription() {
        return description;
    }
    public String setDescription(String description) {
        this.description = description;
        return description;
    }

    public int getNo_students() {
        return no_students;
    }

    public void setNo_students(int no_students) {
        this.no_students = no_students;
    }

    public void addStudent() {
        no_students++;
    }

    public void removeStudent() {
        no_students--;
    }

    }
