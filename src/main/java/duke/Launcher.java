package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A launcher class to workaround classpath issues.
 */
public class Launcher extends Application {

    private final Duke duke = new Duke();

    /**
     * Displays the JavaFX stage.
     *
     * @param stage Stage to render to screen.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.setTitle("SotongBoi - Nice to meet you!");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
