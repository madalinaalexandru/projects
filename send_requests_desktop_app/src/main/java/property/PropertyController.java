package property;

import entity.User;
import messages.ErrorMessages;
import service.PropertyService;

import javax.swing.*;

public class PropertyController {

    private PropertyService propertyService;

    public PropertyController() {

        propertyService = new PropertyService();
    }

    public User addProperty(User u, String country, String address, String city) {

        if (u == null || country.isEmpty() || address.isEmpty() || city.isEmpty()) {

            JOptionPane.showMessageDialog(null, ErrorMessages.EMPTY_FIELD, ErrorMessages.COULD_NOT_ADD_PROPERTY,
                    JOptionPane.ERROR_MESSAGE);

            return null;
        } else {
            u = propertyService.addProperty(u, country, city, address);
        }

        return u;
    }
}
