package repository;

import dto.PropertyDto;
import dto.RequestDto;
import entity.Property;
import entity.Request;
import entity.User;
import messages.ErrorMessages;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RequestRepo {


    private final EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");

    private UserRepo userRepo;

    public RequestRepo() {

        userRepo = new UserRepo();

    }

    public User addRequest(RequestDto requestDto) {

        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();

        Request r = new Request(requestDto.getUser(), requestDto.getType(), requestDto.getProperty());

        String propertyId = r.getProperty().getId();

        int year = r.getDate().getYear();

        ArrayList<Request> queryResult = (ArrayList<Request>) em.createQuery("SELECT request FROM Request request " +
                "WHERE request.property.id LIKE :propertyId AND request.status <> 'Cancelled'").setParameter("propertyId", propertyId).getResultList();

        int nbOfEntries = 0;

        for (Request request : queryResult) {

            if (request.getDate().getYear() == year) {
                nbOfEntries++;
            }
        }

        if (nbOfEntries < 3) {
            em.persist(r);

            User u = userRepo.findUserById(r.getUser().getId());

            em.getTransaction().commit();
            em.close();

            return u;
        } else {
            JOptionPane.showMessageDialog(null, ErrorMessages.TOO_MANY_REQUESTS, ErrorMessages.COULD_NOT_ADD_REQUEST,
                    JOptionPane.ERROR_MESSAGE);
        }

        return null;
    }

    public ArrayList<Request> collectDataFromTable() {

        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();

        ArrayList<Request> requestsList = (ArrayList<Request>) em.createQuery("SELECT request FROM Request request").getResultList();

        em.getTransaction().commit();
        em.close();

        return requestsList;
    }

    public void deleteRequest(String id) {

        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();

        em.createQuery("DELETE FROM Request r WHERE r.id LIKE :id").setParameter("id", id).executeUpdate();

        em.getTransaction().commit();
        em.close();

    }

    public void updateRequest(String id, String status) {

        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();

        em.createQuery("UPDATE Request r SET r.status = :status WHERE r.id LIKE :id AND r.status <> 'Cancelled'").setParameter("id", id).setParameter("status", status).executeUpdate();

        em.getTransaction().commit();
        em.close();
    }

    public ArrayList<Request> findDataWithKeyword(String keyword) {

        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();

        String query = new String("SELECT request FROM Request request WHERE " +
                "request.id LIKE '%" + keyword + "%' " +
                "OR request.status LIKE '%" + keyword + "%' " +
                "OR request.date LIKE '%" + keyword + "%' " +
                "OR request.type LIKE '%" + keyword + "%'  " +
                "OR request.user.email LIKE '%" + keyword + "%' " +
                "OR request.property.id LIKE '%" + keyword + "%' ");


        ArrayList<Request> requestsList = (ArrayList<Request>) em.createQuery(query).getResultList();

        System.out.println(requestsList.size());

        em.getTransaction().commit();
        em.close();

        return requestsList;
    }
}
