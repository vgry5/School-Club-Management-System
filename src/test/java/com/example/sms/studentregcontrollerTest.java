package com.example.sms;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class studentregcontrollerTest {

    @Test
    void test() {
        studentregcontroller test = new studentregcontroller();
        assertTrue(test.studentDetailsValidate("lukmal", "ilyas", "19", "3328", "luk23", "11111111"));
    }
}