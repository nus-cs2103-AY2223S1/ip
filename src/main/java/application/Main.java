package application;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import tobtob.TobTob;

/**
 * A GUI for TobTob using FXML.
 */
public class Main extends Application {
    private TobTob tobTob = new TobTob();

    /**
     * Starts the application required components.
     *
     * @param stage {@link Stage}
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setTobTob(tobTob);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
