package org.indreiu.travel.ui;

import org.indreiu.travel.controller.UserManager;

import javax.swing.*;
import java.awt.*;

public class LoginDialog extends JDialog {
    private JTextField usernameField;
    private JTextField passwordField;
    private JButton loginBtn;
    private UserManager userManager;

    private boolean userIsAllowed = false;

    public LoginDialog(JFrame parentFrame, UserManager userManager) {
        super(parentFrame, true);
        this.setLocationRelativeTo(parentFrame);
        this.userManager = userManager;

        setSize(200, 50);

        initComponents();
        initLayout();
        initActions();
    }

    private void initComponents() {
        this.usernameField = new JTextField();
        this.passwordField = new JPasswordField();
        this.loginBtn = new JButton("Login");
    }

    private void initLayout() {
        BoxLayout boxLayout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS);
        this.setLayout(boxLayout);

        add(new JLabel("Username"));
        add(usernameField);
        add(new JLabel("Password"));
        add(passwordField);
        add(new JSeparator());

        JPanel buttonPanel = new JPanel(new BorderLayout());
        add(buttonPanel);
        buttonPanel.add(loginBtn, BorderLayout.CENTER);

        pack();
    }

    private void initActions() {
        loginBtn.addActionListener(e -> {
            if (usernameField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Username is missing");
                return;
            }

            if (passwordField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Password is missing");
                return;
            }

            if (userManager.isUserAllowed(usernameField.getText(), passwordField.getText())) {
                userIsAllowed = true;
                dispose();

            } else {
                JOptionPane.showMessageDialog(null, "You are not allowed here");
            }
        });
    }

    public boolean isUserAllowed() {
        return userIsAllowed;
    }
}