package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
//@Table(name = "user")
public class Property {

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private String country;

    @Column
    private String city;

    @Column
    private String address;

    @OneToMany(mappedBy = "property")
    private List<Request> requestList;

    public Property(User user, String country, String city, String address) {

        requestList = new ArrayList<>();
        UUID id = UUID.randomUUID();

        this.id = id.toString();
        this.user = user;
        this.country = country;
        this.city = city;
        this.address = address;
    }

    public Property() {
        UUID id = UUID.randomUUID();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Request> getRequestList() {
        return requestList;
    }
}
