package com.example.sms;

public class event {
    String clubName , eventName , date;

    public event (String date,String clubName, String eventName){
        this.clubName=clubName;
        this.eventName=eventName;
        this.date=date;

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


}
