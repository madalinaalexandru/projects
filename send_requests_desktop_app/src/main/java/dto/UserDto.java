package dto;

import entity.Property;
import entity.User;

import java.util.ArrayList;

public class UserDto {

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String address;
    private String phoneNumber;
    private boolean marriageStatus;
    private boolean legalStatus;
    private ArrayList<Property> properties;

    public UserDto(User u) {

        properties = new ArrayList<Property>();
        firstName = u.getFirstName();
        lastName = u.getLastName();
        password = u.getPassword();
        email = u.getEmail();
        address = u.getAddress();
        phoneNumber = u.getPhoneNumber();
        marriageStatus = u.isMarriageStatus();
        legalStatus = u.isLegalStatus();
    }

    public UserDto() {
        properties = new ArrayList<Property>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public boolean isMarriageStatus() {
        return marriageStatus;
    }

    public void setMarriageStatus(boolean marriageStatus) {
        this.marriageStatus = marriageStatus;
    }

    public boolean isLegalStatus() {
        return legalStatus;
    }

    public void setLegalStatus(boolean legalStatus) {
        this.legalStatus = legalStatus;
    }

    public ArrayList<Property> getProperties() {
        return properties;
    }
}
