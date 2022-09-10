package duke;

import duke.ui.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Entry point for Duke.
 * Initializes Root Node and sets the Scene.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        MainWindow mainWindow = MainWindow.getMainWindow();
        Scene scene = new Scene(mainWindow);
        stage.setScene(scene);
        stage.show();
    }
}
