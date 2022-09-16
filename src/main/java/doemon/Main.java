package doemon;

import java.io.IOException;

import doemon.controller.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Doemon using FXML.
 */
public class Main extends Application {
    /** The file path of the file where tasks will be saved to. */
    private static final String TASK_FILE_PATH = "./data/duke.txt";
    private Doemon doemon = new Doemon(TASK_FILE_PATH);

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().add(getClass().getResource("/view/styles.css").toExternalForm());
            stage.setTitle("Doemon!");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDoemon(doemon);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
