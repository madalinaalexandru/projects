package repository;

import entity.RequestType;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.ArrayList;

public class RequestTypeRepo {

    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");

    public RequestTypeRepo() {

    }

    public void addRequestType(String name) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        RequestType requestType = new RequestType(name);

        em.persist(requestType);

        em.getTransaction().commit();
        em.close();
    }

    public void deleteRequestType(String name) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        em.createQuery("DELETE FROM Request r WHERE r.type LIKE :name").setParameter("name", name).executeUpdate();
        em.createQuery("DELETE FROM RequestType rt WHERE rt.name LIKE :name").setParameter("name", name).executeUpdate();

        em.getTransaction().commit();
        em.close();
    }

    public ArrayList<String> getAllRequestTypes() {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        ArrayList<String> requestTypes = (ArrayList<String>) em.createQuery("SELECT requestType.name from RequestType requestType").getResultList();

        em.getTransaction().commit();
        em.close();

        return requestTypes;
    }
}
