package com.example.sms;

public class Attendance {
    public String eventName;
    public String username1;
    public String clubName1;

    public Attendance(String eventName, String username1, String clubName1){
        this.eventName = eventName;
        this.username1 = username1;
        this.clubName1 = clubName1;

    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getUsername1() {
        return username1;
    }

    public void setUsername1(String username1) {
        this.username1 = username1;
    }

    public String getClubName1() {
        return clubName1;
    }

    public void setClubName1(String clubName1) {
        this.clubName1 = clubName1;
    }
}
