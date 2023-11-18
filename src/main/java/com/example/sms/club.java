package com.example.sms;

import java.util.ArrayList;

public class club {
    //Encapsulation
    private  String name;
    private String description;
    private String advisorID;

    private int no_students;
    public ArrayList<Students> students = new ArrayList<>();



    //Constructor
    public club (String name,String description,String advisorID,int no_students){
        this.name = name;
        this.description = description;
        this.advisorID = advisorID;
        this.no_students = no_students;
    }

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
    public ArrayList<Students> getStudents(){
        return students;
    }
    public void setStudents(ArrayList<Students> students){this.students = students;}
    public Students removeStudent(int index){return students.remove(index);}
    public String StudentString(){
        StringBuilder result = new StringBuilder();
        for (Students element : students){
            result.append(element.getUsername()).append(",");
        }
        if (result.length()>0){
            result.setLength(result.length() -1);
        }
        String string = String.valueOf(result);
        return string;
    }
}
