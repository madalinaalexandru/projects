package repository;

import dto.PropertyDto;
import entity.Property;
import entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;

public class PropertyRepo {

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");

    private UserRepo userRepo;

    public PropertyRepo() {

        userRepo = new UserRepo();

    }


    public Property findPropertyByID(String id) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Property p = (Property) em.createQuery("SELECT property FROM Property property WHERE property.id LIKE :id").setParameter("id", id).getResultList().get(0);

        em.getTransaction().commit();
        em.close();

        return p;
    }

    public User addProperty(PropertyDto propertyDto) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Property p = new Property(propertyDto.getUser(), propertyDto.getCountry(), propertyDto.getCity(), propertyDto.getAddress());

        em.persist(p);

        User u = userRepo.findUserById(p.getUser().getId());

        em.getTransaction().commit();
        em.close();

        return u;
    }

    public User deleteProperty(User user, Property property) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        String address = new String(property.getAddress());
        String id = property.getId();

        em.createQuery("DELETE FROM Request r WHERE r.property.id LIKE :id").setParameter("id", id).executeUpdate();
        em.createQuery("DELETE FROM Property p WHERE p.address LIKE :address").setParameter("address", address).executeUpdate();

        User u = userRepo.findUserById(property.getUser().getId());

        em.getTransaction().commit();
        em.close();

        return u;
    }

    public Property findPropertyByAddress(User user, String address) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        String userId = user.getId();

        Property p = (Property) em.createQuery("SELECT property FROM Property property WHERE property.address LIKE " +
                ":address AND property.user.id LIKE :userId").setParameter("address", address).setParameter("userId", userId).getResultList().get(0);

        em.getTransaction().commit();
        em.close();

        return p;
    }

    public User deleteRequest(String id, User user) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        em.createQuery("UPDATE Request r SET r.status = 'Cancelled' WHERE r.id LIKE :id").setParameter("id", id).executeUpdate();

        User u = userRepo.findUserById(user.getId());

        em.getTransaction().commit();
        em.close();

        return u;
    }

    public ArrayList<Property> collectDataFromTable() {

        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();

        ArrayList<Property> propertiesList = (ArrayList<Property>) em.createQuery("SELECT property FROM Property property").getResultList();

        em.getTransaction().commit();
        em.close();

        return propertiesList;
    }

    public void deleteProperty(String id) {

        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();

        em.createQuery("DELETE FROM Request r WHERE r.property.id LIKE :id").setParameter("id", id).executeUpdate();
        em.createQuery("DELETE FROM Property p WHERE p.id LIKE :id").setParameter("id", id).executeUpdate();

        em.getTransaction().commit();
        em.close();
    }
}
