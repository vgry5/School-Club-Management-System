package com.example.sms;

public class Staff extends Person {
    private String staffid;

    public club getClub() {
        return Club;
    }

    public void setClub(club club) {
        Club = club;
    }

    private club Club;

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
