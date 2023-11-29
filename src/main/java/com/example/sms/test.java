package com.example.sms;

import java.sql.SQLException;

public class test {
    public void createStudent(String firstname, String lastname, int age, String adno, String username, String password) {
        Students student = new Students(firstname, lastname, age, adno, username, password);
        OOPCoursework.studentList.add(student);
    }

    public boolean checkLogin(String username, String password) {
        for (int i = 0 ; i < OOPCoursework.studentList.size(); i++) {
            if (username.equals(OOPCoursework.studentList.get(i).getUsername()) && password.equals(OOPCoursework.studentList.get(i).getPassword())) {
                return true;
            }

    }
        return false;

    }
    public void ScheduleEvent(String eventName, String clubName, String eventType, String date, String Description){
        event event =new event(eventName,clubName,eventType,date,Description);
        OOPCoursework.scheduleEvents.add(event);
    }
    public boolean eventValidation(String eventName , String club, String eventType, String date , String description) throws SQLException {

        for (int i =0 ;i<OOPCoursework.scheduleEvents.size();i++){
            if (OOPCoursework.scheduleEvents.get(i).getEventName().equals(eventName)){
                return false;
            }
        }
        if (eventName.isEmpty()){
            return false;
        }
        if (club == null){
            return false;}
        if (eventType  == null){
            return false;}
        if ( date == null){
            return false;}
        if (description.isEmpty()){
            return false;
        }
        return true;
    }
    public boolean clubcreationValidation(String Clubname, String Clubdescrip) {
        boolean ResultClubName = Clubname.matches("[a-zA-Z ]+$");//Checks if the club name contains only letters and stores the result of the checking in a boolean
        boolean ResultDescription = Clubdescrip.matches("[a-zA-Z ]+");//Checks if the club description contains only letters and stores the result of the checking in a boolean
        for(int x=0;x<OOPCoursework.clublist.size();x++){
            if(OOPCoursework.clublist.get(x).getName().equals(Clubname)){
                return false;
            }
        }
        if (!ResultClubName) {
            return false;
        }
        if (!ResultDescription) {
            return false;
        }
        return true;
    }
    public boolean clubcreationValidationInCharge(String Clubname, String Clubdescrip) {
        boolean ResultClubName = Clubname.matches("[a-zA-Z ]+$");//Checks if the club name contains only letters and stores the result of the checking in a boolean
        boolean ResultDescription = Clubdescrip.matches("[a-zA-Z ]+");//Checks if the club description contains only letters and stores the result of the checking in a boolean
        if (!ResultClubName) {
            return false;
        }
        if (!ResultDescription) {

            return false;
        }
        return true;
    }
    public void ClubCreation( String Clubname, String Clubdescrip, String AdvisorID, int no_students){
        club Club =new club(Clubname,Clubdescrip,AdvisorID,no_students);
        OOPCoursework.clublist.add(Club);
    }
}

