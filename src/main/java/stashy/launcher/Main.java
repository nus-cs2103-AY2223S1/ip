package stashy.launcher;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import stashy.Stashy;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Stashy stashy = new Stashy();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(stashy);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
