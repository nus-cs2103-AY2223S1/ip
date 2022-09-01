package jarvis;

import java.io.IOException;

import jarvis.controller.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main --- runs Jarvis.
 */
public class Main extends Application {
    private final Jarvis jarvis = new Jarvis();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setJarvis(jarvis);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
