package doke;

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
    //anything related to GUI is taken from/referenced from the website:
    //https://se-education.org/guides/tutorials/javaFx.html
    private Doke doke = new Doke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setResizable(false);
            fxmlLoader.<MainWindow>getController().setDoke(doke);
            stage.setTitle("doke");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
