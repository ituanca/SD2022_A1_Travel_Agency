package repository;

import model.TravelAgency;
import model.User;
import org.hibernate.Criteria;

import javax.persistence.*;

public class TravelAgencyRepository {
    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");
    EntityManager em = entityManagerFactory.createEntityManager();

    public void insertAgencyPassword(String id, String password){
        em.getTransaction().begin();
        createQueryForInsertPassword(id, password);
        em.getTransaction().commit();
        em.close();
    }

    private void createQueryForInsertPassword(String id, String password){
        em.createNativeQuery("INSERT INTO travelAgency (id, password) VALUES (?,?)")
                .setParameter(1, id)
                .setParameter(2, password)
                .executeUpdate();
    }

    public String verifyPassword(String string) {
        try{
            Query query = em.createQuery("SELECT u from TravelAgency u WHERE u.password = :password", TravelAgency.class)
                    .setParameter("password", string);
            TravelAgency travelAgency = (TravelAgency) query.getSingleResult();
            return travelAgency.getPassword();
        }catch(NoResultException e) {
            return null;
        }

    }

}
