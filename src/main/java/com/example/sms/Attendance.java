package com.example.sms;

public class Attendance extends scheduleController{
    String eventName;
     String username1;
     String clubName1;
     String attendence;
     String date;

    public String getAttendence() {
        return attendence;
    }

    public void setAttendence(String attendence) {
        this.attendence = attendence;
    }

    public Attendance(String eventName,String clubName1 ,String date, String username1,  String attendence){
        this.eventName = eventName;
        this.clubName1 = clubName1;
        this.date = date;
        this.username1 = username1;
        this.attendence = attendence;


    }

    public Attendance(String username1, String attendence) {
        this.username1 = username1;
        this.attendence = attendence;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
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
