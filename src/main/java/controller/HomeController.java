package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class HomeController {
    public Button btnLogIn;
    public Button btnSignUp;
    public BorderPane mainPaneHome;

    public void signUp(ActionEvent actionEvent) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("signUp");
        mainPaneHome.setCenter(view);
    }

    public void logIn(ActionEvent actionEvent) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("logIn");
        mainPaneHome.setCenter(view);
    }


}
