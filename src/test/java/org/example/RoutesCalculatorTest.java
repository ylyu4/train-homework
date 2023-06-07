package org.example;

import org.example.exception.InvalidArgumentException;
import org.example.exception.NullAgumentException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    void should_throw_invalid_argument_exception_when_argument_is_null() {
        // then
        assertThrows(NullAgumentException.class, () -> RoutesCalculator.calculateRouteAmountWithMaximumStop(null));
    }

    @Test
    void should_throw_invalid_argument_exception_when_argument_size_is_exceed() {
        // then
        assertThrows(InvalidArgumentException.class, () -> RoutesCalculator.calculateRouteAmountWithMaximumStop("A", "C", "3", "4"));
    }

    @Test
    void should_throw_invalid_argument_exception_when_argument_type_is_wrong() {
        // then
        assertThrows(InvalidArgumentException.class, () -> RoutesCalculator.calculateRouteAmountWithMaximumStop("A", "C", "B"));
    }


}
