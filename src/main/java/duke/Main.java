package duke;

import java.io.IOException;

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
    private Duke duke = new Duke("savedata/", "duke.txt");

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
