package repository;

import dto.RegisterDto;
import dto.UserDto;
import entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;

public class UserRepo {

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");

    public UserRepo() {

    }

    public void registerUser(RegisterDto registerDto) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        User user = new User(registerDto.getFirstName(), registerDto.getLastName(), registerDto.getEmail(),
                registerDto.getPassword(), registerDto.getAddress(), registerDto.getPhoneNumber(),
                registerDto.isMarriageStatus(), registerDto.isLegalStatus());

        em.persist(user);

        em.getTransaction().commit();
        em.close();
    }

    public User findUserByEmail(String email) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        User u = (User) em.createQuery("SELECT user FROM User user WHERE user.email LIKE :email").setParameter("email", email).getResultList().get(0);

        em.getTransaction().commit();
        em.close();

        return u;
    }

    public User findUserById(String id) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        User u = (User) em.createQuery("SELECT user FROM User user WHERE user.id LIKE :id").setParameter("id", id).getResultList().get(0);

        em.getTransaction().commit();
        em.close();

        return u;
    }

    public User updateAccount(UserDto userDto) {

        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();
        User u = em.find(User.class, userDto.getId());

        u.setAddress(userDto.getAddress());
        u.setFirstName(userDto.getFirstName());
        u.setLastName(userDto.getLastName());
        u.setEmail(userDto.getEmail());
        u.setPassword(userDto.getPassword());
        u.setPhoneNumber(userDto.getPhoneNumber());
        u.setMarriageStatus(userDto.isMarriageStatus());
        u.setLegalStatus(userDto.isLegalStatus());
        em.getTransaction().commit();
        em.close();

        return u;
    }

    public ArrayList<User> collectDataFromTable() {

        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();

        ArrayList<User> usersList = (ArrayList<User>) em.createQuery("SELECT user FROM User user").getResultList();

        em.getTransaction().commit();
        em.close();

        return usersList;
    }

    public void deleteUser(String id) {

        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();

        em.createQuery("DELETE FROM Request r WHERE r.user.id LIKE :id").setParameter("id", id).executeUpdate();
        em.createQuery("DELETE FROM Property p WHERE p.user.id LIKE :id").setParameter("id", id).executeUpdate();
        em.createQuery("DELETE FROM User u WHERE u.id LIKE :id").setParameter("id", id).executeUpdate();

        em.getTransaction().commit();
        em.close();

    }

    public void addUser(User user) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        em.persist(user);

        em.getTransaction().commit();
        em.close();
    }

}
