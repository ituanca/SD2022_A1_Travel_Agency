package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import org.hibernate.mapping.Array;
import service.VacationPackagesService;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VacationPackagesController implements Initializable {
    public BorderPane mainPanePackages;
    public Button btnGoBack;
    public ListView listViewPackages;
    public ComboBox cbDestination;
    public DatePicker dpStart;
    public DatePicker dpEnd;
    public TextField tfMinimumPrice;
    public TextField tfMaximumPrice;
    public Button btnFilter;
    public Label lblSelection;
    public TextField tfSelectedOption;
    public Button btnClear;

    VacationPackagesService packageService = new VacationPackagesService();

    public void goBack(ActionEvent actionEvent) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("home");
        mainPanePackages.setCenter(view);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            listViewPackages.getItems().addAll(packageService.getPackages());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public void selectDestination(ActionEvent actionEvent) {
    }

    public void filter(ActionEvent actionEvent) throws SQLException {
        ArrayList<String> packagesFilteredByPeriod = findPackagesByPeriodPreferences();
        ArrayList<String> packagesFilteredByPrice = findPackagesByPriceIntervalPreferences();
        if(packagesFilteredByPeriod!=null || packagesFilteredByPrice!=null) {
            listViewPackages.getItems().clear();
            makeIntersection(packagesFilteredByPrice, packagesFilteredByPeriod);
        }
        listViewPackages.setVisible(true);
        selectPackage(listViewPackages);
    }

    private void makeIntersection(ArrayList<String> packages1, ArrayList<String> packages2) throws SQLException {
        boolean atLeastOneSolution = false;
        for (String packageInstance : packageService.getPackages()) {
            if (packages1.isEmpty()){
                if( packages2.contains(packageInstance) ){
                    atLeastOneSolution = true;
                    listViewPackages.getItems().addAll(packageInstance);
                }
            } else if (packages2.isEmpty()){
                if (packages1.contains(packageInstance)){
                    atLeastOneSolution = true;
                    listViewPackages.getItems().addAll(packageInstance);
                }
            }else {
                if (packages1.contains(packageInstance) && packages2.contains(packageInstance)) {
                    atLeastOneSolution = true;
                    listViewPackages.getItems().addAll(packageInstance);
                }
            }
        }
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if(!atLeastOneSolution){
            alert.setContentText("We have not found any package for you");
            alert.show();
        }
    }

    private boolean checkIfPriceIntervalIsValid(){
       return packageService.checkIfValidPriceInterval(getMinimumPrice(), getMaximumPrice());
    }

    private ArrayList<String> findExistingPackagesInSelectedPriceInterval(){
        return packageService.findPackagesInPriceInterval(getMinimumPrice(), getMaximumPrice());
    }

    private ArrayList<String> findPackagesByPriceIntervalPreferences(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        ArrayList<String> packages = new ArrayList<>();
        if(!getMinimumPrice().isEmpty() || !getMaximumPrice().isEmpty()){
            if(checkIfPriceIntervalIsValid()){
                packages = findExistingPackagesInSelectedPriceInterval();
                if(packages.isEmpty()){
                    alert.setContentText("There are no packages at a price situated in the specified interval");
                    alert.show();
                    listViewPackages.getItems().clear();
                }
            }
        }
        return packages;
    }


    private ArrayList<String> findExistingPackagesWithSelectedPeriod(){
        return packageService.findPackagesWithSelectedPeriod(getStartDate(), getEndDate());
    }

    private boolean checkIfValidPeriod(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if(!packageService.checkIfValidPeriod(getStartDate(), getEndDate())){
            alert.setContentText("Please select a valid time interval");
            alert.show();
            return false;
        }
        return true;
    }

    private ArrayList<String> findPackagesByPeriodPreferences(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        ArrayList<String> packages = new ArrayList<>();
        if(getStartDate()!=null && getEndDate()!=null && checkIfValidPeriod()){
            packages = findExistingPackagesWithSelectedPeriod();
            System.out.println(packages);
            if(packages.isEmpty()){
                alert.setContentText("There are no packages for the selected period");
                alert.show();
                listViewPackages.getItems().clear();
            }
        }
        System.out.println(packages);
        return packages;
    }

    public void selectPackage(ListView listView){
        listView.setOnMouseClicked(new ListViewHandler(){
            @Override
            public void handle(MouseEvent mouseEvent) {
                if(listView.getSelectionModel().getSelectedItem() != null) {
                    tfSelectedOption.setText(packageService.getPackageName(getSelectionAsString()));
                    lblSelection.setVisible(true);
                    tfSelectedOption.setVisible(true);
                }
            }
        });
    }

    public void clear(ActionEvent actionEvent) {
        tfMinimumPrice.setText("");
        tfMaximumPrice.setText("");
//        cbDestination.setValue("");
    }

    private String getSelectionAsString() {
        return (String) listViewPackages.getSelectionModel().getSelectedItem();
    }

    private String getDestinationSelection() {
        return (String) cbDestination.getValue();
    }

    private String getMinimumPrice() { return tfMinimumPrice.getText(); }

    private String getMaximumPrice() { return tfMaximumPrice.getText(); }

    private LocalDate getStartDate(){ return dpStart.getValue(); }

    private LocalDate getEndDate(){ return dpEnd.getValue(); }


}
