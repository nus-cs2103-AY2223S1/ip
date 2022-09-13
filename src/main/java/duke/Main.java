package duke;

import java.io.IOException;

import duke.display.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private static final String FXML_FILE_LOCATION = "/view/MainWindow.fxml";

    private Duke duke;

    @Override
    public void start(Stage stage) {
        try {
            duke = new Duke();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(FXML_FILE_LOCATION));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
            stage.setResizable(false);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
