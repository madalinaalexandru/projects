package service;

import entity.Property;
import entity.User;
import repository.PropertyRepo;

public class ProfileService {

    PropertyRepo propertyRepo;

    public ProfileService() {

        propertyRepo = new PropertyRepo();
    }

    public User deleteProperty(User user, Property property) {

        user = propertyRepo.deleteProperty(user, property);

        return user;
    }

    public Property findProperty(User user, String address) {

        return propertyRepo.findPropertyByAddress(user, address);
    }

    public User deleteRequest(String id, User user) {

        user = propertyRepo.deleteRequest(id, user);

        return user;
    }
}
