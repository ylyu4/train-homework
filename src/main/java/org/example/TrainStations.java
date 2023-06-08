package org.example;

import org.example.exception.InsufficientArgumentsException;
import org.example.exception.InvalidArgumentException;
import org.example.exception.NoSuchRouteException;
import org.example.exception.NullAgumentException;
import org.example.model.Route;
import org.example.model.Trip;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TrainStations {

    private final List<Trip> trips;

    public static final String NO_SUCH_ROUTE = "NO SUCH ROUTE";

    public TrainStations(String graph) {
        this.trips = Arrays.stream(graph.split(","))
                .map(it -> new Trip(it.substring(0, 1), it.substring(1, 2), Integer.parseInt(it.substring(2))))
                .collect(Collectors.toList());
    }


    public Object calculateDistance(String...args) {
        validateDistanceCalculationArguments(args);
        try {
            return calculateDistanceForMultiplePoints(args);
        } catch (NoSuchRouteException ex) {
            return NO_SUCH_ROUTE;
        }
    }

    public int calculateRouteNumbersWithMaximumStop(String... args) {
        validateRouteCalculationArguments(args);

        try {
            String start = args[0];
            String end = args[1];
            int maximumStop = Integer.parseInt(args[2]);

            List<Route> routes = new ArrayList<>();

            findRouteWithMaximumStop(start, end, maximumStop, routes, new ArrayList<>());
            return routes.size();
        } catch (NumberFormatException exception) {
            throw new InvalidArgumentException();
        }
    }


    public int calculateRouteNumbersWithFixedStop(String... args) {
        validateRouteCalculationArguments(args);

        try {
            String start = args[0];
            String end = args[1];
            int fixedStop = Integer.parseInt(args[2]);

            List<Route> routes = new ArrayList<>();
            findRouteWithMaximumStop(start, end, fixedStop, routes, new ArrayList<>());
            return (int) routes.stream().filter(route -> route.getTrips().size() == fixedStop).count();
        } catch (NumberFormatException exception) {
            throw new InvalidArgumentException();
        }
    }

    private void findRouteWithMaximumStop(String start, String end, int maximumStop, List<Route> routes, List<Trip> tripList) {
        if (tripList.size() >= maximumStop) {
            return;
        }

        List<Trip> tripsWithMatchedStart = trips.stream().filter(it -> it.getStart().equals(start)).toList();
        for (Trip trip : tripsWithMatchedStart) {
            List<Trip> tempTripList = new ArrayList<>();
            if (!tripList.isEmpty()) {
                tempTripList.addAll(tripList);
            }
            tempTripList.add(trip);
            if (trip.getEnd().equals(end)) {
                routes.add(new Route(tempTripList));
            }
            findRouteWithMaximumStop(trip.getEnd(), end, maximumStop, routes, tempTripList);
        }
    }

    private int calculateDistanceForMultiplePoints(String[] args) {
        List<Trip> allTrips = new ArrayList<>();
        for (int i = 0; i < args.length - 1; i++) {
            String start = args[i];
            String end = args[i + 1];
            Trip target = trips.stream()
                    .filter(trip -> trip.getStart().equals(start) && trip.getEnd().equals(end))
                    .findFirst()
                    .orElseThrow(NoSuchRouteException::new);
            allTrips.add(target);
        }

        return new Route(allTrips).getTotalDistance();
    }


    private static void validateDistanceCalculationArguments(String[] args) {
        if (args == null) {
            throw new NullAgumentException();
        }
        if (args.length < 2) {
            throw new InsufficientArgumentsException();
        }
    }

    private static void validateRouteCalculationArguments(Object[] args) {
        if (args == null) {
            throw new NullAgumentException();
        }
        if (args.length != 3) {
            throw new InvalidArgumentException();
        }
    }
}
