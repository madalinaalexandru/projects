package service;

import entity.Property;
import repository.PropertyRepo;

import java.util.ArrayList;
import java.util.Vector;

public class PropertiesManagerService {

    private PropertyRepo propertyRepo;

    public PropertiesManagerService() {

        propertyRepo = new PropertyRepo();
    }

    public ArrayList<String[]> collectDataFromTable() {

        ArrayList<Property> allProperties = propertyRepo.collectDataFromTable();

        ArrayList<String[]> data;

        data = new ArrayList<String[]>();

        for (Property p : allProperties) {

            String[] dataForOneProperty = {p.getId(), p.getUser().getEmail(), p.getCountry(), p.getCity(), p.getAddress()};

            data.add(dataForOneProperty);

        }

        return data;
    }

    public void deleteProperty(Vector<String> propertyData) {

        String id = propertyData.elementAt(0);

        propertyRepo.deleteProperty(id);
    }

}
