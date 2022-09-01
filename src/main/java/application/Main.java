package application;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mew.Mew;


/**
 * A GUI for Mew using FXML.
 */
public class Main extends Application {
    private final String home = System.getProperty("user.home");
    private final Path path = Paths.get(home, "data", "mew");
    private final Mew mew = new Mew(path);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            Scene scene = new Scene(anchorPane);
            stage.setScene(scene);
            stage.setTitle("Mew");
            fxmlLoader.<MainWindow>getController().setMew(mew);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}