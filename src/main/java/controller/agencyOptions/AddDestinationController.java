package controller.agencyOptions;

import controller.FxmlLoader;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import model.User;
import service.VacationDestinationService;

public class AddDestinationController {
    public BorderPane mainPaneAddDestination;
    public Button btnGoBack;
    public TextField tfDestination;
    public Button btnAddDestination;

    VacationDestinationService destinationService = new VacationDestinationService();

    public void goBack(ActionEvent actionEvent) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("agencyOptions");
        mainPaneAddDestination.setCenter(view);
    }

    public void addDestination(ActionEvent actionEvent) {
        if(checkIfFieldIsNotEmpty()){
            FxmlLoader object = new FxmlLoader();
            destinationService.insertDestination(getDestination());
            showConfirmation();
            Pane view = object.getPage("agencyOptions");
            mainPaneAddDestination.setCenter(view);
        }
    }

    private void showConfirmation(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("New vacation destination was added successfully.");
        alert.show();
    }

    private boolean checkIfFieldIsNotEmpty(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if(!destinationService.checkIfNotEmpty(getDestination())){
            alert.setContentText("Please fill in all the fields");
            alert.show();
            return false;
        }
        return true;
    }


    private String getDestination() { return tfDestination.getText(); }

}
