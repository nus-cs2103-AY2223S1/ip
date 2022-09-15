package froggy;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Froggy using FXML.
 */
public class Main extends Application {

    private Froggy froggy = new Froggy();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Froggy");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setFroggy(froggy);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}