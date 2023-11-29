package com.example.sms;
public class event {
    String Description, clubName, eventName, date, EventType;
    club club;



    public event(String eventName, String clubName, String eventType, String date, String Description) {
        this.clubName = clubName;
        this.eventName = eventName;
        this.EventType = eventType;
        this.date = date;
        this.Description = Description;
    }

    public event(String clubName, String eventName, String eventType) {
        this.clubName = clubName;
        this.eventName = eventName;
        this.EventType = eventType;
    }


    public String getClubName() {
        return clubName;
    }

    public String getEventName() {
        return eventName;
    }

    public void setClubName(String clubName) {
        this.clubName = clubName;
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

    public com.example.sms.club getClub() {
        return club;
    }

    public void setClub(com.example.sms.club club) {
        this.club = club;
    }
}

