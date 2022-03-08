package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class SignUpController{

    public Button btnGoBack;
    public AnchorPane signUpPane;
    public BorderPane mainPaneSignUp;

    public void goBack(ActionEvent actionEvent) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("home");
        mainPaneSignUp.setCenter(view);
    }
}
