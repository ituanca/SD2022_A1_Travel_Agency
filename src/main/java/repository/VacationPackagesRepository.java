package repository;

import model.Destination;
import model.Package;
import model.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

public class VacationPackagesRepository {

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");
    EntityManager em = entityManagerFactory.createEntityManager();

    public void addPackage(String destinationID, String name, String price, LocalDate startDate, LocalDate endDate,
                           String extraDetails, String numberOfBookings){
        em.getTransaction().begin();
        createQueryForAddingPackage(destinationID, name, price, startDate, endDate, extraDetails, numberOfBookings);
        em.getTransaction().commit();
        em.close(); // ar trebui sa inchid conexiunea doar la final de tot pentru ca altfel apar erori
    }

    private void createQueryForAddingPackage(String destinationID, String name, String price,
                                             LocalDate startDate, LocalDate endDate,
                                             String extraDetails, String numberOfBookings){
        int id  = findFirstAvailableId();
        em.createNativeQuery("INSERT INTO package (id, destinationID, name, price, startDate, endDate, extraDetails, numberOfBookings, status) VALUES (?,?,?,?,?,?,?,?,?)")
                .setParameter(1, Integer.toString(id))
                .setParameter(2, destinationID)
                .setParameter(3, name)
                .setParameter(4, price)
                .setParameter(5, startDate.toString())
                .setParameter(6, endDate.toString())
                .setParameter(7, extraDetails)
                .setParameter(8, numberOfBookings)
                .setParameter(9, "NOT_BOOKED")
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
