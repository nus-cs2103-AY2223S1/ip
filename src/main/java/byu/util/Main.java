package byu.util;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * A GUI for Byu using FXML.
 */
public class Main extends Application {

    private final Byu byu = new Byu();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            VBox ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("BYU");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setByu(byu);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
