package controller;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class AgencyOptionsController {
    public BorderPane mainPaneAgencyOptions;
    public Button btnGoBack;
    public Button btnAddDestination;
    public Button btnAddVacationPackage;
    public Button btnEditPackage;
    public Button btnDeleteVacationPackage;
    public Button btnViewPackages;
    public Button btnDeleteDestination;

    public void addDestination(ActionEvent actionEvent) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("agencyOptions/addDestination");
        mainPaneAgencyOptions.setCenter(view);
    }

    public void addVacationPackage(ActionEvent actionEvent) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("agencyOptions/addPackages");
        mainPaneAgencyOptions.setCenter(view);
    }

    public void editPackage(ActionEvent actionEvent) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("agencyOptions/editPackage");
        mainPaneAgencyOptions.setCenter(view);
    }

    public void deleteVacationPackage(ActionEvent actionEvent) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("agencyOptions/deletePackage");
        mainPaneAgencyOptions.setCenter(view);
    }

    public void viewPackages(ActionEvent actionEvent) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("agencyOptions/viewPackages");
        mainPaneAgencyOptions.setCenter(view);
    }

    public void deleteDestination(ActionEvent actionEvent) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("agencyOptions/deleteDestination");
        mainPaneAgencyOptions.setCenter(view);
    }

    public void goBack(ActionEvent actionEvent) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("agencyLogIn");
        mainPaneAgencyOptions.setCenter(view);
    }
}
