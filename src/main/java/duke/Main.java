package duke;

import duke.gui.DukeWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main application class.
 */
public class Main extends Application {
    private final Duke duke = new Duke();

    /**
     * The start function is called by the JavaFX runtime when the application is
     * launched. It creates a new stage and sets its scene to a new instance of
     * MainWindow, which is defined in this class. The stage is then made visible.
     *
     * @param stage
     *            Set the stage to the scene
     */
    @Override
    public void start(Stage stage) {
        AnchorPane ap = new DukeWindow(duke);
        Scene scene = new Scene(ap);
        stage.setScene(scene);
        stage.show();
    }
}
