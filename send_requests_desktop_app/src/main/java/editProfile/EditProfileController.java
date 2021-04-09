package editProfile;

import entity.User;
import messages.ErrorMessages;
import service.EditProfileService;

import javax.swing.*;

public class EditProfileController {

    private EditProfileService editProfileService;

    public EditProfileController() {

        editProfileService = new EditProfileService();
    }

    public User changeCredentials(String id, String email, String password, String firstName, String lastName, String address,
                                  String phoneNumber, boolean isMarried, boolean isLegal, boolean isNotMarried,
                                  boolean isIndividual) {

        if (email.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty()
                || phoneNumber.isEmpty() || address.isEmpty()) {
            JOptionPane.showMessageDialog(null, ErrorMessages.EMPTY_FIELD, ErrorMessages.REGISTRATION_FAILED,
                    JOptionPane.ERROR_MESSAGE);
            return null;
        } else if ((!isMarried && !isNotMarried) || (!isLegal && !isIndividual)) {
            JOptionPane.showMessageDialog(null, ErrorMessages.NO_CHECKBOX, ErrorMessages.REGISTRATION_FAILED,
                    JOptionPane.ERROR_MESSAGE);
            return null;
        } else {
            try {
                return editProfileService.updateAccount(id, firstName, lastName, address, email,
                        phoneNumber, password, isMarried, isLegal);

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, ErrorMessages.REGISTRATION_FAILED,
                        ErrorMessages.REGISTRATION_FAILED, JOptionPane.ERROR_MESSAGE);
                return null;
            }
        }
    }
}
