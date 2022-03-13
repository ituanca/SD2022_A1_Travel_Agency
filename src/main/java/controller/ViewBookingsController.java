package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import service.VacationDestinationService;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ViewBookingsController {
    public Button btnGoBack;
    public ListView listViewBookings;
    public BorderPane mainPaneViewBookings;

    VacationDestinationService packageService = new VacationDestinationService();

    public void goBack(ActionEvent actionEvent) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("vacationPackages");
        mainPaneViewBookings.setCenter(view);
    }

}
