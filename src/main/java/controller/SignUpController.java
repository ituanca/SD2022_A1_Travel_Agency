package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import service.UserService;

public class SignUpController{

    public Button btnGoBack;
    public AnchorPane signUpPane;
    public BorderPane mainPaneSignUp;
    public TextField tfFirstName;
    public TextField tfLastName;
    public TextField tfUsername;
    public TextField tfEmail;
    public PasswordField pfPassword;
    public Button btnSignUp;

    service.UserService userService = new UserService();

    public void checkValidity(ActionEvent actionEvent) {
        if(checkIfFieldsAreNotEmpty() && checkIfValidName() && checkIfValidEmail() && checkIfValidUsername() && checkIfValidPassword()){
            FxmlLoader object = new FxmlLoader();
            showConfirmation();
            userService.insertUser(getFirstName(), getLastName(), getEmail(), getUsername(), getPassword());
            Pane view = object.getPage("vacationPackages");
            mainPaneSignUp.setCenter(view);
        }
    }

    private void showConfirmation(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Account was created successfully.");
        alert.show();
    }

    private boolean checkIfFieldsAreNotEmpty(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if(!userService.checkIfNotEmptySignUp(getFirstName(), getLastName(), getEmail(), getUsername(), getPassword())){
            alert.setContentText("Please fill in all the fields");
            alert.show();
            return false;
        }
        return true;
    }

    private boolean checkIfValidName(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if(!userService.checkIfValidName(getFirstName()) || !userService.checkIfValidName(getLastName())){
            alert.setContentText("Please fill in a valid first name or last name");
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.showAndWait();
            return false;
        }
        return true;
    }

    private boolean checkIfValidEmail(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if(!userService.checkIfValidEmail(getEmail())){
            alert.setContentText("Please fill in a valid email address");
            alert.show();
            return false;
        }
        return true;
    }

    private boolean checkIfValidUsername(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if(!userService.checkIfValidUsername(getUsername())){
            alert.setContentText("Your username should have at least 8 characters "
                    + "and it can contain lowercase letters, "
                    + "uppercase letters and numeric values");
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.showAndWait();
            return false;
        }
        return true;
    }

    private boolean checkIfValidPassword(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if(userService.checkIfValidPassword(getPassword()).equals("too short")){
            alert.setContentText("Your password must have at least 8 characters!");
            alert.show();
            return false;
        }else if(userService.checkIfValidPassword(getPassword()).equals("invalid")){
            alert.setContentText("Your password must have at least one uppercase, one lowercase, one special characters and one numeric value");
            alert.getDialogPane().setMinHeight(Region.USE_PREF_SIZE);
            alert.showAndWait();
            return false;
        }
        return true;
    }

    public void goBack(ActionEvent actionEvent) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("home");
        mainPaneSignUp.setCenter(view);
    }

    private String getFirstName() { return tfFirstName.getText(); }

    private String getLastName() { return tfLastName.getText(); }

    private String getUsername() { return tfUsername.getText(); }

    private String getEmail() { return tfEmail.getText();}

    private String getPassword() { return pfPassword.getText(); }

}
