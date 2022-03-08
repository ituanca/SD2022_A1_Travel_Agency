package application;

import controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.User;
import service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Objects;

public class Main extends Application {

    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getClassLoader().getResource("sample.fxml")));
        primaryStage.setTitle("Globetrotters – Trips for Travellers");
        Scene scene = new Scene(root, 800, 600);
        root.setStyle("-fx-background-image: url('https://cdn.wallpapersafari.com/9/32/YF5sBZ.jpg'); " +
                "-fx-background-repeat: no-repeat; " +
                "-fx-background-size: 800 600; " +
                "-fx-background-position: center center;");

        primaryStage.setScene(scene);
        primaryStage.show();

        EntityManager em = entityManagerFactory.createEntityManager();
        UserService service = new UserService();
        User user = new User("1", "Itu", "Anca", "ituanca", "itu_anca@yahoo.com", "password123");
        em.getTransaction().begin();
        service.insertUser(user, em);
        em.getTransaction().commit();
        em.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
