package com.example.sms;

import java.util.ArrayList;
import java.util.Objects;

public class club {
    //Encapsulation
    private  String name;
    private String description;
    private String advisorID;
    private int no_students;

    private Staff advisor;

    private ArrayList<event> events = new ArrayList<>();

    public ArrayList<event> getEvent() {
        return events;
    }

    public void setEvent(ArrayList<com.example.sms.event> event) {
        this.events = event;
    }

    public Staff getAdvisor() {
        return advisor;
    }

    public void setAdvisor(Staff advisor) {
        this.advisor = advisor;
    }

    //Constructor
    public club (String name,String description,String advisorID,int no_students){
        this.name = name;
        this.description = description;
        this.advisorID = advisorID;
        this.no_students = no_students;
    }

    public club (String name,int no_students,String advisorID){
        this.name = name;
        this.no_students = no_students;
        this.advisorID = advisorID;
    }

    //Getters and Setters(Encapsulation)

    public  String getName() {
        return name;
    }
    public String setName(String name) {
        this.name = name;
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String setDescription(String description) {
        this.description = description;
        return description;
    }
    public String  getAdvisorID() {
        return advisorID;
    }
    public void setAdvisorID(String advisorID) {
        this.advisorID = advisorID;
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

    public void addEvent(event event) {
        events.add(event);
    }
    public String eventString() {
        StringBuilder result = new StringBuilder();
        for (event element : events) {
            result.append(element.getEventName()).append(",");
        }

        // Remove the trailing space
        if (result.length() > 0) {
            result.setLength(result.length() - 1);
        }
        String string = String.valueOf(result);
        return string;
    }
    }

