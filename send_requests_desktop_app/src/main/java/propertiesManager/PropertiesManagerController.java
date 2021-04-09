package propertiesManager;

import service.PropertiesManagerService;

import java.util.ArrayList;
import java.util.Vector;

public class PropertiesManagerController {

    private PropertiesManagerService propertiesManagerService;

    public PropertiesManagerController() {

        propertiesManagerService = new PropertiesManagerService();
    }

    public ArrayList<String[]> collectDataFromTable() {

        return propertiesManagerService.collectDataFromTable();
    }

    public void deleteProperty(Vector<String> propertyData) {

        propertiesManagerService.deleteProperty(propertyData);
    }

}
