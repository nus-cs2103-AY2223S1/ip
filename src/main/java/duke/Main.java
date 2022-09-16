package duke;

import java.io.IOException;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A class to that setups the GUI for Duke.
 */
public class Main extends Application {
    private final String USER_HOME = System.getProperty("user.home");
    private final String SAVE_FOLDER = "dukeData";
    private final String SAVE_FOLDER_LOCATION = Paths.get(USER_HOME, SAVE_FOLDER).toString();
    private final String SAVE_NAME = "duke.txt";

    private Duke duke = new Duke(SAVE_FOLDER_LOCATION, SAVE_NAME);

    @Override
    public void start(Stage stage) {
        try {
            String appName = "Duke";
            Image appIcon = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));

            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.setTitle(appName);
            stage.getIcons().add(appIcon);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
