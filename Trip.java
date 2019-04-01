package org.indreiu.travel.controller;

public class Trip {
    private int id;
    private String origin;
    private String destination;
    private String departure;
    private String time;
    private int numberOfSeats;

    public Trip(int id, String origin, String destination, String departure,  String time, int numberOfSeats) {
        this.id = id;
        this.origin = origin;
        this.departure = departure;
        this.destination = destination;
        this.time = time;
        this.numberOfSeats = numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrigin() {
        return origin;
    }

    public String getTime() {
        return time;
    }

    public String getDeparture() {
        return departure;
    }

    public String getDestination() {
        return destination;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }
}
