package scottie;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import scottie.ui.MainWindow;

/**
 * A GUI for the Scottie application using FXML.
 */
public class MainApp extends Application {

    private final Scottie scottie = new Scottie();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainApp.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            MainWindow mainWindow = fxmlLoader.getController();
            mainWindow.setScottie(this.scottie);
            stage.show();
            mainWindow.showStartupMessage();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
