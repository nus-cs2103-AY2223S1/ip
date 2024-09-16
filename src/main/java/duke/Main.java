//@@author lpohsien-reused
//Adapted from https://se-education.org/guides/tutorials/javaFxPart3.html
// with minor changes.
package duke;

import java.io.IOException;

import duke.gui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke("./data/tasks.txt");
    public static final String EXIT_SIGNAL = "bye";

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle(Duke.APPLICATION_NAME);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            fxmlLoader.<MainWindow>getController().showWelcome();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
//@@author