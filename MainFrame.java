package org.indreiu.travel.ui;

import org.indreiu.travel.controller.Trip;
import org.indreiu.travel.controller.TripManager;
import org.indreiu.travel.controller.UserManager;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainFrame extends JFrame {

    private JTable table;
    private JTextField destinationField;
    private JTextField departureField;
    private JButton searchButton;
    private UserManager userManager;
    private TripManager tripManager;
    private DefaultTableModel tableModel;
    private JButton buyButton;
    private JTextField buyNoSeats;
    private JTextField buyClientName;
    private JTextField buyTouristsName;
    private JTextField buyClientAdress;
    private JButton logOutButton;

    public MainFrame(UserManager userManager, TripManager tripManager) {
        this.setSize(800, 600);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.userManager = userManager;
        this.tripManager = tripManager;
    }

    public void login() {
        LoginDialog loginDialog = new LoginDialog(this, userManager);
        loginDialog.setVisible(true);

        if (!loginDialog.isUserAllowed()) {
            System.exit(0);
        }

        initComponents();
        initLayout();
        initActions();


    }

    private void initActions() {
        buyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                int id = (Integer) table.getValueAt(row, 0);
                int noSeatsAvailable = tripManager.getAvailableSeats(id);
                String noSeatsRequested = buyNoSeats.getText();
                if(noSeatsRequested.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No. Seats is missing");
                    return;
                }
                if(buyClientAdress.getText().isEmpty())
                {

                    JOptionPane.showMessageDialog(null, "Client adress is missing");
                    return;
                }
                if(buyTouristsName.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Tourists field is empty");
                    return;
                }
                if(buyClientName.getText().isEmpty())
                {

                    JOptionPane.showMessageDialog(null, "Client name is missing");
                    return;
                }
                int noSeatsLeft = noSeatsAvailable-Integer.parseInt(noSeatsRequested);
                if(noSeatsLeft<0)
                {
                    JOptionPane.showMessageDialog(null, "There are not enough seats.");
                    return;
                }
                if(noSeatsLeft>=0 && row!=-1)
                {
                    tripManager.buyTickets(noSeatsLeft,id);
                    showTable();
                    JOptionPane.showMessageDialog(null, "Succesfully bought.");
                    return;
                }


            }
        });
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainFrame.this.getContentPane().removeAll();
                MainFrame.this.repaint();
                login();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String departure = departureField.getText();
                String destination = destinationField.getText();
                if(departure.isEmpty() && destination.isEmpty())
                    {
                        showTable();
                        return;
                    }
                if (departure.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Departure is missing");
                    return;
                }

                if (destination.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Destination is missing");
                    return;
                }
                List<Trip> tripsToDisplay = tripManager.getTrips(departure, destination);
                displayTrips(tripsToDisplay);

            }
        });
    }

    private void displayTrips(List<Trip> tripsToDisplay) {
        tableModel.setRowCount(0);
        for (Trip trip : tripsToDisplay) {
            tableModel.addRow(new Object[]{trip.getId(), trip.getOrigin(), trip.getDestination(), trip.getDeparture(), trip.getTime(), trip.getNumberOfSeats()});
        }

    }

    private void showTable() {
        displayTrips(tripManager.getAllTrips());
    }

    private void initLayout() {
        setLayout(new BorderLayout());

        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.Y_AXIS));
        searchPanel.add(new JLabel("Departure"));
        searchPanel.add(departureField);
        searchPanel.add(new JLabel("Destination"));
        searchPanel.add(destinationField);
        searchPanel.add(searchButton);


        JPanel buyPanel = new JPanel();
        buyPanel.setLayout(new BoxLayout(buyPanel, BoxLayout.Y_AXIS));
        buyPanel.add(new JLabel("Client Name"));
        buyPanel.add(buyClientName);
        buyPanel.add(new JLabel("Tourists Names"));
        buyPanel.add(buyTouristsName);
        buyPanel.add(new JLabel("Client adress"));
        buyPanel.add(buyClientAdress);
        buyPanel.add(new JLabel("No. seats"));
        buyPanel.add(buyNoSeats);
        buyPanel.add(buyButton);

        JPanel logoutPanel = new JPanel();
        logoutPanel.add(logOutButton);

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());
        leftPanel.add(searchPanel, BorderLayout.BEFORE_FIRST_LINE);
        leftPanel.add(buyPanel, BorderLayout.LINE_START);
        leftPanel.add(logoutPanel,BorderLayout.SOUTH);
        add(leftPanel, BorderLayout.WEST);

        add(new JScrollPane(table), BorderLayout.CENTER);
        revalidate();
    }

    private void initComponents() {
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tableModel.setColumnIdentifiers(new Object[]{"id","Origin", "Destination","Departure","Time", "No. seats"});
        table = new JTable(tableModel);
        tableModel.setColumnCount(6);

        displayTrips(tripManager.getAllTrips());

        departureField = new JTextField();
        destinationField = new JTextField();
        searchButton = new JButton("Search");

        buyButton = new JButton("Buy");
        buyClientName = new JTextField();
        buyTouristsName = new JTextField();
        buyClientAdress = new JTextField();
        buyNoSeats = new JTextField();

        logOutButton = new JButton("Log Out");
    }
}
