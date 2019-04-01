package org.indreiu.travel.controller;

public interface UserManager {
    boolean isUserAllowed(String username, String password);
}
