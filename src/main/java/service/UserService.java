package service;

import repository.UserRepository;
import service.validators.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UserService {

    UserRepository userRepository = new UserRepository();

    public void insertUser(String firstName, String lastName, String email, String username, String password){
        userRepository.insertUser(firstName, lastName, email, username, password);
    }

    public boolean checkIfNotEmptySignUp(String firstName, String lastName, String email, String username, String password){
        EmptyFieldValidator emptyFieldValidator = new EmptyFieldValidator();
        return emptyFieldValidator.validate(firstName) == CasesOfInvalidations.GOOD &&
                emptyFieldValidator.validate(lastName) == CasesOfInvalidations.GOOD &&
                emptyFieldValidator.validate(email) == CasesOfInvalidations.GOOD &&
                emptyFieldValidator.validate(username) == CasesOfInvalidations.GOOD &&
                emptyFieldValidator.validate(password) == CasesOfInvalidations.GOOD;
    }

    public boolean checkIfNotEmptyLogIn(String username, String password){
        EmptyFieldValidator emptyFieldValidator = new EmptyFieldValidator();
        return emptyFieldValidator.validate(username) == CasesOfInvalidations.GOOD &&
                emptyFieldValidator.validate(password) == CasesOfInvalidations.GOOD;
    }

    public String checkIfUsernameExistsAndFindPassword(String username){
        return userRepository.findUsernameAndRetrievePassword(username);
    }

    public boolean checkIfValidName(String name){
        NameValidator nameValidator = new NameValidator();
        return nameValidator.validate(name) == CasesOfInvalidations.GOOD;
    }

    public boolean checkIfValidEmail(String email){
        EmailValidator emailValidator = new EmailValidator();
        return emailValidator.validate(email) == CasesOfInvalidations.GOOD;
    }

    public boolean checkIfValidUsername(String username){
        UsernameValidator emailValidator = new UsernameValidator();
        return emailValidator.validate(username) == CasesOfInvalidations.GOOD;
    }

    public String checkIfValidPassword(String password){
        PasswordValidator passwordValidator = new PasswordValidator();
        if(passwordValidator.validate(password) == CasesOfInvalidations.TOO_SHORT){
            return "too short";
        }else if(passwordValidator.validate(password) == CasesOfInvalidations.INVALID_FORM_PASSWORD){
            return "invalid";
        }return "ok";
    }

    boolean checkIfAlreadyExists(String string){
        return userRepository.checkIfExistent(string);
    }

    public boolean findByUsername(String username){
        return userRepository.checkIfExistent(username);
    }
}
