package logIn;

import entity.User;
import messages.ErrorMessages;
import service.LogInService;

import javax.swing.*;

public class LogInController {

    private LogInService logInService;

    public LogInController() {

        logInService = new LogInService();

    }

    public User logIn(String email, String password) {

        if (email.isEmpty() && password.isEmpty()) {

            JOptionPane.showMessageDialog(null, ErrorMessages.NO_CREDENTIALS,
                    ErrorMessages.LOGIN_FAILED, JOptionPane.ERROR_MESSAGE);
            return null;
        } else if (email.isEmpty()) {

            JOptionPane.showMessageDialog(null, ErrorMessages.NO_EMAIL, ErrorMessages.LOGIN_FAILED,
                    JOptionPane.ERROR_MESSAGE);
            return null;
        } else if (password.isEmpty()) {

            JOptionPane.showMessageDialog(null, ErrorMessages.NO_PASSWORD, ErrorMessages.LOGIN_FAILED,
                    JOptionPane.ERROR_MESSAGE);
            return null;
        } else {
            try {
                return logInService.logIn(email, password);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, ErrorMessages.NO_USER,
                        ErrorMessages.LOGIN_FAILED, JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }
    }

}
