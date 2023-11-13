package com.example.sms;

public class Admins {
    String Firstname;
    String Lastname;
    String Username;
    String Password;

    public Admins(String firstname, String lastname, String username, String password) {
        Firstname = firstname;
        Lastname = lastname;
        Username = username;
        Password = password;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
