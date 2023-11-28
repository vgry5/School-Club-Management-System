package com.example.sms;

import java.util.ArrayList;
import java.util.SplittableRandom;

public class Students extends Person {
    private int age;
    private String admissionNumber;

    public ArrayList<club> clubs = new ArrayList<club>();

    public Students(String firstname, String lastname, int age, String admissionNumber, String username, String password) {
        super(firstname, lastname, username, password);
        this.age = age;
        this.admissionNumber = admissionNumber;
    }

    public Students(String firstname, String lastname, int age, String admissionNumber, String username, String password, ArrayList<club> clubs) {
        super(firstname, lastname, username, password);
        this.age = age;
        this.admissionNumber = admissionNumber;
        this.clubs = clubs;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setAdmissionNumber(String admissionNumber) {
        this.admissionNumber = admissionNumber;
    }

    public String getAdmissionNumber() {
        return admissionNumber;
    }

    public ArrayList<club> getClubs() {
        return clubs;
    }
    public void setClubs(ArrayList<club> clubs) {
        this.clubs = clubs;
    }
    public void addClub(club club) {
        clubs.add(club);
    }

    public club removeClub(int index){
        return clubs.remove(index);
    }

    public String clubString() {
        StringBuilder result = new StringBuilder();
        for (club element : clubs) {
            result.append(element.getName()).append(",");
        }

        // Remove the trailing space
        if (result.length() > 0) {
            result.setLength(result.length() - 1);
        }
        String string = String.valueOf(result);
        return string;
    }
}
