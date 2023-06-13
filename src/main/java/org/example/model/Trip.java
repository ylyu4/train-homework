package org.example.model;

public class Trip {

    public static final int DISTANCE_DURATION = 1;

    private String start;

    private String end;

    private int distance;

    public Trip(String start, String end, int distance) {
        this.start = start;
        this.end = end;
        this.distance = distance;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getDurations() {
        return this.distance * DISTANCE_DURATION;
    }
}
