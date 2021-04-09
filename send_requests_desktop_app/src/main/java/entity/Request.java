package entity;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
public class Request {

    @Id
    private String id;

    @Column
    private String type;

    @Column
    private LocalDateTime date;

    @Column
    private String status;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "requestType_id")
    private RequestType requestType;

    public Request() {

        this.id = UUID.randomUUID().toString();

    }

    public Request(User user, String type, Property property) {

        this.user = user;
        this.type = type;
        this.id = UUID.randomUUID().toString();
        this.date = LocalDateTime.now();
        this.status = "Pending";
        this.property = property;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Property getProperty() {
        return property;
    }

    public void setProperty(Property property) {
        this.property = property;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
