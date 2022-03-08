package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Objects;

public class LogInController {
    public Button btnGoBack;
    public AnchorPane logInPane;
    public BorderPane mainPaneLogIn;

    public void goBack(ActionEvent actionEvent) throws IOException {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("home");
        mainPaneLogIn.setCenter(view);
    }
}
