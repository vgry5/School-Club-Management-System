package com.example.sms;

import org.junit.jupiter.api.Test;

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
    void testevent() {
        test test = new test();
        assertTrue(test.eventValidation("", "ilyas", "fsef", "3328", "luk23"));
    }
}