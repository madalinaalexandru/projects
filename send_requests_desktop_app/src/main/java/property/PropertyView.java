package property;

import customization.BorderClass;
import customization.ColorClass;
import customization.FontClass;
import entity.User;
import profile.ProfileView;

import javax.swing.*;

public class PropertyView {

    private PropertyController propertyController;

    private JFrame propertyView;

    private JTextArea welcomeMessage;
    private JTextArea infoMessage;

    private JLabel countryLabel;
    private JTextField countryTextField;

    private JLabel cityLabel;
    private JTextField cityTextField;

    private JLabel addressLabel;
    private JTextField addressTextField;

    private JButton approveButton;
    private ProfileView profileView;

    public PropertyView(User u, ProfileView profileView) {

        this.profileView = profileView;

        createAllObjects(u);

        setBoundsForAllObjects();

        setAspectForAllObjects();

        addActionListeners(u);

        addAllObjects();

        drawLogInView();
    }

    public void createAllObjects(User u) {

        propertyController = new PropertyController();

        propertyView = new JFrame();

        welcomeMessage = new JTextArea(1, 1);
        welcomeMessage.setEditable(false);
        welcomeMessage.append("Add a property");

        infoMessage = new JTextArea(1, 1);
        infoMessage.setEditable(false);
        infoMessage.append("(*) All fields must be filled in.");

        countryLabel = new JLabel("Country:");
        countryTextField = new JTextField();

        cityLabel = new JLabel("City:");
        cityTextField = new JTextField();

        addressLabel = new JLabel("Address:");
        addressTextField = new JTextField();

        approveButton = new JButton("OK");

    }

    public void setBoundsForAllObjects() {

        welcomeMessage.setBounds(50, 32, 600, 60);
        infoMessage.setBounds(50, 90, 600, 60);

        countryLabel.setBounds(50, 160, 150, 40);
        countryTextField.setBounds(220, 160, 300, 40);

        cityLabel.setBounds(50, 220, 150, 40);
        cityTextField.setBounds(220, 220, 300, 40);

        addressLabel.setBounds(50, 280, 150, 40);
        addressTextField.setBounds(220, 280, 300, 40);

        approveButton.setBounds(220, 360, 200, 60);
    }

    public void setAspectForAllObjects() {

        setFontsForAllObjects(new FontClass());
        setColorsForAllObjects(new ColorClass());
        setBordersForAllObjects(new BorderClass());

    }

    public void addAllObjects() {

        propertyView.add(welcomeMessage);
        propertyView.add(infoMessage);
        propertyView.add(countryLabel);
        propertyView.add(countryTextField);
        propertyView.add(cityLabel);
        propertyView.add(cityTextField);
        propertyView.add(addressLabel);
        propertyView.add(addressTextField);
        propertyView.add(approveButton);

    }

    public void drawLogInView() {

        propertyView.setTitle("Add property");
        propertyView.setSize(650, 500);
        propertyView.setLayout(null);
        propertyView.setLocationRelativeTo(null);
        propertyView.setVisible(true);

    }

    public void setFontsForAllObjects(FontClass customFonts) {
        countryLabel.setFont(customFonts.getCustomLabelFont());
        cityLabel.setFont(customFonts.getCustomLabelFont());
        addressLabel.setFont(customFonts.getCustomLabelFont());

        countryTextField.setFont(customFonts.getCustomTextFieldFont());
        cityTextField.setFont(customFonts.getCustomTextFieldFont());
        addressTextField.setFont(customFonts.getCustomTextFieldFont());

        approveButton.setFont(customFonts.getCustomButtonFont());

        welcomeMessage.setFont(customFonts.getTextAreaTitleFont());

        infoMessage.setFont(customFonts.getTextAreaContentFont());

    }

    public void setColorsForAllObjects(ColorClass customColors) {

        propertyView.getContentPane().setBackground(customColors.getBackgroundColor());

        countryLabel.setForeground(customColors.getLabelFontColor());
        cityLabel.setForeground(customColors.getLabelFontColor());
        addressLabel.setForeground(customColors.getLabelFontColor());
        welcomeMessage.setForeground(customColors.getLabelFontColor());

        countryTextField.setForeground(customColors.getTextFieldFontColor());
        cityTextField.setForeground(customColors.getTextFieldFontColor());
        addressTextField.setForeground(customColors.getTextFieldFontColor());
        infoMessage.setForeground(customColors.getTextFieldFontColor());

        approveButton.setForeground(customColors.getButtonTextFontColor());

        approveButton.setBackground(customColors.getButtonBackgroundColor());

    }

    public void setBordersForAllObjects(BorderClass customBorders) {

        approveButton.setBorder(customBorders.getButtonBorder());

        countryTextField.setBorder(customBorders.getCustomBorder());
        cityTextField.setBorder(customBorders.getCustomBorder());
        addressTextField.setBorder(customBorders.getCustomBorder());
    }

    public void addActionListeners(User u) {

        approveButton.addActionListener(a -> {

            User user = propertyController.addProperty(u, countryTextField.getText(), addressTextField.getText(), cityTextField.getText());

            profileView.refresh(user);
            propertyView.dispose();

        });
    }
}
