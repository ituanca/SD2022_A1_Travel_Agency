package controller.agencyOptions;

import controller.FxmlLoader;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import service.VacationDestinationService;
import service.VacationPackagesService;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class EditPackageController implements Initializable {
    public BorderPane mainPaneEditPackage;
    public Button btnGoBack;
    public ComboBox cbEditPackage;
    public TextField tfName;
    public Button btnSave;
    public Label lblPackageName;
    public Label lblPackagePrice;
    public TextField tfPrice;
    public Label lblStartDate;
    public DatePicker dpStart;
    public Label lblEndDate;
    public DatePicker dpEnd;
    public Label lblExtra;
    public Label lblNumber;
    public TextField tfNumber;
    public Label lblStatus;
    public TextField tfStatus;
    public TextArea taExtra;
    public Label lblDestination;
    public Label lblEuro;
    public ComboBox cbDestination;

    VacationPackagesService packageService = new VacationPackagesService();

    public void goBack(ActionEvent actionEvent) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("agencyOptions");
        mainPaneEditPackage.setCenter(view);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            cbEditPackage.getItems().addAll(getPackagesNames());
            cbDestination.getItems().addAll(getDestinations());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private ArrayList<String> getDestinations() throws SQLException {
        VacationDestinationService destinationService = new VacationDestinationService();
        return destinationService.getDestinations();
    }

    private ArrayList<String> getPackagesNames() throws SQLException {
        return packageService.getPackagesNames();
    }

    public void selectPackageToEdit(ActionEvent actionEvent) {
        setLabelAndTextField(lblPackageName, tfName, getPackageName());
        setLabelAndTextField(lblPackagePrice, tfPrice, getPackagePrice());
        lblEuro.setVisible(true);
        lblDestination.setVisible(true);
        cbDestination.setVisible(true);
        cbDestination.setValue(getPackageDestination());
        lblExtra.setVisible(true);
        taExtra.setVisible(true);
        taExtra.setText(getPackageExtraDetails());
        taExtra.setEditable(true);
        setLabelAndTextField(lblNumber, tfNumber, getPackageNumberOfBookings());
        setLabelAndTextField(lblStatus, tfStatus, getPackageStatus());
        lblStartDate.setVisible(true);
        dpStart.setVisible(true);
        dpStart.setValue(getPackageStartDate());
        dpStart.setEditable(true);
        lblEndDate.setVisible(true);
        dpEnd.setVisible(true);
        dpEnd.setValue(getPackageEndDate());
        dpEnd.setEditable(true);
        btnSave.setVisible(true);
    }

    public void selectDestination(ActionEvent actionEvent) {
    }

    private void setLabelAndTextField(Label lbl, TextField tf, String string){
        lbl.setVisible(true);
        tf.setVisible(true);
        tf.setText(string);
        tf.setEditable(true);
    }

    public void saveChanges(ActionEvent actionEvent) {
        if(checkIfFieldsAreNotEmpty() && checkIfValidPeriod() && checkIfValidNumber()) {
            FxmlLoader object = new FxmlLoader();
            packageService.editPackage(getPackageId(), getDestinationSelection(), getName(), getPrice(),
                    getStartDate(), getEndDate(),getExtraDetails(), getNumberOfBookings(), getStatus());
            showConfirmation();
            Pane view = object.getPage("agencyOptions");
            mainPaneEditPackage.setCenter(view);
        }
    }

    private void showConfirmation(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Vacation package was successfully updated.");
        alert.show();
    }

    private boolean checkIfFieldsAreNotEmpty(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if(!packageService.checkIfNotEmpty(getStatus(), getName(), getPrice(), getStartDate(), getEndDate(), getExtraDetails(), getNumberOfBookings())){
            alert.setContentText("Please fill in all the fields");
            alert.show();
            return false;
        }
        return true;
    }

    private boolean checkIfValidPeriod(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if(!packageService.checkIfValidPeriod(getStartDate(), getEndDate())){
            alert.setContentText("Please select a valid time interval");
            alert.show();
            return false;
        }
        return true;
    }

    private boolean checkIfValidNumber(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if(!packageService.checkIfValidNumber(getPrice())){
            alert.setContentText("Please fill in a valid price");
            alert.showAndWait();
            return false;
        }else if(!packageService.checkIfValidNumber(getNumberOfBookings())){
            alert.setContentText("Please fill in a valid number of possible bookings");
            alert.showAndWait();
            return false;
        }
        return true;
    }

    private String getPackageName(){
       return packageService.findPackageName(getSelection());
    }

    private String getPackageId(){
        return packageService.findPackageId(getSelection());
    }

    private String getPackagePrice(){
        return packageService.findPackagePrice(getSelection());
    }

    private String getPackageDestination(){
        return packageService.findPackageDestination(getSelection());
    }

    private LocalDate getPackageStartDate(){
        return packageService.findPackageStartDate(getSelection());
    }

    private LocalDate getPackageEndDate(){
        return packageService.findPackageEndDate(getSelection());
    }

    private String getPackageExtraDetails(){
        Text text = new Text();
        text.wrappingWidthProperty().set(345);
        return packageService.findPackageExtraDetails(getSelection());
    }

    private String getPackageNumberOfBookings(){
        return packageService.findPackageNumberOfBookings(getSelection());
    }

    private String getPackageStatus(){
        return packageService.findPackageStatus(getSelection());
    }

    private String getSelection() {
        return (String) cbEditPackage.getValue();
    }

    private String getName() { return tfName.getText(); }

    private String getPrice() { return tfPrice.getText(); }

    private String getDestinationSelection() {
        return (String) cbDestination.getValue();
    }

    private String getExtraDetails() { return taExtra.getText(); }

    private String getNumberOfBookings() { return tfNumber.getText(); }

    private LocalDate getStartDate(){ return dpStart.getValue(); }

    private LocalDate getEndDate(){ return dpEnd.getValue(); }

    private String getStatus() { return tfStatus.getText(); }


}
