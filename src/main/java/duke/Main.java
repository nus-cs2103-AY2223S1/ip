package duke;

import java.io.IOException;
import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main class for setting Duke Scene.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {
            Duke duke = new Duke("src/main/java/data/tasks.txt");
            URL mainWindowUrl = Main.class.getResource("/view/MainWindow.fxml");
            FXMLLoader fxmlLoader = new FXMLLoader(mainWindowUrl);
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
