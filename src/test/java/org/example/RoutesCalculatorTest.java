package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoutesCalculatorTest {

    @Test
    void should_return_correct_route_number_first_scenario() {
        // when
        Integer result = RoutesCalculator.calculateRouteAmountWithMaximumStop("C", "C", "3");

        // then
        assertEquals(2, result);
    }

    @Test
    void should_return_correct_route_numbers_second_scenario() {
        // when
        Integer result = RoutesCalculator.calculateRouteAmountWithMaximumStop("A", "C", "3");

        // then
        assertEquals(3, result);
    }

    @Test
    void should_return_zero_route_numbers_when_there_is_no_routes_in_given_stops() {
        // when
        Integer result = RoutesCalculator.calculateRouteAmountWithMaximumStop("C", "A", "3");

        // then
        assertEquals(0, result);
    }

}
