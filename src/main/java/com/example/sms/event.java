package com.example.sms;
public class event {
    String Location,clubName , eventName , date, EType;


    public  event (String eventName , String clubName , String date , String location , String EType){
        this.clubName=clubName;
        this.eventName=eventName;
        this.date=date;
        this.Location = location;
        this.EType = EType;

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

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }
    public String getLocation() {
        return Location;
    }

    public void setLocation(String location) {
        Location = location;
    }

    public String getEType() {
        return EType;
    }

    public void setEType(String EType) {
        this.EType = EType;
    }
}