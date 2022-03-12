package controller.agencyOptions;

import controller.FxmlLoader;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import service.VacationPackagesService;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DeletePackageController implements Initializable {
    public BorderPane mainPaneDeletePackage;
    public Button btnGoBack;
    public ComboBox cbDeletePackage;
    public Button btnDeletePackage;

    VacationPackagesService packageService = new VacationPackagesService();

    public void goBack(ActionEvent actionEvent) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("agencyOptions");
        mainPaneDeletePackage.setCenter(view);
    }

    public void selectPackageToDelete(ActionEvent actionEvent) {
        btnDeletePackage.setVisible(true);
    }

    public void deletePackage(ActionEvent actionEvent) {
        FxmlLoader object = new FxmlLoader();
        packageService.deletePackage(getSelection());
        showConfirmation();
        Pane view = object.getPage("agencyOptions");
        mainPaneDeletePackage.setCenter(view);
    }

    private void showConfirmation(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setContentText("Vacation package was successfully deleted.");
        alert.show();
    }

    private ArrayList<String> getPackages() throws SQLException {
        return packageService.getPackages();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            cbDeletePackage.getItems().addAll(getPackages());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private String getSelection() {
        return (String) cbDeletePackage.getValue();
    }
}
