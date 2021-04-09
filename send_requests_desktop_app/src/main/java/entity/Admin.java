package entity;

import javax.persistence.Entity;
import java.util.UUID;

@Entity
//@Table(name = "user")
public class Admin extends User {

    public Admin() {

        this.setId(UUID.randomUUID().toString());

    }

    public Admin(String email, String password) {

        this.setId(UUID.randomUUID().toString());
        this.setEmail(email);
        this.setPassword(password);
    }
}
