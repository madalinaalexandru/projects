package usersManager;

import admin.AdminView;
import customization.BorderClass;
import customization.ColorClass;
import customization.FontClass;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class UsersManagerView {

    private UsersManagerController usersManagerController;
    private JFrame usersManagerView;
    private DefaultTableModel model;
    private JTable usersManagerTable;
    private ArrayList<String[]> data;
    private JScrollPane tablePanel;

    private JButton goBackButton;
    private JButton deleteUserButton;

    public UsersManagerView() {

        collectDataFromTable();

        createAllObjects();

        setBoundsForAllObjects();

        setAspectForAllObjects();

        addActionListeners();

        addAllObjects();

        drawUsersManagerView();
    }

    public void collectDataFromTable() {

        usersManagerController = new UsersManagerController();
        data = new ArrayList<String[]>();
        data = usersManagerController.collectDataFromTable();
    }

    public void createAllObjects() {

        usersManagerView = new JFrame();

        model = new DefaultTableModel();

        model.addColumn("Id");
        model.addColumn("First Name");
        model.addColumn("Last Name");
        model.addColumn("Email");
        model.addColumn("Phone Number");
        model.addColumn("Address");
        model.addColumn("Married");
        model.addColumn("Individual");

        for (String[] d : data) {
            model.addRow(d);
        }

        BorderClass borderClass = new BorderClass();

        usersManagerTable = new JTable(model) { // I'm super bad, dar asta e; asta sunt
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                JComponent jc = (JComponent) super.prepareRenderer(renderer, row, column);
                jc.setBorder(borderClass.getCustomBorder());
                return jc;
            }
        };

        usersManagerTable.setShowVerticalLines(false);
        usersManagerTable.setShowHorizontalLines(false);
        usersManagerTable.setAutoCreateRowSorter(true);

        usersManagerTable.setRowHeight(40);
        tablePanel = new JScrollPane(usersManagerTable);
        usersManagerTable.setFillsViewportHeight(true);
        usersManagerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        goBackButton = new JButton("Go Back");
        deleteUserButton = new JButton("Delete User");
    }

    public void setBoundsForAllObjects() {

        tablePanel.setBounds(50, 100, 1300, 500);

        TableColumnModel columnModel = usersManagerTable.getColumnModel();

        columnModel.getColumn(0).setPreferredWidth(320);
        columnModel.getColumn(1).setPreferredWidth(110);
        columnModel.getColumn(2).setPreferredWidth(110);
        columnModel.getColumn(3).setPreferredWidth(200);
        columnModel.getColumn(4).setPreferredWidth(160);
        columnModel.getColumn(5).setPreferredWidth(200);
        columnModel.getColumn(6).setPreferredWidth(100);
        columnModel.getColumn(7).setPreferredWidth(100);

        goBackButton.setBounds(55, 650, 200, 50);
        deleteUserButton.setBounds(275, 650, 200, 50);

    }

    public void addAllObjects() {

        usersManagerView.add(tablePanel);
        usersManagerView.add(goBackButton);
        usersManagerView.add(deleteUserButton);
    }

    public void drawUsersManagerView() {

        usersManagerView.setTitle("Manage Users");
        usersManagerView.setSize(1420, 900);
        usersManagerView.setLayout(null);
        usersManagerView.setLocationRelativeTo(null);
        usersManagerView.setVisible(true);
        usersManagerView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setAspectForAllObjects() {

        setFontsForAllObjects(new FontClass());
        setColorsForAllObjects(new ColorClass());
        setBordersForAllObjects(new BorderClass());
    }

    public void setFontsForAllObjects(FontClass fontClass) {

        usersManagerTable.getTableHeader().setFont(fontClass.getCustomLabelFont());
        usersManagerTable.setFont(fontClass.getCustomTextFieldFont());

        goBackButton.setFont(fontClass.getCustomButtonFont());
        deleteUserButton.setFont(fontClass.getCustomButtonFont());
    }

    public void setColorsForAllObjects(ColorClass colorClass) {

        usersManagerView.getContentPane().setBackground(colorClass.getBackgroundColor());

        usersManagerTable.getTableHeader().setForeground(colorClass.getButtonTextFontColor());
        usersManagerTable.getTableHeader().setBackground(colorClass.getButtonBackgroundColor());


        usersManagerTable.setForeground(colorClass.getTextFieldFontColor());
        usersManagerTable.setBackground(colorClass.getBackgroundColor());

        tablePanel.setBackground(colorClass.getBackgroundColor());

        goBackButton.setForeground(colorClass.getButtonTextFontColor());
        deleteUserButton.setForeground(colorClass.getButtonTextFontColor());

        goBackButton.setBackground(colorClass.getButtonBackgroundColor());
        deleteUserButton.setBackground(colorClass.getButtonBackgroundColor());


    }

    public void setBordersForAllObjects(BorderClass borderClass) {

        usersManagerTable.getTableHeader().setBorder(borderClass.getButtonBorder());

        usersManagerTable.setBorder(borderClass.getCustomBorder());

        tablePanel.setBorder(borderClass.getCustomBorder());

        goBackButton.setBorder(borderClass.getButtonBorder());
        deleteUserButton.setBorder(borderClass.getButtonBorder());
    }

    public void addActionListeners() {

        goBackButton.addActionListener(a -> {

            usersManagerView.dispose();
            new AdminView();
        });

        deleteUserButton.addActionListener(a -> {

            Vector<String> userData = model.getDataVector().elementAt(usersManagerTable.getSelectedRow());
            usersManagerController.deleteUser(userData);

            usersManagerView.dispose();
            new UsersManagerView();
        });

    }
}
