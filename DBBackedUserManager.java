package org.indreiu.travel.controller;

import org.indreiu.travel.controller.SqliteDbConnection;
import org.indreiu.travel.controller.UserManager;

import javax.swing.*;
import java.sql.SQLException;

public class DBBackedUserManager implements UserManager {
    private DBManager DBManager = new DBManager();

    @Override
    public boolean isUserAllowed(String username, String password) {
        return DBManager.isUserAllowed(username,password);
    }

}
