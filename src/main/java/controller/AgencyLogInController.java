package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import service.TravellingAgencyService;
import service.UserService;

import java.io.IOException;

public class AgencyLogInController {
    public PasswordField pfPassword;
    public Button btnLogIn;
    public BorderPane mainPaneAgencyLogIn;

    service.TravellingAgencyService travellingAgencyService = new TravellingAgencyService();

    public void checkValidity(ActionEvent actionEvent) {
        if(checkIfFieldIsNotEmpty() && checkIfCorrectPassword()){
            FxmlLoader object = new FxmlLoader();
            Pane view = object.getPage("home");
            mainPaneAgencyLogIn.setCenter(view);
        }
    }

    private boolean checkIfFieldIsNotEmpty(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if(!travellingAgencyService.checkIfNotEmpty( getPassword())){
            alert.setContentText("Please fill in the password field");
            alert.show();
            return false;
        }
        return true;
    }

    private boolean checkIfCorrectPassword(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if(travellingAgencyService.checkIfCorrectPassword(getPassword())==null){
            alert.setContentText("Password is incorrect");
            alert.show();
            return false;
        }
        return true;
    }

    public void goBack(ActionEvent actionEvent) throws IOException {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("home");
        mainPaneAgencyLogIn.setCenter(view);
    }

    private String getPassword() { return pfPassword.getText(); }


}
