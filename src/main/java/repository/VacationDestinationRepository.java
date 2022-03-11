package repository;

import model.Destination;
import model.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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

}
