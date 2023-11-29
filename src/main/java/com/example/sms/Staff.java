package com.example.sms;

public class Staff extends Person {
    public String staffid;

    public club getClub() {
        return Club;
    }

    public void setClub(club club) {
        Club = club;
    }

    public club Club;

    public Staff(String firstname, String lastname, String staffid, String username, String password) {
        super(firstname, lastname, username, password);
        this.staffid = staffid;
    }

    public String getStaffid() {
        return staffid;
    }

    public void setStaffid(String staffid) {
        this.staffid = staffid;
    }


}
