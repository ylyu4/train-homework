package org.example;

import org.example.exception.InvalidArgumentException;
import org.example.exception.NoSuchRouteException;
import org.example.exception.NullArgumentException;
import org.example.model.Route;
import org.example.model.Trip;
import org.example.validator.ArgsValidator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TrainStations {

    private final List<Trip> trips;

    public static final String NO_SUCH_ROUTE = "NO SUCH ROUTE";

    public TrainStations(String graph) {
        this.trips = Arrays.stream(graph.split(","))
                .map(it -> new Trip(it.substring(0, 1), it.substring(1, 2), Integer.parseInt(it.substring(2))))
                .toList();
    }


    public Object calculateDistance(String...args) {
        ArgsValidator.validateDistanceCalculationArguments(args);
        try {
            return calculateDistanceForMultiplePoints(args);
        } catch (NoSuchRouteException ex) {
            return NO_SUCH_ROUTE;
        }
    }

    public int calculateRouteNumbersWithMaximumStop(String... args) {
        return calculateRouteNumbersWithStops(args).size();
    }

    public int calculateRouteNumbersWithFixedStop(String... args) {
        int fixedStops = getFixedStops(args);
        return (int) calculateRouteNumbersWithStops(args)
                .stream()
                .filter(route -> route.getTrips().size() == fixedStops)
                .count();
    }

    public List<Route> calculateRouteNumbersWithStops(String... args) {
        ArgsValidator.validateRouteCalculationArguments(args);

        try {
            String start = args[0];
            String end = args[1];
            int stop = Integer.parseInt(args[2]);

            List<Route> routes = new ArrayList<>();

            findRouteWithStops(start, end, stop, routes, new ArrayList<>());
            return routes;
        } catch (NumberFormatException exception) {
            throw new InvalidArgumentException();
        }
    }

    private void findRouteWithStops(String start, String end, int stops, List<Route> routes, List<Trip> tripList) {
        if (tripList.size() >= stops) {
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
            findRouteWithStops(trip.getEnd(), end, stops, routes, tempTripList);
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

    private static int getFixedStops(String... args) {
        try {
            return Integer.parseInt(args[2]);
        } catch (Exception ex) {
            if (ex instanceof NullPointerException) {
                throw new NullArgumentException();
            }
            throw new InvalidArgumentException();
        }
    }
}