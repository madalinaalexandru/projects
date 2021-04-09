package register;

import messages.ErrorMessages;
import service.RegisterService;

import javax.swing.*;

public class RegisterController {

    private RegisterService registerService;

    public RegisterController() {

        registerService = new RegisterService();
    }

    public boolean registerAccount(String email, String password, String firstName, String lastName, String address,
                                   String phoneNumber, boolean isMarried, boolean isLegal, boolean isNotMarried,
                                   boolean isIndividual) {

        if (email.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty()
                || phoneNumber.isEmpty() || address.isEmpty()) {
            JOptionPane.showMessageDialog(null, ErrorMessages.EMPTY_FIELD, ErrorMessages.REGISTRATION_FAILED,
                    JOptionPane.ERROR_MESSAGE);
            return false;
        } else if ((!isMarried && !isNotMarried) || (!isLegal && !isIndividual)) {
            JOptionPane.showMessageDialog(null, ErrorMessages.NO_CHECKBOX, ErrorMessages.REGISTRATION_FAILED,
                    JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            try {
                registerService.registerAccount(firstName, lastName, address, email,
                        phoneNumber, password, isMarried, isLegal);
                return true;

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, ErrorMessages.REGISTRATION_FAILED,
                        ErrorMessages.REGISTRATION_FAILED, JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
    }

}
