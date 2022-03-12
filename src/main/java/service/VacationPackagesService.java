package service;

import com.mysql.cj.util.StringUtils;
import model.Package;
import repository.VacationDestinationRepository;
import repository.VacationPackagesRepository;
import service.validators.*;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class VacationPackagesService {

    VacationPackagesRepository packagesRepository = new VacationPackagesRepository();

    public void addPackage(String destination, String name, String price, LocalDate startDate, LocalDate endDate,
                           String extraDetails, String numberOfBookings){
        String destinationId = checkIfDestinationNameExistsAndRetrieveId(destination);
        packagesRepository.addPackage(destinationId, name, price, startDate, endDate, extraDetails, numberOfBookings);
    }

    public boolean checkIfNotEmpty(String destinationOrStatus, String name, String price, LocalDate startDate, LocalDate endDate,
                                   String extraDetails, String numberOfBookings){
        EmptyFieldValidator emptyFieldValidator = new EmptyFieldValidator();
        return emptyFieldValidator.validate(destinationOrStatus) == CasesOfInvalidations.GOOD &&
                emptyFieldValidator.validate(name) == CasesOfInvalidations.GOOD &&
                emptyFieldValidator.validate(price) == CasesOfInvalidations.GOOD &&
                emptyFieldValidator.validate(startDate.toString()) == CasesOfInvalidations.GOOD &&
                emptyFieldValidator.validate(endDate.toString()) == CasesOfInvalidations.GOOD &&
                emptyFieldValidator.validate(extraDetails) == CasesOfInvalidations.GOOD &&
                emptyFieldValidator.validate(numberOfBookings) == CasesOfInvalidations.GOOD;
    }

    public boolean checkIfValidPeriod(LocalDate startDate, LocalDate endDate){
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

    public ArrayList<String> getPackagesNames() throws SQLException {
        return packagesRepository.getPackagesNames();
    }

    public ArrayList<String> getPackages() throws SQLException {
        return packagesRepository.getPackages();
    }

    public void deletePackage( String packageString){
        String packageName = getPackageName(packageString);
        packagesRepository.deletePackage(packageName);
    }

    public String getPackageName(String packageString){
        packageString = packageString.substring(packageString.indexOf("\"") + 1);
        packageString = packageString.substring(0, packageString.indexOf("\""));
        return packageString;
    }

    public void editPackage(String packageId, String destination, String name, String price, LocalDate startDate, LocalDate endDate,
                            String extraDetails, String numberOfBookings, String status){
        String destinationId = checkIfDestinationNameExistsAndRetrieveId(destination);
        packagesRepository.editPackage(packageId, destinationId, name, price, startDate, endDate, extraDetails, numberOfBookings, status);
    }

    public ArrayList<String> findPackagesWithSelectedPeriod(LocalDate startDate, LocalDate endDate){
        return packagesRepository.findPackagesWithSelectedPeriod(startDate, endDate);
    }

    public boolean checkIfValidPriceInterval(String minimum, String maximum){
        if((minimum.equals("") && !maximum.equals("")) ||
                (maximum.equals("") && !minimum.equals(""))){
            return true;
        }else {
            return Integer.parseInt(minimum) < Integer.parseInt(maximum);
        }
    }

    public ArrayList<String> findPackagesInPriceInterval(String minimum, String maximum){
        return packagesRepository.findPackagesInPriceInterval(minimum, maximum);
    }

    public String findPackageName(String name) {
        return packagesRepository.getPackageName(name);
    }

    public String findPackageId(String name) {
        return packagesRepository.getPackageId(name);
    }

    public String findPackagePrice(String name) {
        return packagesRepository.getPackagePrice(name);
    }

    public String findPackageDestination(String name) {
        return packagesRepository.getPackageDestination(name);
    }

    public LocalDate findPackageStartDate(String name) {
        return packagesRepository.getPackageStartDate(name);
    }

    public LocalDate findPackageEndDate(String name) {
        return packagesRepository.getPackageEndDate(name);
    }

    public String findPackageExtraDetails(String name){
        return packagesRepository.getPackageExtraDetails(name);
    }

    public String findPackageNumberOfBookings(String name){
        return packagesRepository.getPackageNumberOfBookings(name).toString();
    }

    public String findPackageStatus(String name){
        return packagesRepository.getPackageStatus(name);
    }

}
