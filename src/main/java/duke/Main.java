package duke;

import duke.ui.MainWindow;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
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
