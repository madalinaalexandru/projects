package service;

import dto.UserDto;
import entity.User;
import repository.UserRepo;

public class EditProfileService {

    UserRepo userRepo;

    public EditProfileService() {

        userRepo = new UserRepo();
    }

    public User updateAccount(String id, String firstName, String lastName, String address, String email, String phoneNumber,
                              String password, boolean marriageStatus, boolean legalStatus) {

        UserDto userDto = new UserDto();

        userDto.setId(id);
        userDto.setFirstName(firstName);
        userDto.setLastName(lastName);
        userDto.setAddress(address);
        userDto.setEmail(email);
        userDto.setPassword(password);
        userDto.setPhoneNumber(phoneNumber);
        userDto.setLegalStatus(legalStatus);
        userDto.setMarriageStatus(marriageStatus);

        return userRepo.updateAccount(userDto);
    }
}
