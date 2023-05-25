package org.example;

import java.util.Arrays;

public class DistanceCalculator {

    public static final String GRAPH = "AB5,BC4,CD8,DC8,DE6,AD5,CE2,EB3,AE7";

    public static Integer calculate(String... args) {
        String[] splitGraph = GRAPH.split(",");
        String combination = args[0] + args[1];
        return Integer.parseInt(Arrays.stream(splitGraph)
                .filter(it -> it.startsWith(combination))
                .findFirst()
                .map(it -> it.substring(it.length() - 1))
                .get());
    }
}
