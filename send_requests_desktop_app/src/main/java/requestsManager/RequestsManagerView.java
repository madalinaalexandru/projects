package requestsManager;

import admin.AdminView;
import customization.BorderClass;
import customization.ColorClass;
import customization.FontClass;
import repository.RequestTypeRepo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.Vector;

public class RequestsManagerView {

    private RequestsManagerController requestsManagerController;
    private JFrame requestsManagerView;
    private DefaultTableModel model;
    private JTable requestsManagerTable;
    private ArrayList<String[]> data;
    private JScrollPane tablePanel;

    private JButton goBackButton;
    private JButton deleteRequestButton;
    private JButton saveButton;
    private JButton findButton;

    private JButton plusButton;
    private JButton deleteRequestTypeButton;
    private JButton addRequestTypeButton;

    private JLabel findLabel;
    private JTextField findTextField;

    private DefaultTableModel modelRequestType;
    private JTable requestTypesManagerTable;
    private ArrayList<String> requestTypes;
    private JScrollPane requestTypeTablePanel;

    public RequestsManagerView() {

        collectDataFromTable();

        createAllObjects();

        setBoundsForAllObjects();

        setAspectForAllObjects();

        addActionListeners();

        addAllObjects();

        drawUsersManagerView();
    }

    public void collectDataFromTable() {

        requestsManagerController = new RequestsManagerController();
        data = new ArrayList<String[]>();
        data = requestsManagerController.collectDataFromTable();
    }

    public void collectRequestTypes() {

        requestTypes = (new RequestTypeRepo()).getAllRequestTypes();
    }

    public void createAllObjects() {

        modelRequestType = new DefaultTableModel();

        modelRequestType.addColumn("Request type");

        BorderClass borderClass = new BorderClass();

        collectRequestTypes();

        for (String s : requestTypes) {
            modelRequestType.addRow(new String[]{s});
        }

        requestTypesManagerTable = new JTable(modelRequestType) { // I'm super bad, dar asta e; asta sunt
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                JComponent jc = (JComponent) super.prepareRenderer(renderer, row, column);
                jc.setBorder(borderClass.getCustomBorder());
                return jc;
            }
        };

        requestTypesManagerTable.setShowVerticalLines(false);
        requestTypesManagerTable.setShowHorizontalLines(false);
        requestTypesManagerTable.setAutoCreateRowSorter(true);

        requestTypesManagerTable.setRowHeight(40);
        requestTypeTablePanel = new JScrollPane(requestTypesManagerTable);
        requestTypesManagerTable.setFillsViewportHeight(true);
        requestTypesManagerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        requestsManagerView = new JFrame();

        model = new DefaultTableModel();

        model.addColumn("Id");
        model.addColumn("Property Id");
        model.addColumn("User Email");
        model.addColumn("Type");
        model.addColumn("Status");
        model.addColumn("Date");

        for (String[] d : data) {
            model.addRow(d);
        }

        requestsManagerTable = new JTable(model) { // I'm super bad, dar asta e; asta sunt
            public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
                JComponent jc = (JComponent) super.prepareRenderer(renderer, row, column);
                jc.setBorder(borderClass.getCustomBorder());
                return jc;
            }
        };

        requestsManagerTable.setShowVerticalLines(false);
        requestsManagerTable.setShowHorizontalLines(false);
        requestsManagerTable.setAutoCreateRowSorter(true);

        requestsManagerTable.setRowHeight(40);
        tablePanel = new JScrollPane(requestsManagerTable);
        requestsManagerTable.setFillsViewportHeight(true);
        requestsManagerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        goBackButton = new JButton("Go Back");
        deleteRequestButton = new JButton("Delete Request");
        saveButton = new JButton("Save Changes");
        findButton = new JButton("Search");

        plusButton = new JButton("+");
        deleteRequestTypeButton = new JButton("Delete");
        addRequestTypeButton = new JButton("Confirm");

        findLabel = new JLabel("Enter keyword");
        findTextField = new JTextField();

    }

    public void setBoundsForAllObjects() {

        tablePanel.setBounds(50, 100, 1300, 500);
        requestTypeTablePanel.setBounds(900, 620, 300, 200);

        TableColumnModel columnModel = requestsManagerTable.getColumnModel();

        columnModel.getColumn(0).setPreferredWidth(280);
        columnModel.getColumn(1).setPreferredWidth(280);
        columnModel.getColumn(2).setPreferredWidth(160);
        columnModel.getColumn(3).setPreferredWidth(200);
        columnModel.getColumn(4).setPreferredWidth(70);
        columnModel.getColumn(5).setPreferredWidth(160);

        columnModel = requestTypesManagerTable.getColumnModel();
        columnModel.getColumn(0).setPreferredWidth(200);

        goBackButton.setBounds(55, 650, 230, 50);
        saveButton.setBounds(305, 650, 230, 50);
        deleteRequestButton.setBounds(555, 650, 230, 50);
        findButton.setBounds(370, 30, 190, 40);

        plusButton.setBounds(1220, 620, 100, 40);
        addRequestTypeButton.setBounds(1220, 670, 100, 40);
        deleteRequestTypeButton.setBounds(1220, 720, 100, 40);

        findLabel.setBounds(55, 20, 300, 30);
        findTextField.setBounds(55, 50, 300, 30);

    }

    public void addAllObjects() {

        requestsManagerView.add(tablePanel);

        requestsManagerView.add(goBackButton);
        requestsManagerView.add(saveButton);
        requestsManagerView.add(deleteRequestButton);
        requestsManagerView.add(findButton);

        requestsManagerView.add(plusButton);
        requestsManagerView.add(addRequestTypeButton);
        requestsManagerView.add(deleteRequestTypeButton);

        requestsManagerView.add(findLabel);
        requestsManagerView.add(findTextField);

        requestsManagerView.add(requestTypeTablePanel);
    }

    public void drawUsersManagerView() {

        requestsManagerView.setTitle("Manage Requests");
        requestsManagerView.setSize(1420, 900);
        requestsManagerView.setLayout(null);
        requestsManagerView.setLocationRelativeTo(null);
        requestsManagerView.setVisible(true);
        requestsManagerView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setAspectForAllObjects() {

        setFontsForAllObjects(new FontClass());
        setColorsForAllObjects(new ColorClass());
        setBordersForAllObjects(new BorderClass());
    }

    public void setFontsForAllObjects(FontClass fontClass) {

        requestsManagerTable.getTableHeader().setFont(fontClass.getCustomLabelFont());
        requestsManagerTable.setFont(fontClass.getCustomTextFieldFont());

        requestTypesManagerTable.getTableHeader().setFont(fontClass.getCustomTextFieldFont());
        requestTypesManagerTable.setFont(fontClass.getTextAreaContentFont());

        goBackButton.setFont(fontClass.getCustomButtonFont());
        saveButton.setFont(fontClass.getCustomButtonFont());
        deleteRequestButton.setFont(fontClass.getCustomButtonFont());
        findButton.setFont(fontClass.getCustomButtonFont());
        plusButton.setFont(fontClass.getCustomSmallButtonFont());
        addRequestTypeButton.setFont(fontClass.getCustomSmallButtonFont());
        deleteRequestTypeButton.setFont(fontClass.getCustomSmallButtonFont());

        findLabel.setFont(fontClass.getCustomLabelFont());
        findTextField.setFont(fontClass.getCustomTextFieldFont());
    }

    public void setColorsForAllObjects(ColorClass colorClass) {

        requestsManagerView.getContentPane().setBackground(colorClass.getBackgroundColor());

        requestsManagerTable.getTableHeader().setForeground(colorClass.getButtonTextFontColor());
        requestsManagerTable.getTableHeader().setBackground(colorClass.getButtonBackgroundColor());

        requestsManagerTable.setForeground(colorClass.getTextFieldFontColor());
        requestsManagerTable.setBackground(colorClass.getBackgroundColor());

        requestTypesManagerTable.getTableHeader().setForeground(colorClass.getButtonTextFontColor());
        requestTypesManagerTable.getTableHeader().setBackground(colorClass.getButtonBackgroundColor());

        requestTypesManagerTable.setForeground(colorClass.getTextFieldFontColor());
        requestTypesManagerTable.setBackground(colorClass.getBackgroundColor());

        tablePanel.setBackground(colorClass.getBackgroundColor());

        goBackButton.setForeground(colorClass.getButtonTextFontColor());
        saveButton.setForeground(colorClass.getButtonTextFontColor());
        deleteRequestButton.setForeground(colorClass.getButtonTextFontColor());
        findButton.setForeground(colorClass.getButtonTextFontColor());
        plusButton.setForeground(colorClass.getButtonTextFontColor());
        addRequestTypeButton.setForeground(colorClass.getButtonTextFontColor());
        deleteRequestTypeButton.setForeground(colorClass.getButtonTextFontColor());

        goBackButton.setBackground(colorClass.getButtonBackgroundColor());
        saveButton.setBackground(colorClass.getButtonBackgroundColor());
        deleteRequestButton.setBackground(colorClass.getButtonBackgroundColor());
        findButton.setBackground(colorClass.getButtonBackgroundColor());
        plusButton.setBackground(colorClass.getButtonBackgroundColor());
        addRequestTypeButton.setBackground(colorClass.getButtonBackgroundColor());
        deleteRequestTypeButton.setBackground(colorClass.getButtonBackgroundColor());

        findLabel.setForeground(colorClass.getLabelFontColor());
        findTextField.setForeground(colorClass.getTextFieldFontColor());
        findTextField.setBackground(colorClass.getBackgroundColor());

    }

    public void setBordersForAllObjects(BorderClass borderClass) {

        requestsManagerTable.getTableHeader().setBorder(borderClass.getButtonBorder());
        requestTypesManagerTable.getTableHeader().setBorder(borderClass.getButtonBorder());

        requestsManagerTable.setBorder(borderClass.getCustomBorder());
        requestTypesManagerTable.setBorder(borderClass.getCustomBorder());

        tablePanel.setBorder(borderClass.getCustomBorder());

        goBackButton.setBorder(borderClass.getButtonBorder());
        saveButton.setBorder(borderClass.getButtonBorder());
        deleteRequestButton.setBorder(borderClass.getButtonBorder());
        findButton.setBorder(borderClass.getButtonBorder());
        plusButton.setBorder(borderClass.getButtonBorder());
        addRequestTypeButton.setBorder(borderClass.getButtonBorder());
        deleteRequestTypeButton.setBorder(borderClass.getButtonBorder());

        findTextField.setBorder(borderClass.getCustomBorder());
    }

    public void addActionListeners() {

        goBackButton.addActionListener(a -> {

            requestsManagerView.dispose();
            new AdminView();
        });

        deleteRequestButton.addActionListener(a -> {

            Vector<String> requestData = model.getDataVector().elementAt(requestsManagerTable.getSelectedRow());
            requestsManagerController.deleteRequest(requestData);

            requestsManagerView.dispose();
            new RequestsManagerView();
        });

        saveButton.addActionListener(a -> {

            Vector<String> requestData = model.getDataVector().elementAt(requestsManagerTable.getSelectedRow());
            requestsManagerController.updateRequest(requestData.elementAt(0), requestData.elementAt(4));

            requestsManagerView.dispose();
            new RequestsManagerView();
        });

        findButton.addActionListener(a -> {

            String keyword = findTextField.getText();

            ArrayList<String[]> resultData;

            resultData = requestsManagerController.findDataWithKeyword(keyword);

            model = new DefaultTableModel();

            model.addColumn("Id");
            model.addColumn("Property Id");
            model.addColumn("User Email");
            model.addColumn("Type");
            model.addColumn("Status");
            model.addColumn("Date");

            for (String[] d : resultData) {
                model.addRow(d);
            }

            requestsManagerTable.setModel(model);
            requestsManagerTable.removeAll();
            requestsManagerTable.revalidate();
            requestsManagerTable.repaint();
        });

        plusButton.addActionListener(a -> {

            modelRequestType.addRow(new String[]{});
        });

        addRequestTypeButton.addActionListener(a -> {
            Vector<String> requestData = modelRequestType.getDataVector().elementAt(requestTypesManagerTable.getSelectedRow());
            requestsManagerController.addRequestType(requestData.elementAt(0));

            requestsManagerView.dispose();
            new RequestsManagerView();
        });

        deleteRequestTypeButton.addActionListener(a -> {

            Vector<String> requestData = modelRequestType.getDataVector().elementAt(requestTypesManagerTable.getSelectedRow());
            requestsManagerController.deleteRequestType(requestData.elementAt(0));

            requestsManagerView.dispose();
            new RequestsManagerView();
        });

    }
}
