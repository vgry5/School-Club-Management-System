package com.example.sms;
public class event {
    String Description,clubName , eventName , date;
    public  event (String eventName , String clubName , String date , String Description){
        this.clubName=clubName;
        this.eventName=eventName;
        this.date=date;
        this.Description = Description;
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
    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }


}