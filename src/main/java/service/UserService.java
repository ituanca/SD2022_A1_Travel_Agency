package service;

import model.User;
import repository.UserRepository;

import javax.persistence.EntityManager;

public class UserService {

    UserRepository userRepository = new UserRepository();

    public void insertUser(User newUser, EntityManager em){
        if(newUser.getId().chars().allMatch(Character::isDigit) &&
                newUser.getFirstName().chars().allMatch(Character::isLetter) &&
                newUser.getLastName().chars().allMatch(Character::isLetter) &&
                (!checkIfAlreadyExists(newUser.getId(), em)) &&
                (!checkIfAlreadyExists(newUser.getUsername(), em)) &&
                (!checkIfAlreadyExists(newUser.getEmail(), em)) &&
                checkIfMinimumLength(newUser.getPassword()) && checkIfNotNull(newUser)) {
            userRepository.insertUser(newUser, em);
        }
    }

    boolean checkIfAlreadyExists(String string, EntityManager em){
        return userRepository.checkIfExistent(string, em);
    }

    boolean checkIfNotNull(User newUser){
        return newUser.getId() != null &&
                newUser.getUsername() != null &&
                newUser.getFirstName() != null &&
                newUser.getLastName() != null &&
                newUser.getEmail() != null &&
                newUser.getPassword() != null;
    }

    boolean checkIfMinimumLength(String string){
        if(string.length() < 7){
            System.out.println("Password should have at least 8 characters.");
            return false;
        }
        return true;
    }

    public void findByUsername(String username , EntityManager em){
        if (userRepository.checkIfExistent(username, em)){
            System.out.println("Username " + username + " found.");
        }else{
            System.out.println("Username " + username + " not found.");
        }
    }
}
