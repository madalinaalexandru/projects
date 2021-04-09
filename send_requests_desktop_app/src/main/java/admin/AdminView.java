package admin;

import customization.BorderClass;
import customization.ColorClass;
import customization.FontClass;
import logIn.LogInView;
import propertiesManager.PropertiesManagerView;
import requestsManager.RequestsManagerView;
import usersManager.UsersManagerView;

import javax.swing.*;

public class AdminView {

    JFrame adminView;
    JButton logOutButton;
    JButton viewUsersButton;
    JButton viewRequestsButton;
    JButton viewPropertiesButton;

    public AdminView() {

        createAllObjects();

        setBoundsForAllObjects();

        setAspectForAllObjects();

        addActionListeners();

        addAllObjects();

        drawAdminView();
    }

    public void createAllObjects() {

        adminView = new JFrame();
        logOutButton = new JButton("Log Out");
        viewPropertiesButton = new JButton("View Properties");
        viewRequestsButton = new JButton("View Requests");
        viewUsersButton = new JButton("View Users");
    }

    public void setBoundsForAllObjects() {

        viewUsersButton.setBounds(50, 30, 250, 60);
        viewPropertiesButton.setBounds(50, 110, 250, 60);
        viewRequestsButton.setBounds(50, 190, 250, 60);
        logOutButton.setBounds(50, 270, 250, 60);
    }

    public void setAspectForAllObjects() {

        setFontsForAllObjects(new FontClass());
        setColorsForAllObjects(new ColorClass());
        setBordersForAllObjects(new BorderClass());
    }

    public void setFontsForAllObjects(FontClass fontClass) {

        viewRequestsButton.setFont(fontClass.getCustomButtonFont());
        viewPropertiesButton.setFont(fontClass.getCustomButtonFont());
        viewUsersButton.setFont(fontClass.getCustomButtonFont());
        logOutButton.setFont(fontClass.getCustomButtonFont());
    }

    public void setColorsForAllObjects(ColorClass colorClass) {

        adminView.getContentPane().setBackground(colorClass.getBackgroundColor());

        viewUsersButton.setForeground(colorClass.getButtonTextFontColor());
        viewRequestsButton.setForeground(colorClass.getButtonTextFontColor());
        viewPropertiesButton.setForeground(colorClass.getButtonTextFontColor());
        logOutButton.setForeground(colorClass.getButtonTextFontColor());

        viewUsersButton.setBackground(colorClass.getButtonBackgroundColor());
        viewRequestsButton.setBackground(colorClass.getButtonBackgroundColor());
        viewPropertiesButton.setBackground(colorClass.getButtonBackgroundColor());
        logOutButton.setBackground(colorClass.getButtonBackgroundColor());
    }

    public void setBordersForAllObjects(BorderClass borderClass) {

        viewPropertiesButton.setBorder(borderClass.getButtonBorder());
        viewRequestsButton.setBorder(borderClass.getButtonBorder());
        viewUsersButton.setBorder(borderClass.getButtonBorder());
        logOutButton.setBorder(borderClass.getButtonBorder());
    }

    public void addActionListeners() {

        logOutButton.addActionListener(a -> {

            adminView.dispose();
            new LogInView();
        });

        viewUsersButton.addActionListener(a -> {

            adminView.dispose();
            new UsersManagerView();
        });

        viewPropertiesButton.addActionListener(a -> {

            adminView.dispose();
            new PropertiesManagerView();
        });

        viewRequestsButton.addActionListener(a -> {

            adminView.dispose();
            new RequestsManagerView();
        });
    }

    public void addAllObjects() {

        adminView.add(viewUsersButton);
        adminView.add(viewPropertiesButton);
        adminView.add(viewRequestsButton);
        adminView.add(logOutButton);
    }

    public void drawAdminView() {

        adminView.setTitle("Admin");
        adminView.setSize(370, 390);
        adminView.setLayout(null);
        adminView.setLocationRelativeTo(null);
        adminView.setVisible(true);
        adminView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
