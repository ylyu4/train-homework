package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DistanceCalculatorTest {

    @Test
    void should_return_correct_distance_when_there_are_two_valid_location() {
        // given
        String start = "A";
        String end = "B";

        // when
        Integer distance = DistanceCalculator.calculate(start, end);

        // then
        assertEquals(5, distance);
    }
}
