package Duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The class deals with the interaction with the user
 */
public class Ui extends Application {

    public Ui() {}

    Duke dukey;

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Ui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Duke");
            dukey = new Duke("data/duke.txt", fxmlLoader);
            fxmlLoader.<MainWindow>getController().setDuke(dukey);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeApplication() {
        Platform.exit();
    }

    /**
     * The method is a static and takes in a parameter
     * @param tasks The input to be received
     */

}
