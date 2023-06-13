package org.example.model;

import java.util.List;

public class Route {
    public static final int STOP_DURATION = 2;

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
        return trips.stream().mapToInt(Trip::getDurations).sum() +
                (trips.size() - 1) * STOP_DURATION;
    }

    public static int getTotalDuration(List<Trip> tripList) {
        return tripList.stream().mapToInt(Trip::getDurations).sum() +
                (tripList.size() - 1) * STOP_DURATION;
    }

}
