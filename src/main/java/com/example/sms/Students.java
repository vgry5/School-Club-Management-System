package com.example.sms;

public class Students {
    public String firstname;
    public String lastname;
    public int age;
    public String username;
    public String password;
    public int admissionNumber;




    public Students(String firstname, String lastname, int age, String username, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.admissionNumber = admissionNumber;
        this.username = username;
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    public void setAdmissionNumber(int admissionNumber) {
        this.admissionNumber = admissionNumber;
    }
    public int getAdmissionNumber() {
        return admissionNumber;
    }

}
