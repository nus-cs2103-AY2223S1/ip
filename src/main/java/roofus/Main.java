package roofus;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Roofus using FXML.
 */
public class Main extends Application {

    private Roofus roofus = new Roofus();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Roofus");
            Image icon = new Image("/images/roofus.png");
            stage.getIcons().add(icon);
            fxmlLoader.<MainWindow>getController().setRoofus(roofus);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
