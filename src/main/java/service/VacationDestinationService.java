package service;

import repository.VacationDestinationRepository;
import service.validators.CasesOfInvalidations;
import service.validators.EmptyFieldValidator;

public class VacationDestinationService {
    VacationDestinationRepository destinationRepository = new VacationDestinationRepository();

    public boolean checkIfNotEmpty(String destination){
        EmptyFieldValidator emptyFieldValidator = new EmptyFieldValidator();
        return emptyFieldValidator.validate(destination) == CasesOfInvalidations.GOOD;
    }

    public void insertDestination( String destination){
        destinationRepository.insertDestination(destination);
    }
}
