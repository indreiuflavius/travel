package org.indreiu.travel.controller;

public class DummyUserManager implements UserManager {
    @Override
    public boolean isUserAllowed(String username, String password) {
        return true;
    }
}
