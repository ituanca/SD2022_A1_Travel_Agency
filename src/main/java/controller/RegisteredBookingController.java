package controller;

import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

import javax.xml.crypto.Data;
import java.net.URL;
import java.util.ResourceBundle;

public class RegisteredBookingController {
    public Button btnViewBookings;
    public BorderPane mainPaneRegisteredBooking;

    public void viewBookings(ActionEvent actionEvent) {
        FxmlLoader object = new FxmlLoader();
        Pane view = object.getPage("viewBookings");
        mainPaneRegisteredBooking.setCenter(view);
    }

}
