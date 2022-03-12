package controller.agencyOptions;

import controller.FxmlLoader;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import service.VacationPackagesService;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ViewPackagesController implements Initializable {
    public BorderPane mainPaneViewPackages;
    public Button btnGoBack;
    public ListView listViewPackages;

    VacationPackagesService packageService = new VacationPackagesService();

    public void goBack(ActionEvent actionEvent) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("agencyOptions");
        mainPaneViewPackages.setCenter(view);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            listViewPackages.getItems().addAll(packageService.getPackages());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
