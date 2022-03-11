package controller.agencyOptions;

import controller.FxmlLoader;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import service.VacationDestinationService;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DeleteDestinationController implements Initializable {
    public BorderPane mainPaneDeleteDestination;
    public Button btnGoBack;
    public Button btnDeleteDestination;
    public ComboBox cbDeleteDestination;

    VacationDestinationService destinationService = new VacationDestinationService();

    public void goBack(ActionEvent actionEvent) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("agencyOptions");
        mainPaneDeleteDestination.setCenter(view);
    }

    public void deleteDestination(ActionEvent actionEvent) {
        FxmlLoader object = new FxmlLoader();
        destinationService.deleteDestination(getSelection());
        showConfirmation();
        Pane view = object.getPage("agencyOptions");
        mainPaneDeleteDestination.setCenter(view);
    }

    private void showConfirmation(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Destination was successfully deleted.");
        alert.show();
    }

    public void selectDestinationToDelete(ActionEvent actionEvent) {
        btnDeleteDestination.setVisible(true);
    }

    private ArrayList<String> getDestinations() throws SQLException {
        VacationDestinationService destinationService = new VacationDestinationService();
        return destinationService.getDestinations();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            cbDeleteDestination.getItems().addAll(getDestinations());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private String getSelection() {
        return (String) cbDeleteDestination.getValue();
    }

}
