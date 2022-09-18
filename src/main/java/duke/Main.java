package duke;

import java.io.IOException;

import duke.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * GUI for Duke using FXML.
 *
 * @author Elgin
 */
public class Main extends Application {
    private static final Duke DUKE = new Duke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            VBox box = fxmlLoader.load();
            Scene scene = new Scene(box);
            stage.setTitle("Duke");
            stage.setScene(scene);
            stage.getIcons().add(new Image(Main.class.getResourceAsStream("/images/robot.png")));
            fxmlLoader.<MainWindow>getController().setDuke(Main.DUKE);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
