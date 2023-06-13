package org.example;

import org.example.exception.InsufficientArgumentsException;
import org.example.exception.InvalidArgumentException;
import org.example.exception.NoSuchRouteException;
import org.example.exception.NullArgumentException;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class TrainStationsTest {

    public static final String GRAPH = "AB5,BC4,CD8,DC8,DE6,AD5,CE2,EB3,AE7";
    private TrainStations trainStations = new TrainStations(GRAPH);

    @Nested
    class DistanceCalculatorTest {
        @Test
        void should_return_correct_distance_when_there_are_two_valid_location() {
            // given
            String start = "A";
            String end = "B";

            // when
            Object distance = trainStations.calculateDistance(start, end);

            // then
            assertEquals(5, distance);
        }

        @Test
        void should_return_no_such_route_exception_when_route_does_not_exist() {
            // given
            String start = "B";
            String end = "A";

            // when
            Object result = trainStations.calculateDistance(start, end);

            // then
            assertEquals("NO SUCH ROUTE", result);
        }

        @ParameterizedTest
        @ValueSource(strings = {"A", ""})
        void should_throw_insufficient_arguments_exception_when_there_are_less_than_two_location(String strings) {
            // given
            assertThrows(InsufficientArgumentsException.class,
                    () -> trainStations.calculateDistance(strings.replace(" ", ",")));
        }

        @Test
        void should_throw_null_argument_exception_when_input_is_null() {
            assertThrows(NullArgumentException.class, () -> trainStations.calculateDistance(null));
        }

        @Test
        void should_return_correct_distance_when_there_are_multiple_valid_location() {
            // given
            String position1 = "A";
            String position2 = "D";
            String position3 = "E";

            // when
            Object distance = trainStations.calculateDistance(position1, position2, position3);

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
            Object result = trainStations.calculateDistance(first, second, third);

            // then
            assertEquals("NO SUCH ROUTE", result);
        }
    }

    @Nested
    class RouteCalculatorWithMaximumStopsTest {
        @Test
        void should_return_correct_route_numbers_when_given_maximum_stops_first_scenario() {
            // when
            Integer result = trainStations.calculateRouteNumbersWithMaximumStop("C", "C", "3");

            // then
            assertEquals(2, result);
        }

        @Test
        void should_return_correct_route_numbers_when_given_maximum_stops_second_scenario() {
            // when
            Integer result = trainStations.calculateRouteNumbersWithMaximumStop("A", "C", "3");

            // then
            assertEquals(3, result);
        }

        @Test
        void should_return_zero_route_numbers_when_there_is_no_routes_in_given_stops() {
            // when
            Integer result = trainStations.calculateRouteNumbersWithMaximumStop("C", "A", "3");

            // then
            assertEquals(0, result);
        }

        @Test
        void should_throw_invalid_argument_exception_when_argument_is_null() {
            // then
            assertThrows(NullArgumentException.class, () -> trainStations.calculateRouteNumbersWithMaximumStop(null));
        }

        @Test
        void should_throw_invalid_argument_exception_when_argument_size_is_exceed() {
            // then
            assertThrows(InvalidArgumentException.class, () -> trainStations.calculateRouteNumbersWithMaximumStop("A", "C", "3", "4"));
        }

        @Test
        void should_throw_invalid_argument_exception_when_argument_type_is_wrong() {
            // then
            assertThrows(InvalidArgumentException.class, () -> trainStations.calculateRouteNumbersWithMaximumStop("A", "C", "B"));
        }
    }

    @Nested
    class RouteCalculatorWithFixedStopsTest{
        @Test
        void should_return_correct_route_numbers_when_given_fixed_stops_first_scenario() {
            // when
            Integer result = trainStations.calculateRouteNumbersWithFixedStop("A", "C", "4");

            // then
            assertEquals(3, result);
        }

        @Test
        void should_return_correct_route_numbers_when_given_fixed_stops_second_scenario() {
            // when
            Integer result = trainStations.calculateRouteNumbersWithFixedStop("A", "C", "2");

            // then
            assertEquals(2, result);
        }


        @Test
        void should_return_zero_route_numbers_when_there_is_no_routes_in_given_stops() {
            // when
            Integer result = trainStations.calculateRouteNumbersWithFixedStop("C", "A", "3");

            // then
            assertEquals(0, result);
        }

        @Test
        void should_throw_invalid_argument_exception_when_argument_is_null() {
            // then
            assertThrows(NullArgumentException.class, () -> trainStations.calculateRouteNumbersWithFixedStop(null));
        }

        @Test
        void should_throw_invalid_argument_exception_when_argument_size_is_exceed() {
            // then
            assertThrows(InvalidArgumentException.class, () -> trainStations.calculateRouteNumbersWithFixedStop("A", "C", "3", "4"));
        }

        @Test
        void should_throw_invalid_argument_exception_when_argument_type_is_wrong() {
            // then
            assertThrows(InvalidArgumentException.class, () -> trainStations.calculateRouteNumbersWithFixedStop("A", "C", "B"));
        }
    }

    @Nested
    class ShortestRouteCalculatorTest {
        @Test
        void should_return_the_shortest_route_between_two_stations_scenario_1() {
            // when
            Integer result = trainStations.calculateShortestRouteLength("A", "C");

            // then
            assertEquals(9, result);
        }

        @Test
        void should_return_the_shortest_route_between_two_stations_scenario_2() {
            // when
            Integer result = trainStations.calculateShortestRouteLength("B", "B");

            // then
            assertEquals(9, result);
        }

        @Test
        void should_throw_exception_when_there_is_no_route_between_stations() {
            assertThrows(NoSuchRouteException.class, () -> trainStations.calculateShortestRouteLength("A", "A"));
        }

        @Test
        void should_throw_invalid_argument_exception_when_argument_is_null() {
            // then
            assertThrows(NullArgumentException.class, () -> trainStations.calculateShortestRouteLength(null));
        }

        @Test
        void should_throw_invalid_argument_exception_when_argument_size_is_exceed() {
            // then
            assertThrows(InvalidArgumentException.class, () -> trainStations.calculateShortestRouteLength("A", "C", "3", "4"));
        }

    }

    @Nested
    class RouteNumberCalculatorWithMaximumDistanceTest {
        @Test
        void should_return_the_correct_route_numbers_with_maximum_distance() {
            // when
            Integer result = trainStations.calculateRouteNumbersWithMaximumDistance("C", "C", "30");

            // then
            assertEquals(7, result);
        }

        @Test
        void should_return_the_correct_route_numbers_with_maximum_distance_scenario_2() {
            // when
            Integer result = trainStations.calculateRouteNumbersWithMaximumDistance("C", "C", "2");

            // then
            assertEquals(0, result);
        }

        @Test
        void should_return_0_route_numbers_with_maximum_distance_when_there_is_no_route_between_two_station() {
            // when
            Integer result = trainStations.calculateRouteNumbersWithMaximumDistance("A", "A", "30");

            // then
            assertEquals(0, result);
        }


        @Test
        void should_throw_invalid_argument_exception_when_argument_is_null() {
            // then
            assertThrows(NullArgumentException.class, () -> trainStations.calculateRouteNumbersWithMaximumDistance(null));
        }

        @Test
        void should_throw_invalid_argument_exception_when_argument_size_is_exceed() {
            // then
            assertThrows(InvalidArgumentException.class, () -> trainStations.calculateRouteNumbersWithMaximumDistance("A", "C", "3", "4"));
        }

        @Test
        void should_throw_invalid_argument_exception_when_maximum_distance_is_not_integer() {
            // then
            assertThrows(InvalidArgumentException.class, () -> trainStations.calculateRouteNumbersWithMaximumDistance("A", "C", "C"));
        }
    }

    @Nested
    class DurationCalculationTest {
        @Test
        void should_calculate_the_total_duration_when_there_are_two_valid_location() {
            // given
            String start = "A";
            String end = "D";

            // when
            Object distance = trainStations.calculateTripDuration(start, end);

            // then
            assertEquals(5, distance);
        }

        @Test
        void should_return_correct_DURATION_when_there_are_multiple_valid_station() {
            // given
            String position1 = "A";
            String position2 = "B";
            String position3 = "C";

            // when
            Object distance = trainStations.calculateTripDuration(position1, position2, position3);

            // then
            assertEquals(11, distance);
        }

        @Test
        void should_return_no_such_route_exception_when_route_does_not_exist() {
            // given
            String start = "B";
            String end = "A";

            // when
            Object result = trainStations.calculateTripDuration(start, end);

            // then
            assertEquals("NO SUCH ROUTE", result);
        }

        @ParameterizedTest
        @ValueSource(strings = {"A", ""})
        void should_throw_insufficient_arguments_exception_when_there_are_less_than_two_location(String strings) {
            // given
            assertThrows(InsufficientArgumentsException.class,
                    () -> trainStations.calculateTripDuration(strings.replace(" ", ",")));
        }

        @Test
        void should_throw_null_argument_exception_when_input_is_null() {
            assertThrows(NullArgumentException.class, () -> trainStations.calculateTripDuration(null));
        }

        @Test
        void should_return_no_such_route_when_many_routes_do_not_exist() {
            // given
            String first = "A";
            String second = "F";
            String third = "G";

            // when
            Object result = trainStations.calculateTripDuration(first, second, third);

            // then
            assertEquals("NO SUCH ROUTE", result);
        }
    }

    @Nested
    class RouteCalculatorWithMaximumDurationsTest {
        @Test
        void should_return_correct_route_numbers_when_given_maximum_duration_first_scenario() {
            // when
            Integer result = trainStations.calculateRouteNumbersWithMaximumDuration("C", "C", "30");

            // then
            assertEquals(4, result);
        }

        @Test
        void should_return_correct_route_numbers_when_given_maximum_stops_second_scenario() {
            // when
            Integer result = trainStations.calculateRouteNumbersWithMaximumDuration("A", "C", "15");

            // then
            assertEquals(2, result);
        }

        @Test
        void should_return_0_when_there_is_no_valid_route() {
            // when
            Integer result = trainStations.calculateRouteNumbersWithMaximumDuration("A", "A", "15");

            // then
            assertEquals(0, result);
        }

        @Test
        void should_throw_invalid_argument_exception_when_argument_is_null() {
            // then
            assertThrows(NullArgumentException.class, () -> trainStations.calculateRouteNumbersWithMaximumDuration(null));
        }

        @Test
        void should_throw_invalid_argument_exception_when_argument_size_is_exceed() {
            // then
            assertThrows(InvalidArgumentException.class, () -> trainStations.calculateRouteNumbersWithMaximumDuration("A", "C", "3", "4"));
        }

        @Test
        void should_throw_invalid_argument_exception_when_argument_type_is_wrong() {
            // then
            assertThrows(InvalidArgumentException.class, () -> trainStations.calculateRouteNumbersWithMaximumDuration("A", "C", "B"));
        }

    }

    @Nested
    class RouteCalculatorWithFixedDurationsTest{
        @Test
        void should_return_correct_route_numbers_when_given_fixed_durations_first_scenario() {
            // when
            Integer result = trainStations.calculateRouteNumbersWithFixedDurations("A", "C", "30");

            // then
            assertEquals(1, result);
        }

        @Test
        void should_return_correct_route_numbers_when_given_fixed_durations_second_scenario() {
            // when
            Integer result = trainStations.calculateRouteNumbersWithFixedDurations("A", "C", "11");

            // then
            assertEquals(1, result);
        }

        @Test
        void should_return_zero_route_numbers_when_there_is_no_routes_in_given_stops() {
            // when
            Integer result = trainStations.calculateRouteNumbersWithFixedDurations("C", "A", "11");

            // then
            assertEquals(0, result);
        }

        @Test
        void should_throw_invalid_argument_exception_when_argument_is_null() {
            // then
            assertThrows(NullArgumentException.class, () -> trainStations.calculateRouteNumbersWithFixedDurations(null));
        }

        @Test
        void should_throw_invalid_argument_exception_when_argument_size_is_exceed() {
            // then
            assertThrows(InvalidArgumentException.class, () -> trainStations.calculateRouteNumbersWithFixedDurations("A", "C", "3", "4"));
        }

        @Test
        void should_throw_invalid_argument_exception_when_argument_type_is_wrong() {
            // then
            assertThrows(InvalidArgumentException.class, () -> trainStations.calculateRouteNumbersWithFixedDurations("A", "C", "B"));
        }
    }

    @Nested
    class ShortestRouteDurationCalculatorTest {
        @Test
        void should_return_the_shortest_route_duration_between_two_stations_scenario_1() {
            // when
            Integer result = trainStations.calculateTheShortestDurationRoute("A", "C");

            // then
            assertEquals(11, result);
        }

        @Test
        void should_return_the_shortest_route_between_two_stations_scenario_2() {
            // when
            Integer result = trainStations.calculateTheShortestDurationRoute("B", "B");

            // then
            assertEquals(13, result);
        }

        @Test
        void should_throw_exception_when_there_is_no_route_between_stations() {
            assertThrows(NoSuchRouteException.class, () -> trainStations.calculateTheShortestDurationRoute("A", "A"));
        }

        @Test
        void should_throw_invalid_argument_exception_when_argument_is_null() {
            // then
            assertThrows(NullArgumentException.class, () -> trainStations.calculateTheShortestDurationRoute(null));
        }

        @Test
        void should_throw_invalid_argument_exception_when_argument_size_is_exceed() {
            // then
            assertThrows(InvalidArgumentException.class, () -> trainStations.calculateTheShortestDurationRoute("A", "C", "3", "4"));
        }
    }
}
