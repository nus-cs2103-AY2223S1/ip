package sally.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Main class to launch Sally's GUI using FXML
 *
 * @author liviamil
 */
public class Main extends Application {
    private final Sally sally = new Sally();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setSally(sally);
            stage.setTitle("Sally");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
