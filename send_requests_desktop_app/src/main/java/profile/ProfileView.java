package profile;

import customization.BorderClass;
import customization.ColorClass;
import customization.FontClass;
import editProfile.EditProfileView;
import entity.Property;
import entity.Request;
import entity.User;
import logIn.LogInView;
import messages.ErrorMessages;
import messages.ProfileInformation;
import property.PropertyView;
import request.RequestView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class ProfileView {

    private ProfileController profileController;
    private JFrame profileView;

    private JTextArea welcomeMessage;
    private JTextArea infoMessage;

    private JEditorPane profileInformationTextField;
    private JButton editProfileButton;
    private JButton logOutButton;

    private JLabel propertyListLabel;
    private JButton deletePropertiesButton;
    private JButton addPropertyButton;

    private JLabel requestsListLabel;
    private JButton deleteRequestsButton;
    private JButton addRequestButton;

    private JPanel propertyList;
    private JPanel requestsList;

    private ProfileInformation profileInfo;

    private User user;

    private ArrayList<JCheckBox> checkBoxesProperties;
    private ArrayList<JCheckBox> checkBoxesRequests;
    private ArrayList<String> requestIds;

    public ProfileView(User u) {

        user = u;

        createAllObjects();

        setBoundsForAllObjects();

        setAspectForAllObjects();

        addActionListeners();

        addAllObjects();

        drawProfileView();
    }

    public void createAllObjects() {

        profileController = new ProfileController();

        profileInfo = new ProfileInformation(user);

        profileView = new JFrame();

        welcomeMessage = new JTextArea(1, 1);
        welcomeMessage.setEditable(false);
        welcomeMessage.append("Profile");

        infoMessage = new JTextArea(1, 1);
        infoMessage.setEditable(false);
        infoMessage.append("(*) Information available for your profile.");

        profileInformationTextField = new JEditorPane("text/html", "");
        profileInformationTextField.setText(profileInfo.getInformation());
        profileInformationTextField.setEditable(false);
        editProfileButton = new JButton("Edit profile");
        logOutButton = new JButton("Log Out");

        propertyListLabel = new JLabel("Properties");

        propertyList = new JPanel();


        deletePropertiesButton = new JButton("Remove properties");
        addPropertyButton = new JButton("Add property");

        requestsListLabel = new JLabel("Active requests");
        requestsList = new JPanel();
        deleteRequestsButton = new JButton("Remove requests");
        addRequestButton = new JButton("Add request");

    }


    public void setBoundsForAllObjects() {

        welcomeMessage.setBounds(100, 32, 350, 60);
        infoMessage.setBounds(100, 90, 350, 60);
        editProfileButton.setBounds(600, 40, 200, 60);
        logOutButton.setBounds(830, 40, 200, 60);

        profileInformationTextField.setBounds(50, 160, 1030, 400);

        propertyListLabel.setBounds(50, 600, 100, 30);
        propertyList.setBounds(50, 640, 500, 250);
        deletePropertiesButton.setBounds(415, 600, 135, 30);
        addPropertyButton.setBounds(232, 600, 135, 30);

        requestsListLabel.setBounds(580, 600, 150, 30);
        requestsList.setBounds(580, 640, 500, 250);
        deleteRequestsButton.setBounds(945, 600, 135, 30);
        addRequestButton.setBounds(763, 600, 135, 30);
    }

    public void setAspectForAllObjects() {

        setFontsForAllObjects(new FontClass());
        setColorsForAllObjects(new ColorClass());
        setBordersForAllObjects(new BorderClass());

    }

    public void addAllObjects() {

        profileView.add(welcomeMessage);
        profileView.add(infoMessage);
        profileView.add(editProfileButton);
        profileView.add(logOutButton);

        profileView.add(profileInformationTextField);

        profileView.add(propertyListLabel);

        setPropertiesPanel(new FontClass(), new ColorClass());
        propertyList.setLayout(new BoxLayout(propertyList, BoxLayout.Y_AXIS));
        propertyList.setAlignmentX(Component.LEFT_ALIGNMENT);
        profileView.add(propertyList);
        profileView.add(addPropertyButton);
        profileView.add(deletePropertiesButton);

        profileView.add(requestsListLabel);
        setRequestsPanel(new FontClass(), new ColorClass());
        requestsList.setLayout(new BoxLayout(requestsList, BoxLayout.Y_AXIS));
        requestsList.setAlignmentX(Component.LEFT_ALIGNMENT);
        profileView.add(requestsList);
        profileView.add(addRequestButton);
        profileView.add(deleteRequestsButton);

    }

    public void drawProfileView() {

        profileView.setTitle("Profile Manager");
        profileView.setSize(1150, 950);
        profileView.setLayout(null);
        profileView.setLocationRelativeTo(null);
        profileView.setVisible(true);
        profileView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void setFontsForAllObjects(FontClass customFonts) {

        welcomeMessage.setFont(customFonts.getTextAreaTitleFont());
        infoMessage.setFont(customFonts.getTextAreaContentFont());
        editProfileButton.setFont(customFonts.getCustomButtonFont());
        logOutButton.setFont(customFonts.getCustomButtonFont());

        profileInformationTextField.putClientProperty(JEditorPane.HONOR_DISPLAY_PROPERTIES, Boolean.TRUE);
        profileInformationTextField.setFont(customFonts.getCustomLabelFont());

        propertyListLabel.setFont(customFonts.getCustomLabelFont());
        propertyList.setFont(customFonts.getCustomTextFieldFont());
        addPropertyButton.setFont(customFonts.getCustomSmallButtonFont());
        deletePropertiesButton.setFont(customFonts.getCustomSmallButtonFont());

        requestsListLabel.setFont(customFonts.getCustomLabelFont());
        requestsList.setFont(customFonts.getCustomTextFieldFont());
        addRequestButton.setFont(customFonts.getCustomSmallButtonFont());
        deleteRequestsButton.setFont(customFonts.getCustomSmallButtonFont());

    }

    public void setBordersForAllObjects(BorderClass customBorders) {

        editProfileButton.setBorder(customBorders.getButtonBorder());
        logOutButton.setBorder(customBorders.getButtonBorder());
        addPropertyButton.setBorder(customBorders.getButtonBorder());
        deletePropertiesButton.setBorder(customBorders.getButtonBorder());
        addRequestButton.setBorder(customBorders.getButtonBorder());
        deleteRequestsButton.setBorder(customBorders.getButtonBorder());

        profileInformationTextField.setBorder(customBorders.getCustomBorder());
        propertyList.setBorder(customBorders.getCustomBorder());
        requestsList.setBorder(customBorders.getCustomBorder());
    }

    public void setColorsForAllObjects(ColorClass customColors) {

        profileView.getContentPane().setBackground(customColors.getBackgroundColor());

        editProfileButton.setBackground(customColors.getButtonBackgroundColor());
        logOutButton.setBackground(customColors.getButtonBackgroundColor());
        addRequestButton.setBackground(customColors.getButtonBackgroundColor());
        addPropertyButton.setBackground(customColors.getButtonBackgroundColor());
        deletePropertiesButton.setBackground(customColors.getButtonBackgroundColor());
        deleteRequestsButton.setBackground(customColors.getButtonBackgroundColor());

        profileInformationTextField.setBackground(customColors.getBackgroundColor());
        propertyList.setBackground(customColors.getBackgroundColor());
        requestsList.setBackground(customColors.getBackgroundColor());

        editProfileButton.setForeground(customColors.getButtonTextFontColor());
        logOutButton.setForeground(customColors.getButtonTextFontColor());
        deleteRequestsButton.setForeground(customColors.getButtonTextFontColor());
        addPropertyButton.setForeground(customColors.getButtonTextFontColor());
        deletePropertiesButton.setForeground(customColors.getButtonTextFontColor());
        addRequestButton.setForeground(customColors.getButtonTextFontColor());

        welcomeMessage.setForeground(customColors.getLabelFontColor());
        infoMessage.setForeground(customColors.getTextFieldFontColor());

        propertyListLabel.setForeground(customColors.getLabelFontColor());
        requestsListLabel.setForeground(customColors.getLabelFontColor());

        profileInformationTextField.setForeground(customColors.getTextFieldFontColor());
        propertyList.setForeground(customColors.getTextFieldFontColor());
        propertyList.setForeground(customColors.getTextFieldFontColor());

    }

    public void setPropertiesPanel(FontClass fontClass, ColorClass colorClass) {

        checkBoxesProperties = new ArrayList<JCheckBox>();
        for (Property p : user.getProperties()) {

            JCheckBox box = new JCheckBox(profileInfo.propertyToString(p));
            checkBoxesProperties.add(box);
            box.setFont(fontClass.getCustomTextFieldFont());
            box.setForeground(colorClass.getTextFieldFontColor());
            box.setBackground(colorClass.getBackgroundColor());
            propertyList.add(box);
        }

    }

    public void setRequestsPanel(FontClass fontClass, ColorClass colorClass) {

        checkBoxesRequests = new ArrayList<JCheckBox>();
        requestIds = new ArrayList<String>();

        for (Request r : user.getRequests()) {

            if (!r.getStatus().equals("Cancelled")) {
                requestIds.add(r.getId());
                JCheckBox box = new JCheckBox(profileInfo.requestToString(r));
                checkBoxesRequests.add(box);
                box.setFont(fontClass.getCustomTextFieldFont());
                box.setForeground(colorClass.getTextFieldFontColor());
                box.setBackground(colorClass.getBackgroundColor());
                requestsList.add(box);
            }
        }
    }

    public void refresh(User u) {

        profileView.dispose();
        new ProfileView(u);

    }

    public Property parsePropertyText(User u, String property) {

        int i = 0;
        String country = new String("");
        String city = new String("");
        String address = new String("");
        char c = property.charAt(i);

        while (c != ',') {
            country += c;
            i++;
            c = property.charAt(i);
        }
        c += 2;
        i += 2;

        while (c != ',') {
            city += property.charAt(i);
            i++;
            c = property.charAt(i);
        }

        c += 2;
        i += 2;

        while (i != property.length()) {
            address += property.charAt(i);
            i++;
            if (i < property.length()) c = property.charAt(i);
        }

        return profileController.findProperty(u, address);
    }

    public void addActionListeners() {

        editProfileButton.addActionListener(a -> {
            profileView.dispose();
            new EditProfileView(user);

        });

        addPropertyButton.addActionListener(a -> new PropertyView(user, this));

        deletePropertiesButton.addActionListener(a -> {

            boolean checked = false;
            User u = new User();
            for (JCheckBox checkBox : checkBoxesProperties) {
                if (checkBox.isSelected()) {
                    checked = true;
                    u = profileController.deleteProperty(checkBox.getText(), user);
                }
            }
            if (checked) {
                refresh(u);
            }
        });

        addRequestButton.addActionListener(a -> {

            boolean checkBoxSelected = false;

            for (JCheckBox checkBox : checkBoxesProperties) {
                if (checkBox.isSelected()) {
                    new RequestView(user, parsePropertyText(user, checkBox.getText()), this);
                    checkBoxSelected = true;
                    break;
                }
            }

            if (!checkBoxSelected) {
                JOptionPane.showMessageDialog(null, ErrorMessages.NO_PROPERTY_SELECTED, ErrorMessages.COULD_NOT_ADD_REQUEST,
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        deleteRequestsButton.addActionListener(a -> {

            boolean checked = false;
            User u = new User();

            for (int i = 0; i < checkBoxesRequests.size(); i++) {
                if (checkBoxesRequests.get(i).isSelected()) {
                    checked = true;
                    String id = requestIds.get(i);
                    u = profileController.deleteRequest(requestIds.get(i), user);
                    requestIds.remove(id);
                    break;
                }
            }

            if (checked) {
                refresh(u);
            }
        });

        logOutButton.addActionListener(a -> {
            profileView.dispose();
            new LogInView();
        });
    }


}
