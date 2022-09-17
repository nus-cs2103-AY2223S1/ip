package bloop.gui;

import java.io.IOException;

import bloop.Bloop;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Bloop bloop = new Bloop();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Bloop");
            fxmlLoader.<MainWindow>getController().setBloop(bloop);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
