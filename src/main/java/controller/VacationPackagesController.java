package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import service.BookingService;
import service.VacationDestinationService;
import service.VacationPackagesService;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VacationPackagesController implements Initializable {
    public BorderPane mainPanePackages;
    public Button btnGoBack;
    public ListView listViewPackages;
    public ComboBox cbDestination;
    public DatePicker dpStart;
    public DatePicker dpEnd;
    public TextField tfMinimumPrice;
    public TextField tfMaximumPrice;
    public Button btnFilter;
    public Label lblSelection;
    public TextField tfSelectedOption;
    public Button btnClear;
    public Button btnBook;

    VacationPackagesService packageService = new VacationPackagesService();
    BookingService bookingService = new BookingService();

    public void goBack(ActionEvent actionEvent) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("home");
        mainPanePackages.setCenter(view);
    }

    private ArrayList<String> getDestinations() throws SQLException {
        VacationDestinationService destinationService = new VacationDestinationService();
        return destinationService.getDestinations();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            cbDestination.getItems().addAll(getDestinations());
            listViewPackages.getItems().addAll(packageService.getPackages());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void selectDestination(ActionEvent actionEvent) {
    }

    public void filter(ActionEvent actionEvent) throws SQLException {
        ArrayList<String> packagesFilteredByPeriod = findPackagesByPeriodPreferences();
        ArrayList<String> packagesFilteredByPrice = findPackagesByPriceIntervalPreferences();
        //ArrayList<String> packagesFilteredByDestination = findPackagesByDestination();
        if(getMinimumPrice().equals("") && getMaximumPrice().equals("") && getStartDate() == null && getEndDate() == null){
            listViewPackages.getItems().addAll(packageService.getPackages());
        }else{
            if (packagesFilteredByPeriod != null || packagesFilteredByPrice != null) {
                listViewPackages.getItems().clear();
                makeIntersection(packagesFilteredByPrice, packagesFilteredByPeriod);
            }
        }
        listViewPackages.setVisible(true);
        selectPackageFromListView(listViewPackages);
    }

    private void makeIntersection(ArrayList<String> packages1, ArrayList<String> packages2) throws SQLException {
        boolean atLeastOneSolution = false;
        for (String packageInstance : packageService.getPackages()) {
            if (packages1.isEmpty()) {
                if(checkIfValidPeriod()) {
                    if (packages2.contains(packageInstance)) {
                        atLeastOneSolution = true;
                        listViewPackages.getItems().addAll(packageInstance);
                    }
                }
            } else if (packages2.isEmpty()) {
                if(!getMinimumPrice().isEmpty() || !getMaximumPrice().isEmpty()) {
                    if (checkIfPriceIntervalIsValid()) {
                        if (packages1.contains(packageInstance)) {
                            atLeastOneSolution = true;
                            listViewPackages.getItems().addAll(packageInstance);
                        }
                    }
                }
            } else if (packages1.contains(packageInstance) && packages2.contains(packageInstance)) {
                atLeastOneSolution = true;
                listViewPackages.getItems().addAll(packageInstance);
            }
        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if(!atLeastOneSolution){
            alert.setContentText("We have not found any package for you");
            alert.show();
            listViewPackages.getItems().clear();
        }
    }

//    private ArrayList<String> findPackagesByDestination() throws SQLException {
//        String destinationId = packageService.checkIfDestinationNameExistsAndRetrieveId(getDestinationSelection());
//        ArrayList<String> packages = new ArrayList<>();
//        if(!getDestinationSelection().isEmpty()) {
//            packages = packageService.findPackagesByDestinationId(destinationId);
//            if (packages.isEmpty()) {
//                listViewPackages.getItems().clear();
//            }
//        }
//        return packages;
//    }

    private boolean checkIfPriceIntervalIsValid(){
        return packageService.checkIfValidPriceInterval(getMinimumPrice(), getMaximumPrice());
    }

    private boolean checkIfPriceIntervalIsValidWithErrorMessage(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if(!packageService.checkIfValidPriceInterval(getMinimumPrice(), getMaximumPrice())) {
            alert.setContentText("Please select a valid price interval");
            alert.show();
            return false;
        }
        return true;
    }

    private ArrayList<String> findPackagesByPriceIntervalPreferences(){
        ArrayList<String> packages = new ArrayList<>();
        if(!getMinimumPrice().isEmpty() || !getMaximumPrice().isEmpty()){
            if(checkIfPriceIntervalIsValidWithErrorMessage()){
                packages = packageService.findPackagesInPriceInterval(getMinimumPrice(), getMaximumPrice());
            }
            listViewPackages.getItems().clear();
        }
        return packages;
    }

    private boolean checkIfValidPeriodWithErrorMessage(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if(getStartDate()!=null && getEndDate()!=null){
            if(!packageService.checkIfValidPeriod(getStartDate(), getEndDate())){
                listViewPackages.getItems().clear();
                alert.setContentText("Please select a valid time interval");
                alert.show();
                return false;
            }
        }else if(getStartDate()==null && getEndDate()!=null){
            listViewPackages.getItems().clear();
            alert.setContentText("Please select a start date");
            alert.show();
            return false;
        }else if(getStartDate()!=null && getEndDate()==null){
            listViewPackages.getItems().clear();
            alert.setContentText("Please select an end date");
            alert.show();
            return false;
        }
        return true;
    }

    private boolean checkIfValidPeriod(){
        if(getStartDate() != null && getEndDate() != null){
            return packageService.checkIfValidPeriod(getStartDate(), getEndDate());
        }else if(getStartDate() != null && getEndDate() == null){
            listViewPackages.getItems().clear();
            return false;
        }else if (getStartDate() == null && getEndDate() != null) {
            listViewPackages.getItems().clear();
            return false;
        }
        return true;
    }

    private ArrayList<String> findPackagesByPeriodPreferences(){
        ArrayList<String> packages = new ArrayList<>();
        if(checkIfValidPeriodWithErrorMessage()){
            packages =  packageService.findPackagesWithSelectedPeriod(getStartDate(), getEndDate());
        }
        listViewPackages.getItems().clear();
        return packages;
    }

    public void selectPackageFromListView(ListView listView){
        listView.setOnMouseClicked(new ListViewHandler(){
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(listView.getSelectionModel().getSelectedItem() != null) {
                    tfSelectedOption.setText(packageService.getPackageName(getSelectionAsString()));
                    lblSelection.setVisible(true);
                    tfSelectedOption.setVisible(true);
                    btnBook.setVisible(true);
                }
            }
        });
    }

    public void clear(ActionEvent actionEvent) {
        tfMinimumPrice.clear();
        tfMaximumPrice.clear();
        cbDestination.valueProperty().set(null);
        dpStart.setValue(null);
        dpEnd.setValue(null);
    }

    public void book(ActionEvent actionEvent) {
        FxmlLoader object = new FxmlLoader();
        String packageName = packageService.getPackageName(getSelectionAsString());
        //bookingService.createBooking(packageName);
        Pane view = object.getPage("registeredBooking");
        mainPanePackages.setCenter(view);
    }

    private String getSelectionAsString() {
        return (String) listViewPackages.getSelectionModel().getSelectedItem();
    }

    private String getDestinationSelection() {
        return (String) cbDestination.getValue();
    }

    private String getMinimumPrice() { return tfMinimumPrice.getText(); }

    private String getMaximumPrice() { return tfMaximumPrice.getText(); }

    private LocalDate getStartDate(){ return dpStart.getValue(); }

    private LocalDate getEndDate(){ return dpEnd.getValue(); }


}
