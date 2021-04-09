package service;


import dto.PropertyDto;
import entity.User;
import repository.PropertyRepo;

public class PropertyService {

    private PropertyRepo propertyRepo;

    public PropertyService() {

        propertyRepo = new PropertyRepo();
    }

    public User addProperty(User u, String country, String city, String address) {

        PropertyDto propertyDto = new PropertyDto();
        propertyDto.setUser(u);
        propertyDto.setCountry(country);
        propertyDto.setCity(city);
        propertyDto.setAddress(address);

        u = propertyRepo.addProperty(propertyDto);

        return u;
    }

}
