package duke.ui;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

//@@author RezwanArefin01-reused
//Adapted from https://se-education.org/guides/tutorials/javaFxPart2.html
//with minor modifications
/**
 * A simple graphical user interface for Duke using FXML.
 */
public class Gui extends Application {

    /**
     * Starts the GUI.
     *
     * @param stage The primary stage for this application.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/MainWindow.fxml"));
            VBox mainWindow = fxmlLoader.load();
            Scene scene = new Scene(mainWindow);
            stage.setScene(scene);
            Path path = Paths.get(System.getProperty("user.dir"), "data", "data.txt");
            fxmlLoader.<MainWindow>getController().initDuke(path);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
//@@author

