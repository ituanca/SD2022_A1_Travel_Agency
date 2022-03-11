package repository;

import model.Destination;

import javax.persistence.*;

import java.sql.SQLException;
import java.util.ArrayList;

public class VacationDestinationRepository {

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");
    EntityManager em = entityManagerFactory.createEntityManager();

    public void insertDestination(String destination){
        //Destination newDestination = new Destination(Integer.toString(id), destination);
        em.getTransaction().begin();
        //em.persist(destination);
        createQueryForInsertDestination(destination);
        em.getTransaction().commit();
        em.close();
    }

    private void createQueryForInsertDestination(String destination){
        int id  = findFirstAvailableId();
        em.createNativeQuery("INSERT INTO destination (id, destination) VALUES (?,?)")
                .setParameter(1, Integer.toString(id))
                .setParameter(2, destination)
                .executeUpdate();
    }

    public boolean checkIfExistentId(String string){
        Destination destination = em.find(Destination.class, string);
        return destination != null;
    }

    public int findFirstAvailableId(){
        int id;
        for(id = 1; id < 100; id++){
            if(!checkIfExistentId(Integer.toString(id))){
                break;
            }
        }
        return id;
    }

    public void deleteDestination( String destination){
        em.getTransaction().begin();
        createQueryForDeleteDestination(destination);
        em.getTransaction().commit();
        em.close();
    }

    private void createQueryForDeleteDestination(String destination){
        em.createQuery("delete Destination where destination = :destination")
                .setParameter("destination", destination)
                .executeUpdate();
    }

    public ArrayList<String> selectDestinations() throws SQLException {
        try{
            Query query = em.createQuery("SELECT d from Destination d", Destination.class);
            ArrayList<String> list = new ArrayList<>();
            for(Object o : query.getResultList()){
                Destination destination = (Destination)o;
                list.add(destination.getDestination());
            }
            return list;
        }catch(NoResultException e) {
            return null;
        }
    }

    public ArrayList<Destination> selectDestinationsObjects() throws SQLException {
        try{
            Query query = em.createQuery("SELECT d from Destination d", Destination.class);
            ArrayList<Destination> list = new ArrayList<>();
            for(Object o : query.getResultList()){
                Destination destination = (Destination)o;
                list.add(destination);
            }
            return list;
        }catch(NoResultException e) {
            return null;
        }
    }

    public String findDestinationNameAndRetrieveId(String string) {
        try{
            Query query = em.createQuery("SELECT u from Destination u WHERE u.destination = :destination", Destination.class)
                    .setParameter("destination", string);
            Destination destination = (Destination) query.getSingleResult();
            return destination.getId();
        }catch(NoResultException e) {
            return null;
        }
    }

}
