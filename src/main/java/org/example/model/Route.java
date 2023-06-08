package org.example.model;

import java.util.List;

public class Route {
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

}
