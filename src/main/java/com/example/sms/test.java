package com.example.sms;

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
}

