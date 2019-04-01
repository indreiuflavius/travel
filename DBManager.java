package org.indreiu.travel.controller;

import org.sqlite.SQLiteConnection;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
    private SqliteDbConnection sqliteDbConnection;

    DBManager() {
        connectDB();
    }

    private void connectDB()
    {
        try {
            sqliteDbConnection = new SqliteDbConnection();
        } catch (SQLException e2) {
            e2.printStackTrace();
        }
    }
    public int getAvailableSeats(int id)
    {
        connectDB();
        String queryAvailableSeats="SELECT Seats FROM Flights WHERE id="+"\""+id+"\"";
        try {
            sqliteDbConnection.setRs(sqliteDbConnection.getSt().executeQuery(queryAvailableSeats));
            String numberOfSeats =sqliteDbConnection.getRs().getString(1);
            return Integer.parseInt(numberOfSeats);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    public void buyTickets(int seatsLeft,int id)
    {
        String queryRemoveSeats="UPDATE Flights SET Seats="+"\""+seatsLeft+"\""+" WHERE id="+"\""+id+"\"";
        try {
            sqliteDbConnection.getSt().execute(queryRemoveSeats);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean isUserAllowed(String username, String password)
    {
        connectDB();
        String queryLogIn="SELECT password FROM User WHERE userName="+"\""+username+"\"";
        try {
            sqliteDbConnection.setRs(sqliteDbConnection.getSt().executeQuery(queryLogIn));
            String passwordDatabase =sqliteDbConnection.getRs().getString("password");
            if(passwordDatabase.equals(password))
            {
                sqliteDbConnection.close();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    public List<Trip> getAllTrips()
    {
        String queryFlights = "SELECT id,Origin,Destination,Departure,Time,Seats FROM Flights";
        List<Trip> allTrips = new ArrayList<>();
        try {
            sqliteDbConnection.setRs(sqliteDbConnection.getSt().executeQuery(queryFlights));
            while (sqliteDbConnection.getRs().next())
            {
                allTrips.add((new Trip(sqliteDbConnection.getRs().getInt(1),sqliteDbConnection.getRs().getString(2),
                        sqliteDbConnection.getRs().getString(3), sqliteDbConnection.getRs().getString(4),
                        sqliteDbConnection.getRs().getString(5),sqliteDbConnection.getRs().getInt(6))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allTrips;
    }
}

