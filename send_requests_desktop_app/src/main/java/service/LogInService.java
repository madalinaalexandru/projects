package service;

import entity.User;
import messages.ErrorMessages;
import repository.UserRepo;

import javax.swing.*;

public class LogInService {

    UserRepo userRepo;

    public LogInService() {

        userRepo = new UserRepo();

    }

    public User logIn(String email, String password) {

        User u = userRepo.findUserByEmail(email);

        if (u.getPassword().equals(password)) {
            return u;
        } else {

            JOptionPane.showMessageDialog(null, ErrorMessages.WRONG_PASSWORD, ErrorMessages.LOGIN_FAILED,
                    JOptionPane.ERROR_MESSAGE);
            return null;
        }

    }

}