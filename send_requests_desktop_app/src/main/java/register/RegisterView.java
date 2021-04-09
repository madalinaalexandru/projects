package register;

import customization.BorderClass;
import customization.ColorClass;
import customization.FontClass;
import logIn.LogInView;

import javax.swing.*;

public class RegisterView {

    private RegisterController registerController;

    private JTextArea welcomeMessage;
    private JTextArea infoMessage;

    private JFrame registerView;

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

    private JButton registerButton;
    private JButton logInButton;


    public RegisterView() {

        createAllObjects();

        setBoundsForAllObjects();

        setAspectForAllObjects();

        setActionListeners();

        addAllObjects();

        drawLogInView();

    }

    public void createAllObjects() {

        registerController = new RegisterController();

        registerView = new JFrame();

        welcomeMessage = new JTextArea(1, 1);
        welcomeMessage.setEditable(false);
        welcomeMessage.append("Registration Form");

        infoMessage = new JTextArea(1, 1);
        infoMessage.setEditable(false);
        infoMessage.append("(*)All fields must be completed.");

        firstNameLabel = new JLabel("First name:");
        firstNameTextField = new JTextField();

        lastNameLabel = new JLabel("Last Name:");
        lastNameTextField = new JTextField();

        emailLabel = new JLabel("Email:");
        emailTextField = new JTextField();

        passwordLabel = new JLabel("Password:");
        passwordTextField = new JPasswordField();

        addressLabel = new JLabel("Address:");
        addressTextField = new JTextField();

        phoneLabel = new JLabel("Phone number:");
        phoneTextField = new JTextField();

        marriageStatusLabel = new JLabel("Marriage status:");
        marriedCheckBox = new JCheckBox("Married");
        notMarriedCheckBox = new JCheckBox("Not married");

        statusLabel = new JLabel("I am a(n):");
        individualCheckBox = new JCheckBox("Individual");
        legalPersonCheckBox = new JCheckBox("Legal person");

        logInButton = new JButton("Log In");
        registerButton = new JButton("Register");

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

        logInButton.setBounds(95, 660, 200, 60);
        registerButton.setBounds(320, 660, 200, 60);

    }

    public void setAspectForAllObjects() {

        setFontsForAllObjects(new FontClass());
        setColorsForAllObjects(new ColorClass());
        setBordersForAllObjects(new BorderClass());

    }

    public void addAllObjects() {

        registerView.add(welcomeMessage);
        registerView.add(infoMessage);

        registerView.add(firstNameLabel);
        registerView.add(firstNameTextField);

        registerView.add(lastNameLabel);
        registerView.add(lastNameTextField);

        registerView.add(emailLabel);
        registerView.add(emailTextField);

        registerView.add(passwordLabel);
        registerView.add(passwordTextField);

        registerView.add(addressLabel);
        registerView.add(addressTextField);

        registerView.add(phoneLabel);
        registerView.add(phoneTextField);

        registerView.add(statusLabel);
        registerView.add(individualCheckBox);
        registerView.add(legalPersonCheckBox);

        registerView.add(marriageStatusLabel);
        registerView.add(marriedCheckBox);
        registerView.add(notMarriedCheckBox);

        registerView.add(registerButton);
        registerView.add(logInButton);

    }

    public void drawLogInView() {

        registerView.setTitle("Register");
        registerView.setSize(650, 900);
        registerView.setLayout(null);
        registerView.setLocationRelativeTo(null);
        registerView.setVisible(true);
        registerView.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

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


        logInButton.setFont(customFonts.getCustomButtonFont());
        registerButton.setFont(customFonts.getCustomButtonFont());
        welcomeMessage.setFont(customFonts.getTextAreaTitleFont());
        infoMessage.setFont(customFonts.getTextAreaContentFont());

    }

    private void setColorsForAllObjects(ColorClass customColors) {

        registerView.getContentPane().setBackground(customColors.getBackgroundColor());
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

        logInButton.setForeground(customColors.getButtonTextFontColor());
        registerButton.setForeground(customColors.getButtonTextFontColor());

        logInButton.setBackground(customColors.getButtonBackgroundColor());
        registerButton.setBackground(customColors.getButtonBackgroundColor());

    }

    private void setBordersForAllObjects(BorderClass customBorders) {

        logInButton.setBorder(customBorders.getButtonBorder());
        registerButton.setBorder(customBorders.getButtonBorder());

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

        registerButton.addActionListener(a -> {

            if (registerController.registerAccount(emailTextField.getText(), passwordTextField.getText(),
                    firstNameTextField.getText(), lastNameTextField.getText(), addressTextField.getText(),
                    phoneTextField.getText(), marriedCheckBox.isSelected(), legalPersonCheckBox.isSelected(),
                    notMarriedCheckBox.isSelected(), individualCheckBox.isSelected())) {

                registerView.dispose();
                new LogInView();
            }
        });

        logInButton.addActionListener(a -> {

            registerView.dispose();
            new LogInView();

        });

        marriedCheckBox.addActionListener(a -> marriedStatus());

        notMarriedCheckBox.addActionListener(a -> notMarriedStatus());

        individualCheckBox.addActionListener(a -> individualStatus());

        legalPersonCheckBox.addActionListener(a -> legalPersonStatus());
    }

}
