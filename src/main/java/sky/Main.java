package sky;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sky.ui.controllers.MainWindow;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Sky sky = new Sky();

    /**
     * Starts the JavaFX GUI.
     *
     * @param stage The stage to operate on.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setSky(sky);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}