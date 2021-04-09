package logIn;

import admin.AdminView;
import customization.BorderClass;
import customization.ColorClass;
import customization.FontClass;
import entity.Admin;
import entity.User;
import profile.ProfileView;
import register.RegisterView;

import javax.swing.*;

public class LogInView extends JFrame{

    private LogInController logInController;

    private JFrame logInView;

    private JLabel emailLabel;
    private JTextField emailTextField;

    private JLabel passwordLabel;
    private JPasswordField passwordTextField;

    private JButton logInButton;
    private JButton registerButton;

    private JTextArea welcomeMessage;
    private JTextArea infoMessage;

    public LogInView() {

        createAllObjects();

        setBoundsForAllObjects();

        setAspectForAllObjects();

        addAllObjects();

        setButtonActionListeners();

        drawLogInView();

    }

    public void createAllObjects() {

        logInController = new LogInController();

        logInView = new JFrame();

        welcomeMessage = new JTextArea( 1, 1 );
        welcomeMessage.setEditable( false );
        welcomeMessage.append("Welcome to the Cluj-Napoca city hall app!");

        infoMessage = new JTextArea(1,1 );
        infoMessage.setEditable(false);
        infoMessage.append("(*) In order to be able to manage your account please enter your correct \ncredentials " +
                "and Log In. If you don't already have an account, please register first!");

        emailLabel = new JLabel("Email:");
        emailTextField = new JTextField();

        passwordLabel = new JLabel("Password:");
        passwordTextField = new JPasswordField();

        logInButton = new JButton("Log In");
        registerButton = new JButton("Register");

    }

    private void setBoundsForAllObjects() {

        welcomeMessage.setBounds(50, 32, 600, 60);
        infoMessage.setBounds(50, 90, 600, 60);

        emailLabel.setBounds(114, 160, 150, 40);
        emailTextField.setBounds(220, 160, 300, 40);

        passwordLabel.setBounds(114, 220, 150, 40);
        passwordTextField.setBounds(220, 220, 300, 40);

        logInButton.setBounds(114, 290, 200, 60);
        registerButton.setBounds(320, 290, 200, 60);
    }

    private void setAspectForAllObjects() {

        setFontsForAllObjects( new FontClass() );
        setColorsForAllObjects( new ColorClass() );
        setBordersForAllObjects( new BorderClass() );

    }

    private void addAllObjects() {

        logInView.add( welcomeMessage );
        logInView.add( infoMessage );

        logInView.add( emailLabel );
        logInView.add( emailTextField );

        logInView.add( passwordLabel );
        logInView.add( passwordTextField );

        logInView.add( logInButton );
        logInView.add( registerButton );

    }

    private void drawLogInView() {

        logInView.setTitle( "Log In" );
        logInView.setSize(650,450);
        logInView.setLayout( null );
        logInView.setLocationRelativeTo(null);
        logInView.setVisible( true );
        logInView.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

    }

    private void setFontsForAllObjects( FontClass customFonts ) {

        emailLabel.setFont( customFonts.getCustomLabelFont() );
        passwordLabel.setFont( customFonts.getCustomLabelFont() );

        emailTextField.setFont( customFonts.getCustomTextFieldFont() );
        passwordTextField.setFont( customFonts.getCustomTextFieldFont() );

        logInButton.setFont( customFonts.getCustomButtonFont() );
        registerButton.setFont( customFonts.getCustomButtonFont() );

        welcomeMessage.setFont( customFonts.getTextAreaTitleFont() );

        infoMessage.setFont( customFonts.getTextAreaContentFont() );

    }

    private void setColorsForAllObjects( ColorClass customColors ) {

        logInView.getContentPane().setBackground(customColors.getBackgroundColor());

        emailLabel.setForeground( customColors.getLabelFontColor() );
        passwordLabel.setForeground( customColors.getLabelFontColor() );
        welcomeMessage.setForeground( customColors.getLabelFontColor() );

        emailTextField.setForeground( customColors.getTextFieldFontColor() );
        passwordTextField.setForeground( customColors.getTextFieldFontColor() );
        infoMessage.setForeground( customColors.getTextFieldFontColor() );

        logInButton.setForeground( customColors.getButtonTextFontColor() );
        registerButton.setForeground( customColors.getButtonTextFontColor() );

        logInButton.setBackground( customColors.getButtonBackgroundColor() );
        registerButton.setBackground( customColors.getButtonBackgroundColor() );

    }

    private void setBordersForAllObjects( BorderClass customBorders ) {

        logInButton.setBorder( customBorders.getButtonBorder() );
        registerButton.setBorder( customBorders.getButtonBorder() );

        emailTextField.setBorder( customBorders.getCustomBorder() );
        passwordTextField.setBorder( customBorders.getCustomBorder() );

    }

    public void setButtonActionListeners() {

        logInButton.addActionListener(a -> {

            User u = logInController.logIn( emailTextField.getText(), passwordTextField.getText() );

            if ( u != null ) {

                logInView.dispose();

                if(u instanceof Admin){
                    new AdminView();
                } else{
                    new ProfileView( u );
                }

            }

        });

        registerButton.addActionListener(a -> {

            logInView.dispose();
            new RegisterView();

        });

    }
}