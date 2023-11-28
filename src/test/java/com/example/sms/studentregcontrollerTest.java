package com.example.sms;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class studentregcontrollerTest {

    @Test
    void test() {
        studentregcontroller test = new studentregcontroller();
        assertTrue(test.studentDetailsValidatee("lukmal", "ilyas", "19", "3328", "luk23", "11111111"));
    }
    @Test
    void testfirstname() {
        studentregcontroller test = new studentregcontroller();
        assertTrue(test.studentDetailsValidatee("lukmal5", "ilyas", "19", "3328", "luk23", "11111111"));
    }

    @Test
    void testlastname() {
        studentregcontroller test = new studentregcontroller();
        assertTrue(test.studentDetailsValidatee("lukmal", "", "19", "3328", "luk23", "11111111"));
    }

    @Test
    void testage() {
        studentregcontroller test = new studentregcontroller();
        assertTrue(test.studentDetailsValidatee("lukmal", "ilyas", "22", "3328", "luk23", "11111111"));
    }

    @Test
    void testage1() {
        studentregcontroller test = new studentregcontroller();
        assertTrue(test.studentDetailsValidatee("lukmal", "ilyas", "ss", "3328", "luk23", "11111111"));
    }

    @Test
    void testage2() {
        studentregcontroller test = new studentregcontroller();
        assertTrue(test.studentDetailsValidatee("lukmal", "ilyas", "", "3328", "luk23", "11111111"));
    }

    @Test
    void testadnumber() {
        studentregcontroller test = new studentregcontroller();
        assertTrue(test.studentDetailsValidatee
("lukmal", "ilyas", "22", "fff", "luk23", "11111111"));
    }

    @Test
    void testadnumber1() {
        studentregcontroller test = new studentregcontroller();
        assertTrue(test.studentDetailsValidatee
                ("lukmal", "ilyas", "22", " ", "luk23", "11111111"));
    }

    @Test
    void testusername() {
        studentregcontroller test = new studentregcontroller();
        assertTrue(test.studentDetailsValidatee
("lukmal", "ilyas", "19", "3328", "luk23%", "11111111"));
    }

    @Test
    void testusername1() {
        studentregcontroller test = new studentregcontroller();
        assertTrue(test.studentDetailsValidatee
("lukmal", "ilyas", "19", "3328", " ", "11111111"));
    }

    @Test
    void testpassword() {
        studentregcontroller test = new studentregcontroller();
        assertTrue(test.studentDetailsValidatee
         ("lukmal", "ilyas", "19", "3328", "luk23", "1111111"));
    }

    @Test
    void testpassword1() {
        studentregcontroller test = new studentregcontroller();
        assertTrue(test.studentDetailsValidatee
("lukmal", "ilyas", "19", "3328", "luk23", " "));
    }

    @Test
    void testduplication() {
        studentregcontroller test = new studentregcontroller();
        assertTrue(test.studentDetailsValidatee("lukmal", "ilyas", "19", "3328", "luk23", "11111111"));
    }

    @Test
    void testlogin() {
        test test = new test();
        test.createStudent("lukmal", "ilyas", 19, "3328", "luk23", "11111111");
        assertTrue(test.checkLogin("luk23", "222"));
    }
    @Test
    void testschedule() throws SQLException {
        test test =new test();
        assertTrue(test.eventValidation("","environment Club","Activity","2022-10-10","planting trees"));
    }

    @Test
    void testscheduleDuplicate() throws SQLException {
        test test =new test();
        test.ScheduleEvent("friendly football encounter 23","football","Event","2022-10-10","at school grounds");
        assertTrue(test.eventValidation("friendly football encounter 23","football","Event","2022-10-10","at school grounds"));
    }
    @Test
    void testschedule1() throws SQLException {
        test test =new test();
        assertTrue(test.eventValidation("tree planting","environment Club",null,"2022-10-10","planting trees"));
    }
    @Test
    void testschedule2() throws SQLException {
        test test =new test();
        assertTrue(test.eventValidation("tree planting",null,"Activity","2022-10-10","planting trees"));
    }
    @Test
    void testschedule3() throws SQLException {
        test test =new test();
        assertTrue(test.eventValidation("tree planting","environment Club","Activity",null,"planting trees"));
    }
    @Test
    void testschedule4() throws SQLException {
        test test =new test();
        assertTrue(test.eventValidation("tree planting","environment Club","Activity","2022-10-10",""));
    }


}