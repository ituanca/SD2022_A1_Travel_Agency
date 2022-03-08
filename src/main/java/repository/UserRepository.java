package repository;

import model.User;

import javax.persistence.EntityManager;

public class UserRepository {

    public void insertUser(User newUser, EntityManager em){
        em.createNativeQuery("INSERT INTO user (id, firstName, lastName, username, email, password) VALUES (?,?,?,?,?,?)")
                .setParameter(1, newUser.getId())
                .setParameter(2, newUser.getFirstName())
                .setParameter(3, newUser.getLastName())
                .setParameter(4, newUser.getUsername())
                .setParameter(5, newUser.getEmail())
                .setParameter(6, newUser.getPassword())
                .executeUpdate();
    }

    public boolean checkIfExistent(String string , EntityManager em) {
        User user = em.find(User.class, string);
        return user != null;
    }
}
