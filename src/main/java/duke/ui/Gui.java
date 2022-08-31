package duke.ui;

import java.io.IOException;
import java.nio.file.Paths;

import duke.Duke;
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

    private Duke duke = new Duke(Paths.get(System.getProperty("user.dir"), "data", "data.txt"));

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Gui.class.getResource("/view/MainWindow.fxml"));
            VBox ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
//@@author

