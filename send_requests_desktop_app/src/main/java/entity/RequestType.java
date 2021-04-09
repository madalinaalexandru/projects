package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
public class RequestType {

    @Id
    private String id;

    @OneToMany(mappedBy = "requestType")
    public List<Request> requests;

    @Column
    private String name;

    public RequestType(String name) {

        this.id = UUID.randomUUID().toString();

        requests = new ArrayList<Request>();

        this.name = name;
    }

    public RequestType() {

        this.id = UUID.randomUUID().toString();

        requests = new ArrayList<Request>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
