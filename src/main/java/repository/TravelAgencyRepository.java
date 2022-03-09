package repository;

import model.TravelAgency;
import model.User;

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

    public String verifyPassword(String password) {
        try{
            Query query = em.createQuery("SELECT t from travelagency t WHERE t.password = :password", TravelAgency.class)
                    .setParameter("password", password);
            TravelAgency travelAgency = (TravelAgency) query.getSingleResult();
            return travelAgency.getPassword();
        }catch(NoResultException e) {
            return null;
        }

//        try{
//            TravelAgency travelAgency = em.find(TravelAgency.class, string);
//            System.out.println(travelAgency);
//            //System.out.println(travelAgency.getPassword());
//            return true;
//        }catch(NoResultException e){
//            return false;
//        }

    }

}
