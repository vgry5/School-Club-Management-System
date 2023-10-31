package com.example.sms;

public class club {
    private String name;
    private String description;
    private int advisorID;

    //Constructor
    public club (String name,String description,int advisorID){
        this.name = name;
        this.description = description;
        this.advisorID = advisorID;
    }
    //Getters and Setters(Encapsulation)

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
    public int getAdvisorID() {
        return advisorID;
    }
    public void setAdvisorID(int advisorID) {
        this.advisorID = advisorID;
    }
}
