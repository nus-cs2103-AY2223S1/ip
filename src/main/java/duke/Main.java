package duke;

import java.io.IOException;

import duke.components.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * A GUI for Duke using FXML
 */
public class Main extends Application {
    private Duke duke = new Duke();
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fmxlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fmxlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fmxlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
