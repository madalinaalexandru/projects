package service;

import dto.RegisterDto;
import repository.UserRepo;

public class RegisterService {

    UserRepo userRepo;

    public RegisterService() {

        userRepo = new UserRepo();
    }

    public void registerAccount(String firstName, String lastName, String address, String email, String phoneNumber,
                                String password, boolean marriageStatus, boolean legalStatus) {

        RegisterDto registerDto = new RegisterDto();

        registerDto.setFirstName(firstName);
        registerDto.setLastName(lastName);
        registerDto.setAddress(address);
        registerDto.setEmail(email);
        registerDto.setPassword(password);
        registerDto.setPhoneNumber(phoneNumber);
        registerDto.setLegaStatus(legalStatus);
        registerDto.setMarriageStatus(marriageStatus);

        userRepo.registerUser(registerDto);

    }
}
