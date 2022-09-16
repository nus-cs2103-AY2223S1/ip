package blob;

import java.io.IOException;

import blob.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * The Main class wraps around the Blob application and provides user with a GUI to interact
 * with the application.
 */
public class Main extends Application {
    private final Blob blob = new Blob("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBlob(blob);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
