package com.example.sms;

public class Attendance extends scheduleController{
    event Event;
     String username1;
     String clubName1;
     String attendence;

    public String getAttendence() {
        return attendence;
    }

    public void setAttendence(String attendence) {
        this.attendence = attendence;
    }

    public Attendance(event Event, String username1, String clubName1 , String attendence){
        this.Event = Event;
        this.username1 = username1;
        this.clubName1 = clubName1;

    }

    public Attendance(String username1, String attendence) {
        this.username1 = username1;
        this.attendence = attendence;
    }

    public event getEventName() {
        return Event;
    }

    public void setEventName(String eventName) {
        this.Event = Event;
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
