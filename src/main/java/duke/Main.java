package duke;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * A GUI for Duke.
 */
public class Main extends Application {

    private Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        MainWindow mainWindow = MainWindow.getMainWindow();
        Scene scene = new Scene(mainWindow);
        stage.setScene(scene);
        stage.setTitle("Duke");
        stage.show();
    }
}