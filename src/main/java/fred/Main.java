package fred;

import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private URI filePath = Path.of("data", "fred.txt").toUri();
    private Fred fred = new Fred(filePath);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Fred");
            fxmlLoader.<MainWindow>getController().setFred(fred);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
