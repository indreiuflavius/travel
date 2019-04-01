package org.indreiu.travel.controller;

import java.util.List;

public class DBBackedTripManager implements TripManager {
    private DBManager dbManager = new DBManager();
    private List<Trip> allTrips;

    public DBBackedTripManager() {
        allTrips = dbManager.getAllTrips();
    }

    @Override
    public List<Trip> getAllTrips() {
        return allTrips;
    }
    public int getAvailableSeats(int id)
    {
        return dbManager.getAvailableSeats(id);
    }
    public void buyTickets(int seatsLeft, int id) {
        dbManager.buyTickets(seatsLeft, id+1);
        for (Trip trip: allTrips) {
            if (trip.getId() == id) {
                trip.setNumberOfSeats(seatsLeft);
                return;
            }
        }
    }
}
