package service;

import model.Destination;
import repository.UserRepository;
import repository.VacationDestinationRepository;
import repository.VacationPackagesRepository;
import service.validators.*;

import java.time.LocalDate;
import java.util.Date;

public class VacationPackagesService {

    VacationPackagesRepository packagesRepository = new VacationPackagesRepository();

    public void addPackage(String destination, String name, String price, LocalDate startDate, LocalDate endDate,
                           String extraDetails, String numberOfBookings){
        String destinationId = checkIfDestinationNameExistsAndRetrieveId(destination);
        packagesRepository.addPackage(destinationId, name, price, startDate, endDate, extraDetails, numberOfBookings);
    }

    public boolean checkIfNotEmpty(String destination, String name, String price, LocalDate startDate, LocalDate endDate,
                                   String extraDetails, String numberOfBookings){
        EmptyFieldValidator emptyFieldValidator = new EmptyFieldValidator();
        return emptyFieldValidator.validate(destination) == CasesOfInvalidations.GOOD &&
                emptyFieldValidator.validate(name) == CasesOfInvalidations.GOOD &&
                emptyFieldValidator.validate(price) == CasesOfInvalidations.GOOD &&
                emptyFieldValidator.validate(startDate.toString()) == CasesOfInvalidations.GOOD &&
                emptyFieldValidator.validate(endDate.toString()) == CasesOfInvalidations.GOOD &&
                emptyFieldValidator.validate(extraDetails) == CasesOfInvalidations.GOOD &&
                emptyFieldValidator.validate(numberOfBookings) == CasesOfInvalidations.GOOD;
    }

    public boolean checkIfValidPeriod(LocalDate startDate, LocalDate endDate){
        System.out.println("Period selected:");
        System.out.println(startDate.toString());
        System.out.println(endDate.toString());
        return startDate.compareTo(endDate) < 0;
    }

    public boolean checkIfValidNumber(String number){
        NumberValidator numberValidator = new NumberValidator();
        return numberValidator.validate(number) == CasesOfInvalidations.GOOD;
    }

    public String checkIfDestinationNameExistsAndRetrieveId(String string){
        VacationDestinationRepository destinationRepository = new VacationDestinationRepository();
        return destinationRepository.findDestinationNameAndRetrieveId(string);
    }

}
