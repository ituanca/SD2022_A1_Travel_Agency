package service;

import repository.TravelAgencyRepository;
import service.validators.CasesOfInvalidations;
import service.validators.EmptyFieldValidator;

public class TravellingAgencyService {
    TravelAgencyRepository travelAgencyRepository = new TravelAgencyRepository();

    public void insertAgencyPassword(String id, String password){
        travelAgencyRepository.insertAgencyPassword(id, password);
    }

    public boolean checkIfNotEmpty( String password){
        EmptyFieldValidator emptyFieldValidator = new EmptyFieldValidator();
        return emptyFieldValidator.validate(password) == CasesOfInvalidations.GOOD;
    }

    public String checkIfCorrectPassword(String password){
        return travelAgencyRepository.verifyPassword(password);
    }
}
