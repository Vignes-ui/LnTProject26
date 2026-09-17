package com.lnt;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AppTest {

    @Test
    void calculateTotalShouldReturnCorrectValue() {

        double result = App.calculateTotal(100.0, 3);

        assertEquals(300.0, result);
    }
}