package repository;

import model.Package;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;

public class BookingRepository {

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");
    EntityManager em = entityManagerFactory.createEntityManager();

    public void createBooking(String packageId, String userId){
        em.getTransaction().begin();
        createQueryForCreatingBooking(packageId, userId);
        em.getTransaction().commit();
        em.close();
    }

    private void createQueryForCreatingBooking(String packageId, String userId){
        int id  = findFirstAvailableId();
        em.createNativeQuery("INSERT INTO booking (id, packageID, nameId) VALUES (?,?,?)")
                .setParameter(1, Integer.toString(id))
                .setParameter(2, packageId)
                .setParameter(3, userId)
                .executeUpdate();
    }

    public boolean checkIfExistentId(String string){
        Package package_ = em.find(Package.class, string);
        return package_ != null;
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
