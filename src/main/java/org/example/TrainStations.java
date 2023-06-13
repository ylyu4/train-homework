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

import static org.example.model.Route.STOP_DURATION;

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
        int fixedStops = getFixeIntegerValue(args);
        return (int) calculateRouteNumbersWithStops(args)
                .stream()
                .filter(route -> route.getTrips().size() == fixedStops)
                .count();
    }

    public int calculateShortestRouteLength(String... args) {
        ArgsValidator.validateShortestDistanceCalculationArguments(args);

        String start = args[0];
        String end = args[1];

        List<Route> routes = new ArrayList<>();

        calculateShortestRouteBetweenTwoStation(start, end, routes, new ArrayList<>());
        return routes.stream()
                .map(Route::getTotalDistance)
                .min((distance1, distance2) -> distance1 < distance2 ? 0 : 1)
                .orElseThrow(NoSuchRouteException::new);
    }

    public int calculateRouteNumbersWithMaximumDistance(String... args) {
        ArgsValidator.validateRouteCalculationArguments(args);

        try {
            String start = args[0];
            String end = args[1];
            int maxDistance = Integer.parseInt(args[2]);

            List<Route> routes = new ArrayList<>();
            calculateRouteNumbersWithLimitDistance(start, end, routes, new ArrayList<>(), maxDistance);
            return routes.size();
        } catch (NumberFormatException exception) {
            throw new InvalidArgumentException();
        }
    }

    public Object calculateTripDuration(String...args) {
        ArgsValidator.validateDistanceCalculationArguments(args);

        try {
            List<Trip> allTrips = getTrips(args);
            return new Route(allTrips).getTotalDuration();
        } catch (NoSuchRouteException ex) {
            return NO_SUCH_ROUTE;
        }
    }

    public int calculateRouteNumbersWithMaximumDuration(String... args) {
        return getRoutesByDurations(args).size();
    }

    public int calculateRouteNumbersWithFixedDurations(String... args) {
        int fixedDuration = getFixeIntegerValue(args);
        return (int) getRoutesByDurations(args).stream().filter(it -> it.getTotalDuration() == fixedDuration).count();
    }

    private List<Route> getRoutesByDurations(String... args) {
        ArgsValidator.validateRouteCalculationArguments(args);
        try {
            String start = args[0];
            String end = args[1];
            int durations = Integer.parseInt(args[2]);

            List<Route> routes = new ArrayList<>();
            findRouteWithMaximumDurations(start, end, routes, new ArrayList<>(), durations);
            return routes;
        } catch (NumberFormatException ex) {
            throw new InvalidArgumentException();
        }
    }

    private void findRouteWithMaximumDurations(String start, String end, List<Route> routes, List<Trip> tripList, int durations) {
        List<Trip> tripsWithMatchedStart = trips.stream().filter(it -> it.getStart().equals(start)).toList();
        for (Trip trip : tripsWithMatchedStart) {
            boolean isTripDurationValidForCurrentRoute = tripList.stream().mapToInt(Trip::getDurations).sum() +
                    trip.getDurations() + tripList.size() * STOP_DURATION > durations;
            List<Trip> tempTripList = getTrips(end, routes, tripList, trip, isTripDurationValidForCurrentRoute);
            if (tempTripList == null) break;
            findRouteWithMaximumDurations(trip.getEnd(), end, routes, tempTripList, durations);
        }
    }

    private static List<Trip> getTrips(String end, List<Route> routes, List<Trip> tripList, Trip trip, boolean isTripValidForCurrentRoute) {
        if (isTripValidForCurrentRoute) {
            return null;
        }
        return manageTripsAndRoute(end, routes, tripList, trip);
    }

    private void calculateRouteNumbersWithLimitDistance(String start, String end, List<Route> routes,
                                                        List<Trip> tripList, int maxDistance) {
        List<Trip> tripsWithMatchedStart = trips.stream().filter(it -> it.getStart().equals(start)).toList();
        for (Trip trip : tripsWithMatchedStart) {
            boolean isTripDistanceValidForCurrentRoute = tripList.stream().mapToInt(Trip::getDistance).sum() +
                    trip.getDistance() >= maxDistance;
            List<Trip> tempTripList = getTrips(end, routes, tripList, trip, isTripDistanceValidForCurrentRoute);
            if (tempTripList == null) break;
            calculateRouteNumbersWithLimitDistance(trip.getEnd(), end, routes, tempTripList, maxDistance);
        }
    }

    private List<Route> calculateRouteNumbersWithStops(String... args) {
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

    private void calculateShortestRouteBetweenTwoStation(String start, String end, List<Route> routes,
                                                         List<Trip> tripList) {
        List<Trip> tripsWithMatchedStart = trips.stream().filter(it -> it.getStart().equals(start)).toList();

        if (tripsWithMatchedStart.isEmpty()) {
            return;
        }

        for (Trip trip : tripsWithMatchedStart) {
            if (isDuplicatedTrip(trip, tripList)) {
                return;
            }
            List<Trip> tempTripList = manageTripsAndRoute(end, routes, tripList, trip);
            calculateShortestRouteBetweenTwoStation(trip.getEnd(), end, routes, tempTripList);
        }
    }

    private void findRouteWithStops(String start, String end, int stops, List<Route> routes, List<Trip> tripList) {
        if (tripList.size() >= stops) {
            return;
        }

        List<Trip> tripsWithMatchedStart = trips.stream().filter(it -> it.getStart().equals(start)).toList();
        for (Trip trip : tripsWithMatchedStart) {
            List<Trip> tempTripList = manageTripsAndRoute(end, routes, tripList, trip);
            findRouteWithStops(trip.getEnd(), end, stops, routes, tempTripList);
        }
    }

    private static List<Trip> manageTripsAndRoute(String end, List<Route> routes, List<Trip> tripList, Trip trip) {
        List<Trip> tempTripList = new ArrayList<>();
        if (!tripList.isEmpty()) {
            tempTripList.addAll(tripList);
        }
        tempTripList.add(trip);
        if (trip.getEnd().equals(end)) {
            routes.add(new Route(tempTripList));
        }
        return tempTripList;
    }

    private int calculateDistanceForMultiplePoints(String[] args) {
        List<Trip> allTrips = getTrips(args);
        return new Route(allTrips).getTotalDistance();
    }

    private List<Trip> getTrips(String[] args) {
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
        return allTrips;
    }

    private static int getFixeIntegerValue(String... args) {
        try {
            return Integer.parseInt(args[2]);
        } catch (Exception ex) {
            if (ex instanceof NullPointerException) {
                throw new NullArgumentException();
            }
            throw new InvalidArgumentException();
        }
    }

    private boolean isDuplicatedTrip(Trip trip, List<Trip> tripList) {
        return tripList.stream()
                .anyMatch(it -> it.getStart().equalsIgnoreCase(trip.getStart()) &&
                        it.getEnd().equalsIgnoreCase(trip.getEnd()));
    }
}
