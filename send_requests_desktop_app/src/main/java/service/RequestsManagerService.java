package service;

import entity.Request;
import repository.RequestRepo;
import repository.RequestTypeRepo;

import java.util.ArrayList;
import java.util.Vector;

public class RequestsManagerService {

    private RequestRepo requestRepo;
    private RequestTypeRepo requestTypeRepo;

    public RequestsManagerService() {

        requestRepo = new RequestRepo();
        requestTypeRepo = new RequestTypeRepo();
    }

    public ArrayList<String[]> collectDataFromTable() {

        ArrayList<Request> allRequests = requestRepo.collectDataFromTable();

        ArrayList<String[]> data;

        data = new ArrayList<String[]>();

        for (Request r : allRequests) {

            String[] dataForOneRequest = {r.getId(), r.getProperty().getId(), r.getUser().getEmail(), r.getType(), r.getStatus(), r.getDate().toString()};

            data.add(dataForOneRequest);

        }

        return data;
    }

    public void deleteRequest(Vector<String> requestData) {

        String id = requestData.elementAt(0);

        requestRepo.deleteRequest(id);
    }

    public void updateRequest(String id, String status) {

        requestRepo.updateRequest(id, status);
    }

    public ArrayList<String[]> findDataWithKeyword(String keyword) {

        ArrayList<Request> resultData = requestRepo.findDataWithKeyword(keyword);

        ArrayList<String[]> data;

        data = new ArrayList<String[]>();

        for (Request r : resultData) {

            String[] dataForOneRequest = {r.getId(), r.getProperty().getId(), r.getUser().getEmail(), r.getType(), r.getStatus(), r.getDate().toString()};

            data.add(dataForOneRequest);

        }

        return data;
    }

    public void addRequestType(String name) {

        requestTypeRepo.addRequestType(name);
    }

    public void deleteRequestType(String name) {

        requestTypeRepo.deleteRequestType(name);
    }
}
