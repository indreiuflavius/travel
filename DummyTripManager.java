package org.indreiu.travel.controller;

import java.util.Arrays;
import java.util.List;

public class DummyTripManager implements TripManager {

    @Override
    public List<Trip> getAllTrips() {
        return Arrays.asList(
                new Trip(1,"Cluj", "Roma", "8 March","12:30",10),
                new Trip(2,"Madrid", "Bucuresti","30 April","08:00", 6),
                new Trip(3,"Paris", "Roma","8 March","6:30", 15)
        );
    }

    public boolean buyTickets (int noSeats)
    {
        return true;
    }
    public void buyTickets(int noSeatsLeft, int id) {}
    public int getAvailableSeats(int id){return 0;}
}
