package duke.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 *
 * @author Isaac Li Haoyang
 * @version v0.2
 */
public class Main extends Application {

    private final Duke duke = new Duke("data/tasks.txt");

    /**
     * Runs the main logic of the window displayed by GUI.
     *
     * @param stage the stage provided during execution
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            fxmlLoader.<MainWindow>getController().greeting();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
