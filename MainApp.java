package org.indreiu.travel;

import org.indreiu.travel.controller.DBBackedTripManager;
import org.indreiu.travel.controller.DBBackedUserManager;
import org.indreiu.travel.ui.MainFrame;

public class MainApp {
    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame(new DBBackedUserManager(), new DBBackedTripManager());
        mainFrame.setVisible(true);
        mainFrame.login();
    }
}
