package app;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ui.MainWindow;

/**
 * A GUI for app.Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();

    /**
     * Starts the GUI using FXML.
     * @param stage JavaFX stage object
     * @throws IOException exception related to input or output
     */
    @Override
    public void start(Stage stage) throws IOException {
        try {
            Duke.loadDuke();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}