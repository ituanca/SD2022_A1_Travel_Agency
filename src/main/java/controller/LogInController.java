package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import service.UserService;

public class LogInController {
    public BorderPane mainPaneLogIn2;
    public AnchorPane logInPane;
    public Button btnGoBack;
    public TextField tfUsername;
    public PasswordField pfPassword;
    public Button btnLogIn;

    service.UserService userService = new UserService();

    public void checkValidity(ActionEvent actionEvent) {
        if(checkIfFieldsAreNotEmpty() && checkIfUsernameExistsAndIfPasswordIsCorrect()){
            FxmlLoader object = new FxmlLoader();
            Pane view = object.getPage("vacationPackages");
            mainPaneLogIn2.setCenter(view);
        }
    }

    private boolean checkIfFieldsAreNotEmpty(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if(!userService.checkIfNotEmptyLogIn(getUsername(), getPassword())){
            alert.setContentText("Please fill in all the fields");
            alert.show();
            return false;
        }
        return true;
    }

    private boolean checkIfUsernameExistsAndIfPasswordIsCorrect(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        String password = userService.checkIfUsernameExistsAndFindPassword(getUsername());
        if(password == null){
            alert.setContentText("This username does not exist");
            alert.show();
            return false;
        }else{
            if(!getPassword().equals(password)){
                alert.setContentText("Password is incorrect");
                alert.show();
                return false;
            }
        }
        return true;
    }

    public void goBack(ActionEvent actionEvent) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("home");
        mainPaneLogIn2.setCenter(view);
    }


    private String getUsername() { return tfUsername.getText(); }

    private String getPassword() { return pfPassword.getText(); }

}
