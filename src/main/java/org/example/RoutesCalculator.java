package org.example;

import java.util.Arrays;
import java.util.List;


public class RoutesCalculator {

    public static final String GRAPH = "AB5,BC4,CD8,DC8,DE6,AD5,CE2,EB3,AE7";

    public static Integer calculateRouteAmountWithMaximumStop(String... args) {

        String start = args[0];
        String end = args[1];
        int maximumStop = Integer.parseInt(args[2]);

        String[] split = GRAPH.split(",");
        List<String> strings = Arrays.asList(split);

        int totalRoute = 0;
        return findRouteWithMaximumStop(start, end, strings, 0, maximumStop, totalRoute);

    }

    private static int findRouteWithMaximumStop(String start, String end, List<String> strings, int currentStop, int maximumStop, int totalRoute) {
        if (currentStop >= maximumStop) {
            return 0;
        }

        List<String> startRoute = strings.stream().filter(it -> it.startsWith(start)).toList();
        for (int i = 0; i < startRoute.size(); i++) {
            String currentStart = startRoute.get(i).substring(1, 2);
            if (currentStart.equals(end)) {
                totalRoute++;
            }
            int tempCurrentStop = currentStop;
            tempCurrentStop++;
            totalRoute += findRouteWithMaximumStop(currentStart, end, strings, tempCurrentStop, maximumStop, 0);
        }
        return totalRoute;
    }

}
