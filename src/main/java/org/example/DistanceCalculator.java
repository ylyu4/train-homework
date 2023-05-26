package org.example;

import org.example.exception.InsufficientArgumentsException;
import org.example.exception.NullAgumentException;

import java.util.Arrays;

public class DistanceCalculator {

    public static final String GRAPH = "AB5,BC4,CD8,DC8,DE6,AD5,CE2,EB3,AE7";
    public static final String NO_SUCH_ROUTE = "NO SUCH ROUTE";

    public static String calculate(String... args) {
        validateArguments(args);

        return Arrays.stream(GRAPH.split(","))
                .filter(it -> it.startsWith(args[0] + args[1]))
                .findFirst()
                .map(it -> it.substring(it.length() - 1))
                .orElse(NO_SUCH_ROUTE);
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
