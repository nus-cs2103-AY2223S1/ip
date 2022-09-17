package duke;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * A JavaFX application to run a GUI-based Duke.
 */
public class DukeGui extends Application {
    private Duke duke;

    @Override
    public void start(Stage stage) {
        duke = new Duke("tasks.txt");
        duke.setUi(new GraphicalUi(duke, stage));

        duke.run();
    }
}
