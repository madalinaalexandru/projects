package requestsManager;

import service.RequestsManagerService;

import java.util.ArrayList;
import java.util.Vector;

public class RequestsManagerController {

    private RequestsManagerService requestsManagerService;

    public RequestsManagerController() {

        requestsManagerService = new RequestsManagerService();
    }

    public ArrayList<String[]> collectDataFromTable() {

        return requestsManagerService.collectDataFromTable();
    }

    public void deleteRequest(Vector<String> requestData) {

        requestsManagerService.deleteRequest(requestData);
    }

    public void updateRequest(String id, String status) {

        requestsManagerService.updateRequest(id, status);
    }

    public ArrayList<String[]> findDataWithKeyword(String keyword) {

        return requestsManagerService.findDataWithKeyword(keyword);
    }

    public void addRequestType(String name) {

        requestsManagerService.addRequestType(name);
    }

    public void deleteRequestType(String name) {

        requestsManagerService.deleteRequestType(name);
    }
}
