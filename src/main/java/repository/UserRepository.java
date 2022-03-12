package repository;

import model.Package;
import model.User;

import javax.persistence.*;
import java.util.ArrayList;


public class UserRepository {

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");
    EntityManager em = entityManagerFactory.createEntityManager();

    public void insertUser(String firstName, String lastName, String email, String username, String password){
        em.getTransaction().begin();
        createQueryForInsertUser(firstName, lastName, email, username, password);
        em.getTransaction().commit();
        em.close();
    }

    private void createQueryForInsertUser(String firstName, String lastName, String email, String username, String password){
        int id  = findFirstAvailableId();
        em.createNativeQuery("INSERT INTO user (id, firstName, lastName, username, email, password) VALUES (?,?,?,?,?,?)")
                .setParameter(1, Integer.toString(id))
                .setParameter(2, firstName)
                .setParameter(3, lastName)
                .setParameter(4, username)
                .setParameter(5, email)
                .setParameter(6, password)
                .executeUpdate();
    }

    public boolean checkIfExistent(String string) {
        User user = em.find(User.class, string);
        return user != null;
    }

    public String findUsernameAndRetrievePassword(String string) {
        try{
            Query query = em.createQuery("SELECT u from User u WHERE u.username = :username", User.class)
                .setParameter("username", string);
            User user = (User) query.getSingleResult();
            return user.getPassword();
        }catch(NoResultException e) {
            return null;
        }
    }

    public String findEmail(String string) {
        try{
            Query query = em.createQuery("SELECT u from User u WHERE u.email = :email", User.class)
                    .setParameter("email", string);
            User user = (User) query.getSingleResult();
            return user.getEmail();
        }catch(NoResultException e) {
            return null;
        }
    }

    public boolean checkIfExistentId(String string){
        User user = em.find(User.class, string);
        return user != null;
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
