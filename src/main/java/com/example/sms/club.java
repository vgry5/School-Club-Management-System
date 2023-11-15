package com.example.sms;

public class club {
    //Encapsulation
    private  String name;
    private String description;
    private String advisorID;
    private int no_students;

    //Constructor
    public club (String name,String description,String advisorID,int no_students){
        this.name = name;
        this.description = description;
        this.advisorID = advisorID;
        this.no_students = no_students;
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
    public int getNo_students() {return no_students;}
    public void setNo_students(int no_students) {this.no_students = no_students;
    }
    public void Edit (){}//Method to delete an existing student and change club name

}
