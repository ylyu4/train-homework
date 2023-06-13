package org.example.model;

import java.util.List;

public class Route {
    private static final int STOP_DURATION = 2;

    private static final int DISTANCE_DURATION = 1;

    private List<Trip> trips;

    public Route(List<Trip> trips) {
        this.trips = trips;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public int getTotalDistance() {
       return trips.stream().mapToInt(Trip::getDistance).sum();
    }

    public int getTotalDuration() {
        return trips.stream().mapToInt(it -> it.getDistance() * DISTANCE_DURATION).sum() +
                (trips.size() - 1) * STOP_DURATION;
    }

}
