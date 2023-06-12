package org.example.validator;

import org.example.exception.InsufficientArgumentsException;
import org.example.exception.InvalidArgumentException;
import org.example.exception.NullArgumentException;

public class ArgsValidator {

    public static void validateDistanceCalculationArguments(String[] args) {
        if (args == null) {
            throw new NullArgumentException();
        }
        if (args.length < 2) {
            throw new InsufficientArgumentsException();
        }
    }

    public static void validateShortestDistanceCalculationArguments(String[] args) {
        if (args == null) {
            throw new NullArgumentException();
        }
        if (args.length != 2) {
            throw new InvalidArgumentException();
        }
    }

    public static void validateRouteCalculationArguments(String[] args) {
        if (args == null) {
            throw new NullArgumentException();
        }
        if (args.length != 3) {
            throw new InvalidArgumentException();
        }
    }

}
