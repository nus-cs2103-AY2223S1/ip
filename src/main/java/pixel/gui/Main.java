package pixel.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pixel.Pixel;

/**
 * Main class for Pixel's GUI using FXML.
 */
public class Main extends Application {

    private final Pixel pixel = new Pixel("./data/pixel.txt");

    /**
     * Launches the GUI for Pixel
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setPixel(this.pixel);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
