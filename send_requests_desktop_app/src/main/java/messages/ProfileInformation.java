package messages;

import entity.Property;
import entity.Request;
import entity.User;

public class ProfileInformation {

    public String information;
    public String properties;

    public ProfileInformation(User u) {

        information = "<span style='color:rgb(83, 140, 198)'><br>&nbsp&nbsp&nbsp <b>First name: </b></span>" + u.getFirstName() +
                "<span style='color:rgb(83, 140, 198)'><br>&nbsp&nbsp&nbsp <b>Last name: </b></span>" + u.getLastName() +
                "<span style='color:rgb(83, 140, 198)'><br>&nbsp&nbsp&nbsp <b>Email address: </b></span>" + u.getEmail() +
                "<span style='color:rgb(83, 140, 198)'><br>&nbsp&nbsp&nbsp <b>Address: </b></span>" + u.getAddress() +
                "<span style='color:rgb(83, 140, 198)'><br>&nbsp&nbsp&nbsp <b>Phone number: </b></span>" + u.getPhoneNumber();

        if (!u.isLegalStatus()) {
            information += "<br>&nbsp&nbsp&nbsp <span style='color:rgb(83, 140, 198)'>Individual</span>";
        } else {
            information += "<br>&nbsp&nbsp&nbsp <span style='color:rgb(83, 140, 198)'>Legal Person</span>";
        }

        if (!u.isMarriageStatus()) {
            information += "<br>&nbsp&nbsp&nbsp <span style='color:rgb(83, 140, 198)'>Married</span>";
        } else {
            information += "<br>&nbsp&nbsp&nbsp <span style='color:rgb(83, 140, 198)'>Not Married</span>";
        }

    }

    public String propertyToString(Property p) {

        return p.getCountry() + ", " + p.getCity() + ", " + p.getAddress();
    }

    public String requestToString(Request r) {
        return r.getDate().getDayOfMonth() + "-" + r.getDate().getMonthValue() + "-" + r.getDate().getYear() + ", " +
                r.getType() + ", Status: " + r.getStatus() + ", Pentru: " + r.getProperty().getAddress() + ";";
    }

    public String getInformation() {
        return information;
    }
}
