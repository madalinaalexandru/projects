package service;

import entity.User;
import repository.UserRepo;

import java.util.ArrayList;
import java.util.Vector;

public class UsersManagerService {

    private UserRepo userRepo;

    public UsersManagerService() {

        userRepo = new UserRepo();
    }

    public ArrayList<String[]> collectDataFromTable() {

        ArrayList<User> allUsers = userRepo.collectDataFromTable();

        ArrayList<String[]> data;

        data = new ArrayList<String[]>();

        for (User u : allUsers) {

            String married;
            String individual;

            if (u.isMarriageStatus()) {
                married = new String("True");
            } else {
                married = new String("False");
            }

            if (u.isLegalStatus()) {
                individual = new String("False");
            } else {
                individual = new String("True");
            }

            String[] dataForOneUser = {u.getId(), u.getFirstName(), u.getLastName(), u.getEmail(),
                    u.getPhoneNumber(), u.getAddress(), married, individual};

            data.add(dataForOneUser);

        }

        return data;
    }

    public void deleteUser(Vector<String> userData) {

        String id = userData.elementAt(0);

        userRepo.deleteUser(id);
    }

    public void addUser(Vector<String> userData) {

        boolean married;
        boolean individual;

        married = userData.elementAt(6).equals("True");

        individual = userData.elementAt(7).equals("True");

        User user = new User(userData.elementAt(1), userData.elementAt(2),
                userData.elementAt(3), "", userData.elementAt(5), userData.elementAt(4),
                married, !individual);

        userRepo.addUser(user);
    }
}
