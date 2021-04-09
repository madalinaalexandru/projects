package profile;

import entity.Property;
import entity.User;
import service.ProfileService;

public class ProfileController {

    ProfileService profileService;

    public ProfileController() {
        profileService = new ProfileService();
    }

    public User deleteProperty(String property, User u) {

        int i = 0;
        String country = new String("");
        String city = new String("");
        String address = new String("");
        char c = property.charAt(i);

        while (c != ',') {
            country += c;
            i++;
            c = property.charAt(i);
        }
        c += 2;
        i += 2;

        while (c != ',') {
            city += property.charAt(i);
            i++;
            c = property.charAt(i);
        }

        c += 2;
        i += 2;

        while (i != property.length()) {
            address += property.charAt(i);
            i++;
            if (i < property.length()) c = property.charAt(i);
        }

        Property p = profileService.findProperty(u, address);

        u = profileService.deleteProperty(u, p);

        return u;
    }

    public Property findProperty(User u, String address) {

        return profileService.findProperty(u, address);
    }

    public User deleteRequest(String id, User u) {

        return profileService.deleteRequest(id, u);
    }
}
