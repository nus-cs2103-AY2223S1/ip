package duke.core;

import java.io.IOException;

import duke.gui.MainWindow;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

// @@author Piyotato-reused
// Reused from https://se-education.org/guides/tutorials/javaFxPart4.html
// with minor modifications.
/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke("duke.txt", this);

    /**
     * Starts Main with a given stage.
     *
     * @param stage the primary stage for this application, onto which the application scene
     *              can be set. Applications may create other stages, if needed,
     *              but they will not be primary stages.
     * */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            fxmlLoader.<MainWindow>getController().showWelcome();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        duke.initialize();
    }

    /**
     * Exits the application.
     */
    public void exit() {
        Platform.exit();
    }
}
// @@author
