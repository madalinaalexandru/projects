package request;

import entity.Property;
import entity.User;
import messages.ErrorMessages;
import service.RequestService;

import javax.swing.*;

public class RequestController {

    private RequestService requestService;

    public RequestController() {

        requestService = new RequestService();
    }

    public User addRequest(User u, String type, Property property) {

        if (u == null || type.isEmpty()) {

            JOptionPane.showMessageDialog(null, ErrorMessages.EMPTY_FIELD, ErrorMessages.COULD_NOT_ADD_REQUEST,
                    JOptionPane.ERROR_MESSAGE);

            return null;
        } else {
            u = requestService.addRequest(u, type, property);
        }

        return u;
    }
}
