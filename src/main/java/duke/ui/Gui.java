package duke.ui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * A simple graphical user interface for Duke.
 */
public class Gui extends Application {

    @Override
    public void start(Stage stage) {
        Label label = new Label("Hello World!");
        Scene scene = new Scene(label);

        stage.setTitle("Duke");
        stage.setScene(scene);

        stage.show();
    }
}
