package blink;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Blink blink = new Blink();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getRoot().setStyle("-fx-font-family: 'Helvetica'");
            stage.setTitle("Blink");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBlink(blink);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
