package duke;

import java.io.IOException;

import duke.gui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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

    /**
     * Method to set the JavaFX stages.
     * @param stage the primary stage for this application, onto which the application scene can be set.
     *              Applications may create other stages, if needed, but they will not be primary stages.
     */
    @Override
    public void start(Stage stage) {
        assert(stage != null);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            Image icon = new Image("/view/Icon.png");
            stage.setScene(scene);
            stage.setTitle("AIlfred");
            stage.getIcons().add(icon);

            mainWindow = fxmlLoader.<MainWindow>getController();
            mainWindow.setDuke(duke);
            mainWindow.greet();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
