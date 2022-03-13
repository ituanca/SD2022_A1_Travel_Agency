package repository;

import model.Destination;
import model.Package;

import javax.persistence.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
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

    public ArrayList<String> getPackagesNames() throws SQLException {
        try{
            Query query = em.createQuery("SELECT d from Package d", Package.class);
            ArrayList<String> list = new ArrayList<>();
            for(Object o : query.getResultList()){
                Package packageInstance = (Package) o;
                list.add(packageInstance.getName());
            }
            return list;
        }catch(NoResultException e) {
            return null;
        }
    }

    public ArrayList<String> getPackages() throws SQLException {
        try{
            Query query = em.createQuery("SELECT d from Package d", Package.class);
            ArrayList<String> listString = new ArrayList<>();
            for(Object o : query.getResultList()){
                Package packageInstance = (Package) o;
                String packageString = packageInstance.toString();
                listString.add(packageString);
            }
            return listString;
        }catch(NoResultException e) {
            return null;
        }
    }

    public void deletePackage(String packageName){
        em.getTransaction().begin();
        createQueryForDeletePackage(packageName);
        em.getTransaction().commit();
        em.close();
    }

    private void createQueryForDeletePackage(String name){
        em.createQuery("delete Package where name = :name")
                .setParameter("name", name)
                .executeUpdate();
    }

    public void editPackage(String packageId, String destinationID, String name, String price,
                            LocalDate startDate, LocalDate endDate,
                            String extraDetails, String numberOfBookings, String status){
        em.getTransaction().begin();
        createQueryForEditingPackage(packageId, destinationID, name, price, startDate, endDate, extraDetails, numberOfBookings, status);
        em.getTransaction().commit();
        em.close();
    }

    private void createQueryForEditingPackage(String packageId, String destinationID, String name, String price,
                                              LocalDate startDate, LocalDate endDate,
                                              String extraDetails, String numberOfBookings, String status){
        em.createNativeQuery("UPDATE Package p set p.name=:name, " +
                "p.destinationID=:destinationID," +
                "p.name=:name," +
                "p.price=:price," +
                "p.startDate=:startDate," +
                "p.endDate=:endDate," +
                "p.extraDetails=:extraDetails," +
                "p.numberOfBookings=:numberOfBookings," +
                "p.status=:status " +
                "WHERE p.id=:id")
                .setParameter("destinationID", destinationID)
                .setParameter("name", name)
                .setParameter("price", price)
                .setParameter("startDate", startDate)
                .setParameter("endDate", endDate)
                .setParameter("extraDetails", extraDetails)
                .setParameter("numberOfBookings", numberOfBookings)
                .setParameter("status", status)
                .setParameter("id", packageId)
                .executeUpdate();
    }

    public Package findPackage(String name) {
        try{
            Query query = em.createQuery("SELECT u from Package u WHERE u.name = :name", Package.class)
                    .setParameter("name", name);
            return (Package) query.getSingleResult();
        }catch(NoResultException e) {
            return null;
        }
    }

    public ArrayList<String> findPackagesWithSelectedPeriod(LocalDate startDate, LocalDate endDate){
        try{
            Query query = em.createQuery("SELECT u from Package u WHERE u.startDate=:startDate AND " +
                    "u.endDate=:endDate", Package.class)
                    .setParameter("startDate", startDate)
                    .setParameter("endDate", endDate);
            ArrayList<String> listString = new ArrayList<>();
            for(Object o : query.getResultList()){
                Package packageInstance = (Package) o;
                String packageString = packageInstance.toString();
                listString.add(packageString);
            }
            return listString;
        }catch(NoResultException e) {
            return null;
        }
    }

    public ArrayList<String> findPackagesInPriceInterval(String minimum, String maximum){
        try{
            System.out.println("minimum: "+ minimum + "   maximum: " + maximum);
            Query query;
            if(minimum.equals("")){
                query = em.createQuery("SELECT u from Package u WHERE u.price<:maximum", Package.class)
                        .setParameter("maximum", maximum);
            }else if(maximum.equals("")){
                query = em.createQuery("SELECT u from Package u WHERE u.price>:minimum", Package.class)
                        .setParameter("minimum", minimum);
            }else{
                query = em.createQuery("SELECT u from Package u WHERE u.price>:minimum AND " +
                        "u.price<:maximum", Package.class)
                        .setParameter("minimum", minimum)
                        .setParameter("maximum", maximum);
            }
            ArrayList<String> listString = new ArrayList<>();
            for(Object o : query.getResultList()){
                Package packageInstance = (Package) o;
                String packageString = packageInstance.toString();
                listString.add(packageString);
            }
            return listString;
        }catch(NoResultException e) {
            return null;
        }
    }

//    public ArrayList<String> findPackagesByDestinationId(String destinationId){
//        try{
//            Query query = em.createQuery("SELECT u from Package u WHERE u.destinationId=:destinationId", Package.class)
//                    .setParameter("destinationId", destinationId);
//            ArrayList<String> listString = new ArrayList<>();
//            for(Object o : query.getResultList()){
//                Package packageInstance = (Package) o;
//                String packageString = packageInstance.toString();
//                listString.add(packageString);
//            }
//            return listString;
//        }catch(NoResultException e) {
//            return null;
//        }
//    }

    public String getPackageId(String packageName){
        Package packageInstance = findPackage(packageName);
        return packageInstance.getId();
    }

    public String getPackagePrice(String packageName){
        Package packageInstance = findPackage(packageName);
        return packageInstance.getPrice();
    }

    public String getPackageDestination(String packageName){
        Package packageInstance = findPackage(packageName);
        if(packageInstance.getDestination()!=null){
            return packageInstance.getDestination().getDestination();
        }else{
            return "";
        }
    }

    public String getPackageName(String packageName){
        Package packageInstance = findPackage(packageName);
        return packageInstance.getName();
    }

    public LocalDate getPackageStartDate(String packageName){
        Package packageInstance = findPackage(packageName);
        return packageInstance.getStartDate();
    }

    public LocalDate getPackageEndDate(String packageName){
        Package packageInstance = findPackage(packageName);
        return packageInstance.getEndDate();
    }

    public String getPackageExtraDetails(String packageName){
        Package packageInstance = findPackage(packageName);
        return packageInstance.getExtraDetails();
    }

    public Integer getPackageNumberOfBookings(String packageName){
        Package packageInstance = findPackage(packageName);
        return packageInstance.getNumberOfBookings();
    }

    public String getPackageStatus(String packageName){
        Package packageInstance = findPackage(packageName);
        return packageInstance.getStatus();
    }

}