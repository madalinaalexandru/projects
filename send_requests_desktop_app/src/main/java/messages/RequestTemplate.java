package messages;

import entity.Property;
import entity.User;

public class RequestTemplate {

    private String buildRequest;
    private String subletRequest;
    private String renovationRequest;
    private String defaultRequest;

    public RequestTemplate(User user, Property property) {

        setBuildRequest(user, property);
        setRenovationRequest(user, property);
        setSubletRequest(user, property);
        setDefaultRequest(user, property);
    }

    public void setBuildRequest(User user, Property property) {

        buildRequest = "Subsemnatul, " + user.getFirstName() + " " + user.getLastName() + ", domiciliat la " +
                user.getAddress() + ", va solicit prin prezenta sa imi aprobati cererea de a construi o cladire pe" +
                " proprietatea ce imi apartine din " + property.getCountry() + ", " + property.getCity() + ", " +
                property.getAddress() + '.';
    }

    public void setSubletRequest(User user, Property property) {

        subletRequest = "Subsemnatul, " + user.getFirstName() + " " + user.getLastName() + ", domiciliat la " +
                user.getAddress() + ", va solicit prin prezenta sa imi aprobati cererea de a oferi spre inchiriere" +
                " proprietatea ce imi apartine din " + property.getCountry() + ", " + property.getCity() + ", " +
                property.getAddress() + '.';
    }

    public void setRenovationRequest(User user, Property property) {

        renovationRequest = "Subsemnatul, " + user.getFirstName() + " " + user.getLastName() + ", domiciliat la " +
                user.getAddress() + ", va solicit prin prezenta sa imi aprobati cererea de a renova constructia ce se afla " +
                "pe proprietatea ce imi apartine din " + property.getCountry() + ", " + property.getCity() + ", " +
                property.getAddress() + '.';
    }

    public void setDefaultRequest(User user, Property property) {

        defaultRequest = "Subsemnatul, " + user.getFirstName() + " " + user.getLastName() + ", domiciliat la " +
                user.getAddress() + ", va solicit prin prezenta sa imi aprobati cererea aferenta proprietatii " +
                "ce imi apartine din " + property.getCountry() + ", " + property.getCity() + ", " +
                property.getAddress() + '.';
    }

    public String getBuildRequest() {
        return buildRequest;
    }

    public String getSubletRequest() {
        return subletRequest;
    }

    public String getRenovationRequest() {
        return renovationRequest;
    }

    public String getDefaultRequest() {
        return defaultRequest;
    }
}
