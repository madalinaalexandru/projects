package usersManager;

import service.UsersManagerService;

import java.util.ArrayList;
import java.util.Vector;

public class UsersManagerController {

    private UsersManagerService usersManagerService;

    public UsersManagerController() {

        usersManagerService = new UsersManagerService();
    }

    public ArrayList<String[]> collectDataFromTable() {

        return usersManagerService.collectDataFromTable();
    }

    public void deleteUser(Vector<String> userData) {

        usersManagerService.deleteUser(userData);
    }
}
