package org.indreiu.travel.controller;

import java.util.ArrayList;
import java.util.List;

public interface TripManager {
    List<Trip> getAllTrips();
    void buyTickets(int seatsLeft, int id);
    int getAvailableSeats(int id);
    default List<Trip> getTrips(String departure, String destination) {
        List<Trip> matchingTrips = new ArrayList<>();

        for (Trip trip : getAllTrips()) {
            if (trip.getDestination().equals(destination) && trip.getDeparture().equals(departure)) {
                matchingTrips.add(trip);
            }
        }

        return matchingTrips;
    }
}
