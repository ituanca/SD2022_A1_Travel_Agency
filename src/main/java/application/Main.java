package application;

import controller.FxmlLoader;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.TravelAgency;
import service.TravellingAgencyService;
import service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Objects;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("sample.fxml")));
        root.setStyle("-fx-background-image: url('https://cdn.wallpapersafari.com/9/32/YF5sBZ.jpg'); " +
                "-fx-background-repeat: no-repeat; " +
                "-fx-background-size: 800 600; " +
                "-fx-background-position: center center;");
        primaryStage.setTitle("Globetrotters – Trips for Travellers");
        Scene scene = new Scene(root, 800, 600);

        primaryStage.setScene(scene);
        primaryStage.show();

        //service.TravellingAgencyService travellingAgencyService = new TravellingAgencyService();
        //travellingAgencyService.insertAgencyPassword("1","agency123");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
