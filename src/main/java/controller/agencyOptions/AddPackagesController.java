package controller.agencyOptions;

import controller.FxmlLoader;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import service.VacationDestinationService;
import service.VacationPackagesService;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AddPackagesController implements Initializable {
    public BorderPane mainPaneAddPackages;
    public Button btnGoBack;
    public TextField tfName;
    public Button btnAddPackage;
    public ComboBox cbDestinations;
    public TextField tfPrice;
    public DatePicker dpStart;
    public DatePicker dpEnd;
    public TextField tfNumber;
    public Label lblSelectDestination;
    public Label lblPackageName;
    public Label lblPackagePrice;
    public Label lblStartDate;
    public Label lblEndDate;
    public Label lblExtra;
    public Label lblNumber;
    public TextArea taExtra;

    VacationPackagesService packagesService = new VacationPackagesService();

    public void goBack(ActionEvent actionEvent) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("agencyOptions");
        mainPaneAddPackages.setCenter(view);
    }

    public void addPackage(ActionEvent actionEvent) {
        if(checkIfFieldsAreNotEmpty() && checkIfValidPeriod() && checkIfValidNumber()){
            FxmlLoader object = new FxmlLoader();
            packagesService.addPackage(getSelection(), getName(), getPrice(), getStartDate(), getEndDate(),
                    getExtraDetails(), getNumberOfBookings());
            showConfirmation();
            Pane view = object.getPage("agencyOptions");
            mainPaneAddPackages.setCenter(view);
        }
    }

    private void showConfirmation(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Package was added successfully.");
        alert.show();
    }

    public void selectDestination(ActionEvent actionEvent) {
        lblPackageName.setVisible(true);
        lblPackagePrice.setVisible(true);
        lblExtra.setVisible(true);
        lblNumber.setVisible(true);
        lblStartDate.setVisible(true);
        lblEndDate.setVisible(true);
        tfName.setVisible(true);
        tfPrice.setVisible(true);
        taExtra.setVisible(true);
        dpStart.setVisible(true);
        dpEnd.setVisible(true);
        tfNumber.setVisible(true);
        btnAddPackage.setVisible(true);
    }

    private ArrayList<String> getDestinations() throws SQLException {
        VacationDestinationService destinationService = new VacationDestinationService();
        return destinationService.getDestinations();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            cbDestinations.getItems().addAll(getDestinations());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private boolean checkIfFieldsAreNotEmpty(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if(!packagesService.checkIfNotEmpty(getSelection(), getName(), getPrice(), getStartDate(), getEndDate(), getExtraDetails(), getNumberOfBookings())){
            alert.setContentText("Please fill in all the fields");
            alert.show();
            return false;
        }
        return true;
    }

    private boolean checkIfValidPeriod(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if(!packagesService.checkIfValidPeriod(getStartDate(), getEndDate())){
            alert.setContentText("Please select a valid time interval");
            alert.show();
            return false;
        }
        return true;
    }

    private boolean checkIfValidNumber(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if(!packagesService.checkIfValidNumber(getPrice())){
            alert.setContentText("Please fill in a valid price");
            alert.showAndWait();
            return false;
        }else if(!packagesService.checkIfValidNumber(getNumberOfBookings())){
            alert.setContentText("Please fill in a valid number of possible bookings");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    private String getSelection() { return (String) cbDestinations.getValue(); }

    private String getName() { return tfName.getText(); }

    private String getPrice() { return tfPrice.getText(); }

    private String getExtraDetails() { return taExtra.getText(); }

    private String getNumberOfBookings() { return tfNumber.getText(); }

    private LocalDate getStartDate(){ return dpStart.getValue(); }

    private LocalDate getEndDate(){ return dpEnd.getValue(); }
}
