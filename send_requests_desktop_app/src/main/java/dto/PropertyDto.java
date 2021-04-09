package dto;

import entity.Property;
import entity.User;

public class PropertyDto {

    private String id;
    private User user;
    private String country;
    private String city;
    private String address;

    public PropertyDto(Property p) {

        id = p.getId();
        country = p.getCountry();
        city = p.getCity();
        address = p.getAddress();
        user = p.getUser();
    }

    public PropertyDto() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
