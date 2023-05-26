package org.example;

import org.example.exception.InsufficientArgumentsException;
import org.example.exception.NullAgumentException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DistanceCalculatorTest {

    @Test
    void should_return_correct_distance_when_there_are_two_valid_location() {
        // given
        String start = "A";
        String end = "B";

        // when
        Object distance = DistanceCalculator.calculate(start, end);

        // then
        assertEquals(5, distance);
    }

    @Test
    void should_return_no_such_route_exception_when_route_does_not_exist() {
        // given
        String start = "B";
        String end = "A";

        // when
        Object result = DistanceCalculator.calculate(start, end);

        // then
        assertEquals("NO SUCH ROUTE", result);
    }

    @ParameterizedTest
    @ValueSource(strings = {"A", ""})
    void should_throw_insufficient_arguments_exception_when_there_are_less_than_two_location(String strings) {
        // given
        assertThrows(InsufficientArgumentsException.class,
                () -> DistanceCalculator.calculate(strings.replace(" ", ",")));
    }

    @Test
    void should_throw_null_argument_exception_when_input_is_null() {
        assertThrows(NullAgumentException.class, () -> DistanceCalculator.calculate(null));
    }

    @Test
    void should_return_correct_distance_when_there_are_multiple_valid_location() {
        // given
        String position1 = "A";
        String position2 = "D";
        String position3 = "E";

        // when
        Object distance = DistanceCalculator.calculate(position1, position2, position3);

        // then
        assertEquals(11, distance);
    }

    @Test
    void should_return_no_such_route_when_many_routes_do_not_exist() {
        // given
        String first = "A";
        String second = "F";
        String third = "G";

        // when
        Object result = DistanceCalculator.calculate(first, second, third);

        // then
        assertEquals("NO SUCH ROUTE", result);
    }
}
