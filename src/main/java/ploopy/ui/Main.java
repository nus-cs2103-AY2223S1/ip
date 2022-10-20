package ploopy.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ploopy.Ploopy;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private final Ploopy ploopy = new Ploopy();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Ploopy");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setPloopy(ploopy);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
