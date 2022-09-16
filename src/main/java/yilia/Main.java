package yilia;

import java.io.IOException;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import yilia.controller.MainWindow;

/**
 * A GUI for Yilia using FXML.
 */
public class Main extends Application {

    private final Yilia yilia = new Yilia();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setYilia(yilia);
            stage.setTitle("Yilia");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Exits the programme.
     */
    public static void exit() {
        try {
            Thread.sleep(1000);
            Platform.exit();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
