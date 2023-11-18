package com.example.sms;

public class Staff {
    public String firstname;
    public String lastname;
    public String staffid;
    public String username;
    public String password;

    public String getClub() {
        return Club;
    }

    public void setClub(String club) {
        Club = club;
    }

    public String Club;

    public Staff(String firstname, String lastname, String staffid, String username, String password) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.staffid = staffid;
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

    public String getStaffid() {
        return staffid;
    }

    public void setStaffid(String staffid) {
        this.staffid = staffid;
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
}
