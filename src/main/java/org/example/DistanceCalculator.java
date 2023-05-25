package org.example;

import java.util.Arrays;

public class DistanceCalculator {

    public static final String graph = "AB5,BC4,CD8,DC8,DE6,AD5,CE2,EB3,AE7";

    public static Integer calculate(String... args) {
        String start = args[0];
        String end = args[1];
        String[] splitGraph = graph.split(",");
        String combination = start + end;
        String result = Arrays.stream(splitGraph).filter(it -> it.startsWith(combination)).findFirst().get();
        return Integer.parseInt(result.substring(result.length() - 1));
    }
}
