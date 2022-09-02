package duke;

import javafx.application.Application;
import javafx.stage.Stage;

public class DukeGui extends Application {
    Duke duke;

    @Override
    public void init() {
        //
    }

    @Override
    public void start(Stage stage) {
        duke = new Duke("tasks.txt");
        duke.setUi(new GraphicalUi(duke, stage));

        duke.run();
    }
}
