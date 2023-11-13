package com.example.sms;

public class club {
    //Encapsulation
    private  String name;
    private String description;
    private String advisorID;

    //Constructor
    public club (String name,String description,String advisorID){
        this.name = name;
        this.description = description;
        this.advisorID = advisorID;
    }
    //Getters and Setters(Encapsulation)

    public  String getName() {
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
    public String  getAdvisorID() {
        return advisorID;
    }
    public void setAdvisorID(String advisorID) {
        this.advisorID = advisorID;
    }
}
