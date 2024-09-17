package duke.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;

import javafx.fxml.FXMLLoader;

/**
 * A GUI for Karen using FXML.
 */
public class Main extends Application {

    private Karen karen = new Karen("src/data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Karen Task Bot");
            fxmlLoader.<MainWindow>getController().setKaren(karen);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}