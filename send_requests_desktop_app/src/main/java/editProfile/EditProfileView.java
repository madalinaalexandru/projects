package editProfile;

import customization.BorderClass;
import customization.ColorClass;
import customization.FontClass;
import entity.User;
import profile.ProfileView;

import javax.swing.*;

public class EditProfileView {

    private EditProfileController editProfileController;

    private JTextArea welcomeMessage;
    private JTextArea infoMessage;

    private JFrame editProfileView;

    private JLabel firstNameLabel;
    private JTextField firstNameTextField;

    private JLabel lastNameLabel;
    private JTextField lastNameTextField;

    private JLabel emailLabel;
    private JTextField emailTextField;

    private JLabel passwordLabel;
    private JPasswordField passwordTextField;

    private JLabel addressLabel;
    private JTextField addressTextField;

    private JLabel phoneLabel;
    private JTextField phoneTextField;

    private JLabel marriageStatusLabel;
    private JCheckBox marriedCheckBox;
    private JCheckBox notMarriedCheckBox;

    private JLabel statusLabel;
    private JCheckBox individualCheckBox;
    private JCheckBox legalPersonCheckBox;

    private JButton okButton;
    private JButton goBackButton;

    private String id;

    private User user;

    public EditProfileView(User u) {

        user = u;

        createAllObjects();

        setBoundsForAllObjects();

        setAspectForAllObjects();

        setActionListeners();

        addAllObjects();

        drawLogInView();

    }

    public void createAllObjects() {

        id = user.getId();

        editProfileController = new EditProfileController();

        editProfileView = new JFrame();

        welcomeMessage = new JTextArea(1, 1);
        welcomeMessage.setEditable(false);
        welcomeMessage.append("Edit your profile information");

        infoMessage = new JTextArea(1, 1);
        infoMessage.setEditable(false);
        infoMessage.append("(*) Change the fields you want to replace.");

        firstNameLabel = new JLabel("First name:");
        firstNameTextField = new JTextField();
        firstNameTextField.setText(user.getFirstName());

        lastNameLabel = new JLabel("Last Name:");
        lastNameTextField = new JTextField();
        lastNameTextField.setText(user.getLastName());

        emailLabel = new JLabel("Email:");
        emailTextField = new JTextField();
        emailTextField.setText(user.getEmail());

        passwordLabel = new JLabel("Password:");
        passwordTextField = new JPasswordField();
        passwordTextField.setText(user.getPassword());

        addressLabel = new JLabel("Address:");
        addressTextField = new JTextField();
        addressTextField.setText(user.getAddress());

        phoneLabel = new JLabel("Phone number:");
        phoneTextField = new JTextField();
        phoneTextField.setText(user.getPhoneNumber());

        marriageStatusLabel = new JLabel("Marriage status:");
        marriedCheckBox = new JCheckBox("Married");
        notMarriedCheckBox = new JCheckBox("Not married");

        marriedCheckBox.setSelected(user.isMarriageStatus());
        notMarriedCheckBox.setSelected(!user.isMarriageStatus());

        statusLabel = new JLabel("I am a(n):");
        individualCheckBox = new JCheckBox("Individual");
        legalPersonCheckBox = new JCheckBox("Legal person");

        individualCheckBox.setSelected(!user.isLegalStatus());
        legalPersonCheckBox.setSelected(user.isLegalStatus());

        okButton = new JButton("Save changes");
        goBackButton = new JButton("Cancel");

    }

    public void setBoundsForAllObjects() {

        welcomeMessage.setBounds(50, 32, 600, 60);
        infoMessage.setBounds(50, 90, 600, 60);

        firstNameLabel.setBounds(95, 160, 150, 40);
        firstNameTextField.setBounds(250, 160, 300, 40);

        lastNameLabel.setBounds(95, 220, 150, 40);
        lastNameTextField.setBounds(250, 220, 300, 40);

        emailLabel.setBounds(95, 280, 150, 40);
        emailTextField.setBounds(250, 280, 300, 40);

        passwordLabel.setBounds(95, 340, 150, 40);
        passwordTextField.setBounds(250, 340, 300, 40);

        addressLabel.setBounds(95, 400, 150, 40);
        addressTextField.setBounds(250, 400, 300, 40);

        phoneLabel.setBounds(95, 460, 150, 40);
        phoneTextField.setBounds(250, 460, 300, 40);

        statusLabel.setBounds(95, 520, 150, 40);
        individualCheckBox.setBounds(250, 520, 100, 40);
        legalPersonCheckBox.setBounds(370, 520, 130, 40);

        marriageStatusLabel.setBounds(95, 580, 200, 40);
        marriedCheckBox.setBounds(250, 580, 100, 40);
        notMarriedCheckBox.setBounds(370, 580, 150, 40);

        okButton.setBounds(95, 660, 200, 60);
        goBackButton.setBounds(320, 660, 200, 60);

    }

    public void setAspectForAllObjects() {

        setFontsForAllObjects(new FontClass());
        setColorsForAllObjects(new ColorClass());
        setBordersForAllObjects(new BorderClass());

    }

    public void addAllObjects() {

        editProfileView.add(welcomeMessage);
        editProfileView.add(infoMessage);

        editProfileView.add(firstNameLabel);
        editProfileView.add(firstNameTextField);

        editProfileView.add(lastNameLabel);
        editProfileView.add(lastNameTextField);

        editProfileView.add(emailLabel);
        editProfileView.add(emailTextField);

        editProfileView.add(passwordLabel);
        editProfileView.add(passwordTextField);

        editProfileView.add(addressLabel);
        editProfileView.add(addressTextField);

        editProfileView.add(phoneLabel);
        editProfileView.add(phoneTextField);

        editProfileView.add(statusLabel);
        editProfileView.add(individualCheckBox);
        editProfileView.add(legalPersonCheckBox);

        editProfileView.add(marriageStatusLabel);
        editProfileView.add(marriedCheckBox);
        editProfileView.add(notMarriedCheckBox);

        editProfileView.add(okButton);
        editProfileView.add(goBackButton);

    }

    public void drawLogInView() {

        editProfileView.setTitle("Modify Profile");
        editProfileView.setSize(650, 900);
        editProfileView.setLayout(null);
        editProfileView.setLocationRelativeTo(null);
        editProfileView.setVisible(true);

    }

    private void setFontsForAllObjects(FontClass customFonts) {

        emailLabel.setFont(customFonts.getCustomLabelFont());
        passwordLabel.setFont(customFonts.getCustomLabelFont());
        firstNameLabel.setFont(customFonts.getCustomLabelFont());
        lastNameLabel.setFont(customFonts.getCustomLabelFont());
        addressLabel.setFont(customFonts.getCustomLabelFont());
        phoneLabel.setFont(customFonts.getCustomLabelFont());
        marriageStatusLabel.setFont(customFonts.getCustomLabelFont());
        statusLabel.setFont(customFonts.getCustomLabelFont());

        emailTextField.setFont(customFonts.getCustomTextFieldFont());
        passwordTextField.setFont(customFonts.getCustomTextFieldFont());
        firstNameTextField.setFont(customFonts.getCustomTextFieldFont());
        lastNameTextField.setFont(customFonts.getCustomTextFieldFont());
        addressTextField.setFont(customFonts.getCustomTextFieldFont());
        phoneTextField.setFont(customFonts.getCustomTextFieldFont());
        marriedCheckBox.setFont(customFonts.getCustomTextFieldFont());
        notMarriedCheckBox.setFont(customFonts.getCustomTextFieldFont());
        individualCheckBox.setFont(customFonts.getCustomTextFieldFont());
        legalPersonCheckBox.setFont(customFonts.getCustomTextFieldFont());


        okButton.setFont(customFonts.getCustomButtonFont());
        goBackButton.setFont(customFonts.getCustomButtonFont());
        welcomeMessage.setFont(customFonts.getTextAreaTitleFont());
        infoMessage.setFont(customFonts.getTextAreaContentFont());

    }

    private void setColorsForAllObjects(ColorClass customColors) {

        editProfileView.getContentPane().setBackground(customColors.getBackgroundColor());
        marriedCheckBox.setBackground(customColors.getBackgroundColor());
        notMarriedCheckBox.setBackground(customColors.getBackgroundColor());
        individualCheckBox.setBackground(customColors.getBackgroundColor());
        legalPersonCheckBox.setBackground(customColors.getBackgroundColor());

        emailLabel.setForeground(customColors.getLabelFontColor());
        firstNameLabel.setForeground(customColors.getLabelFontColor());
        lastNameLabel.setForeground(customColors.getLabelFontColor());
        addressLabel.setForeground(customColors.getLabelFontColor());
        phoneLabel.setForeground(customColors.getLabelFontColor());
        passwordLabel.setForeground(customColors.getLabelFontColor());
        welcomeMessage.setForeground(customColors.getLabelFontColor());

        emailTextField.setForeground(customColors.getTextFieldFontColor());
        passwordTextField.setForeground(customColors.getTextFieldFontColor());
        firstNameTextField.setForeground(customColors.getTextFieldFontColor());
        lastNameTextField.setForeground(customColors.getTextFieldFontColor());
        addressTextField.setForeground(customColors.getTextFieldFontColor());
        phoneLabel.setForeground(customColors.getTextFieldFontColor());
        infoMessage.setForeground(customColors.getTextFieldFontColor());

        okButton.setForeground(customColors.getButtonTextFontColor());

        okButton.setBackground(customColors.getButtonBackgroundColor());

        goBackButton.setForeground(customColors.getButtonTextFontColor());

        goBackButton.setBackground(customColors.getButtonBackgroundColor());

    }

    private void setBordersForAllObjects(BorderClass customBorders) {

        okButton.setBorder(customBorders.getButtonBorder());
        goBackButton.setBorder(customBorders.getButtonBorder());

        emailTextField.setBorder(customBorders.getCustomBorder());
        passwordTextField.setBorder(customBorders.getCustomBorder());
        firstNameTextField.setBorder(customBorders.getCustomBorder());
        lastNameTextField.setBorder(customBorders.getCustomBorder());
        addressTextField.setBorder(customBorders.getCustomBorder());
        phoneTextField.setBorder(customBorders.getCustomBorder());

    }

    public void marriedStatus() {

        if (marriedCheckBox.isSelected()) {
            notMarriedCheckBox.setSelected(false);
        }
    }

    public void notMarriedStatus() {

        if (notMarriedCheckBox.isSelected()) {
            marriedCheckBox.setSelected(false);
        }
    }

    public void individualStatus() {

        if (individualCheckBox.isSelected()) {
            legalPersonCheckBox.setSelected(false);
        }
    }

    public void legalPersonStatus() {

        if (legalPersonCheckBox.isSelected()) {
            individualCheckBox.setSelected(false);
        }
    }

    public void setActionListeners() {

        okButton.addActionListener(a -> {

            User u = editProfileController.changeCredentials(id, emailTextField.getText(), passwordTextField.getText(),
                    firstNameTextField.getText(), lastNameTextField.getText(), addressTextField.getText(),
                    phoneTextField.getText(), notMarriedCheckBox.isSelected(), legalPersonCheckBox.isSelected(),
                    marriedCheckBox.isSelected(), individualCheckBox.isSelected());

            if (u != null) {

                editProfileView.dispose();
                new ProfileView(u);
            }
        });

        goBackButton.addActionListener(a -> {
            editProfileView.dispose();
            new ProfileView(user);
        });

        marriedCheckBox.addActionListener(a -> marriedStatus());

        notMarriedCheckBox.addActionListener(a -> notMarriedStatus());

        individualCheckBox.addActionListener(a -> individualStatus());

        legalPersonCheckBox.addActionListener(a -> legalPersonStatus());
    }
}
