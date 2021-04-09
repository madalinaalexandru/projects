package propertiesManager;

import admin.AdminView;
import customization.BorderClass;
import customization.ColorClass;
import customization.FontClass;
import usersManager.UsersManagerView;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class PropertiesManagerView {

    private PropertiesManagerController propertiesManagerController;
    private JFrame propertiesManagerView;
    private DefaultTableModel model;
    private JTable propertiesManagerTable;
    private ArrayList<String[]> data;
    private JScrollPane tablePanel;

    private JButton goBackButton;
    private JButton deletePropertyButton;

    public PropertiesManagerView() {

        collectDataFromTable();

        createAllObjects();

        setBoundsForAllObjects();

        setAspectForAllObjects();

        addActionListeners();

        addAllObjects();

        drawUsersManagerView();
    }

    public void collectDataFromTable() {

        propertiesManagerController = new PropertiesManagerController();
        data = new ArrayList<String[]>();
        data = propertiesManagerController.collectDataFromTable();
    }

    public void createAllObjects() {

        propertiesManagerView = new JFrame();

        model = new DefaultTableModel();

        model.addColumn("Id");
        model.addColumn("User Email");
        model.addColumn("Country");
        model.addColumn("City");
        model.addColumn("Address");

        for (String[] d : data) {
            model.addRow(d);
        }

        BorderClass borderClass = new BorderClass();

        propertiesManagerTable = new JTable(model) { // I'm super bad, dar asta e; asta sunt
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                JComponent jc = (JComponent) super.prepareRenderer(renderer, row, column);
                jc.setBorder(borderClass.getCustomBorder());
                return jc;
            }
        };

        propertiesManagerTable.setShowVerticalLines(false);
        propertiesManagerTable.setShowHorizontalLines(false);
        propertiesManagerTable.setAutoCreateRowSorter(true);

        propertiesManagerTable.setRowHeight(40);
        tablePanel = new JScrollPane(propertiesManagerTable);
        propertiesManagerTable.setFillsViewportHeight(true);
        propertiesManagerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        goBackButton = new JButton("Go Back");
        deletePropertyButton = new JButton("Delete Property");
    }

    public void setBoundsForAllObjects() {

        tablePanel.setBounds(50, 100, 1300, 500);

        TableColumnModel columnModel = propertiesManagerTable.getColumnModel();

        columnModel.getColumn(0).setPreferredWidth(320);
        columnModel.getColumn(1).setPreferredWidth(110);
        columnModel.getColumn(2).setPreferredWidth(110);
        columnModel.getColumn(3).setPreferredWidth(200);
        columnModel.getColumn(4).setPreferredWidth(160);

        goBackButton.setBounds(55, 650, 230, 50);
        deletePropertyButton.setBounds(305, 650, 230, 50);

    }

    public void addAllObjects() {

        propertiesManagerView.add(tablePanel);

        propertiesManagerView.add(goBackButton);
        propertiesManagerView.add(deletePropertyButton);
    }

    public void drawUsersManagerView() {

        propertiesManagerView.setTitle("Manage Properties");
        propertiesManagerView.setSize(1420, 900);
        propertiesManagerView.setLayout(null);
        propertiesManagerView.setLocationRelativeTo(null);
        propertiesManagerView.setVisible(true);
        propertiesManagerView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setAspectForAllObjects() {

        setFontsForAllObjects(new FontClass());
        setColorsForAllObjects(new ColorClass());
        setBordersForAllObjects(new BorderClass());
    }

    public void setFontsForAllObjects(FontClass fontClass) {

        propertiesManagerTable.getTableHeader().setFont(fontClass.getCustomLabelFont());
        propertiesManagerTable.setFont(fontClass.getCustomTextFieldFont());

        goBackButton.setFont(fontClass.getCustomButtonFont());
        deletePropertyButton.setFont(fontClass.getCustomButtonFont());
    }

    public void setColorsForAllObjects(ColorClass colorClass) {

        propertiesManagerView.getContentPane().setBackground(colorClass.getBackgroundColor());

        propertiesManagerTable.getTableHeader().setForeground(colorClass.getButtonTextFontColor());
        propertiesManagerTable.getTableHeader().setBackground(colorClass.getButtonBackgroundColor());


        propertiesManagerTable.setForeground(colorClass.getTextFieldFontColor());
        propertiesManagerTable.setBackground(colorClass.getBackgroundColor());

        tablePanel.setBackground(colorClass.getBackgroundColor());

        goBackButton.setForeground(colorClass.getButtonTextFontColor());
        deletePropertyButton.setForeground(colorClass.getButtonTextFontColor());

        goBackButton.setBackground(colorClass.getButtonBackgroundColor());
        deletePropertyButton.setBackground(colorClass.getButtonBackgroundColor());

    }

    public void setBordersForAllObjects(BorderClass borderClass) {

        propertiesManagerTable.getTableHeader().setBorder(borderClass.getButtonBorder());

        propertiesManagerTable.setBorder(borderClass.getCustomBorder());

        tablePanel.setBorder(borderClass.getCustomBorder());

        goBackButton.setBorder(borderClass.getButtonBorder());
        deletePropertyButton.setBorder(borderClass.getButtonBorder());
    }

    public void addActionListeners() {

        goBackButton.addActionListener(a -> {

            propertiesManagerView.dispose();
            new AdminView();
        });

        deletePropertyButton.addActionListener(a -> {

            Vector<String> propertyData = model.getDataVector().elementAt(propertiesManagerTable.getSelectedRow());
            propertiesManagerController.deleteProperty(propertyData);

            propertiesManagerView.dispose();
            new PropertiesManagerView();
        });

    }
}
