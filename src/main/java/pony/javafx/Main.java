package pony.javafx;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pony.Pony;

/**
 * A GUI for Pony using FXML.
 */
public class Main extends Application {
    /** The file path of the file where tasks will be saved to. */
    private static final String TASK_FILE_PATH = "./data/pony.txt";
    private Pony pony = new Pony(TASK_FILE_PATH);

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setPony(pony);
            stage.setTitle("My Little Pony");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}