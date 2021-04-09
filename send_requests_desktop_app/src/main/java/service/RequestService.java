package service;

import dto.RequestDto;
import entity.Property;
import entity.User;
import repository.RequestRepo;

import java.time.LocalDateTime;

public class RequestService {

    private RequestRepo requestRepo;

    public RequestService() {

        requestRepo = new RequestRepo();
    }

    public User addRequest(User u, String type, Property property) {

        RequestDto requestDto = new RequestDto();
        requestDto.setDate(LocalDateTime.now());
        requestDto.setUser(u);
        requestDto.setType(type);
        requestDto.setStatus("Pending");
        requestDto.setProperty(property);

        u = requestRepo.addRequest(requestDto);

        return u;
    }
}
