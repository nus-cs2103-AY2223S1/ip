package bocil;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ui.MainWindow;

/**
 * A GUI for Bocil using FXML.
 */
public class Main extends Application {
    private final Bocil bocil = new Bocil("./data/", "bocil.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle(bocil.getTitle());
            stage.setScene(scene);
            bocil.initialize();
            fxmlLoader.<MainWindow>getController().setBocil(bocil);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
