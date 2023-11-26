package com.example.sms;
public class event {
    String Description, eventName , date,EventType;
    club Club ;

    public club getClub() {
        return Club;
    }

    public void setClub(club club) {
        Club = club;
    }

    public Attendance getAttendance() {
        return attendance;
    }

    public void setAttendance(Attendance attendance) {
        this.attendance = attendance;
    }

    Attendance attendance;


    public  event (String eventName ,club Club, String eventType ,String date ,String Description  ){
        this.Club=Club;
        this.eventName=eventName;
        this.EventType = eventType;
        this.date=date;
        this.Description = Description;
    }

    public event(club Club, String eventName, String eventType) {
        this.Club = Club;
        this.eventName = eventName;
        this.EventType = eventType;
    }

    public club getClubName() {
        return Club;
    }

    public String getEventName() {
        return eventName;
    }

    public void setClubName(String clubName) {
        this.Club = Club;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }
    public String getEventType() {
        return EventType;
    }

    public void setEventType(String eventType) {
        EventType = eventType;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }
    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }


}