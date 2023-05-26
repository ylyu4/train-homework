package org.example;

import org.example.exception.InsufficientArgumentsException;
import org.example.exception.NoSuchRouteException;
import org.example.exception.NullAgumentException;

import java.util.Arrays;

public class DistanceCalculator {

    public static final String GRAPH = "AB5,BC4,CD8,DC8,DE6,AD5,CE2,EB3,AE7";
    public static final String NO_SUCH_ROUTE = "NO SUCH ROUTE";

    public static Object calculate(String... args) {
        validateArguments(args);
        try {
            return calculateDistanceForMultiplePoints(args);
        } catch (NoSuchRouteException ex) {
            return NO_SUCH_ROUTE;
        }
    }

    private static int calculateDistanceForMultiplePoints(String[] args) {
        int start = 0;
        int end = 1;
        int distance = 0;

        while (end < args.length) {
            distance += calculateDistanceBetweenTwoPoints(args[start], args[end]);
            start++;
            end++;
        }
        return distance;
    }

    private static Integer calculateDistanceBetweenTwoPoints(String start, String end) {
        return Integer.parseInt(Arrays.stream(GRAPH.split(","))
                .filter(it -> it.startsWith(start + end))
                .findFirst()
                .map(it -> it.substring(it.length() - 1))
                .orElseThrow(NoSuchRouteException::new));
    }

    private static void validateArguments(String[] args) {
        if (args == null) {
            throw new NullAgumentException();
        }
        if (args.length < 2) {
            throw new InsufficientArgumentsException();
        }
    }
}
