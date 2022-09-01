package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main class to run and initialize the Duke class.
 * @author Jason
 */
public class Main extends Application {
    private final Duke duke = new Duke();
    private MainWindow mainWindow;

    public Main() throws DukeException, IOException {
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);

            mainWindow = fxmlLoader.<MainWindow>getController();
            mainWindow.setDuke(duke);
            mainWindow.greet();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
