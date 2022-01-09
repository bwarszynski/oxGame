package lab.oxgame.oxgame;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader loader = new FXMLLoader();
            //DS - poprawiłem nazwy/zagnieżdżenie pakietów, bo wcześniej nie można było załadować pliku Main.fxml
            loader.setLocation(getClass().getResource("Main.fxml"));
            Parent root = loader.load();

            Scene scene = new Scene(root, 400, 400);
            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

            MainController controller = loader.getController();
            primaryStage.setOnCloseRequest(event -> {
                controller.shutdown();
                Platform.exit();
            });
            primaryStage.setTitle("Kółko i krzyżyk - Bartosz Warszyński");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}