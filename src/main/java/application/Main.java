package application;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Main extends Application {

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
    }


    public static void main(String[] args) {
        launch(args);
    }
}
