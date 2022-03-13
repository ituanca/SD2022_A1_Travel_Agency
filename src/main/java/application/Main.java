package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Destination;
import service.VacationDestinationService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Objects;

public class Main extends Application {

//    private static final EntityManagerFactory entityManagerFactory =
//            Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");

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

        //EntityManager em = entityManagerFactory.createEntityManager();
        //em.getTransaction().begin();

        //service.TravellingAgencyService travellingAgencyService = new TravellingAgencyService();
        //travellingAgencyService.insertAgencyPassword("1","agency123");


        //service.VacationDestinationService destinationService = new VacationDestinationService();
        //destinationService.insertDestination("Maldive");

//        Destination newDestination = new Destination("1", "Maldive");
//        em.getTransaction().begin();
//        em.persist(newDestination);
//        em.createNativeQuery("INSERT INTO destination (id, destination) VALUES (?,?)")
//                .setParameter(1, newDestination.getId())
//                .setParameter(2, newDestination.getDestination())
//                .executeUpdate();
//        em.getTransaction().commit();
//        em.close();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
