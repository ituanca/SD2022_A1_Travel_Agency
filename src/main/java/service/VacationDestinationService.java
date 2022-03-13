package service;

import model.Destination;
import repository.VacationDestinationRepository;
import service.validators.CasesOfInvalidations;
import service.validators.EmptyFieldValidator;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class VacationDestinationService {
    VacationDestinationRepository destinationRepository = new VacationDestinationRepository();

    public boolean checkIfNotEmpty(String destination){
        EmptyFieldValidator emptyFieldValidator = new EmptyFieldValidator();
        return emptyFieldValidator.validate(destination) == CasesOfInvalidations.GOOD;
    }

    public void insertDestination( String destination){
        destinationRepository.insertDestination(destination);
    }


    public ArrayList<String> getDestinations() throws SQLException {
        return  destinationRepository.selectDestinations();
    }

    public ArrayList<Destination> getDestinationsObjects() throws SQLException {
        return  destinationRepository.selectDestinationsObjects();
    }

    public void deleteDestination( String destination){
        destinationRepository.deleteDestination(destination);
    }

}