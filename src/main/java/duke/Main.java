package duke;

import java.io.IOException;

import duke.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Represents the GUI for Duke using FXML.
 */
public class Main extends Application {
    private static final String APPLICATION_TITLE = "Little Duke";
    private final Duke duke = new Duke();

    /**
     * Starts the GUI for the Stage that takes in a stage.
     *
     * @param stage the primary stage for this application, onto which
     *     the application scene can be set.
     *     Applications may create other stages, if needed, but they will not be
     *     primary stages.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            VBox vBox = fxmlLoader.load();
            Scene scene = new Scene(vBox);
            scene.getStylesheets().add("/styles/stylesheet.css");
            stage.setScene(scene);
            stage.setTitle(APPLICATION_TITLE);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
