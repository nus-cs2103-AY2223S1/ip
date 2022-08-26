package duke.ui;

import duke.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * A GUI for Duke using FXML.
 * Source: https://se-education.org/guides/tutorials/javaFxPart4.html
 */
public class Main extends Application {
    private static final Path PATH = Paths.get(
            System.getProperty("user.home"),
            "duke",
            "tasks.txt"
    );
    public static final String MAIN_WINDOW_FXML = "/view/MainWindow.fxml";
    private static final Duke DUKE = new Duke(PATH);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource(MAIN_WINDOW_FXML));
            Scene scene = new Scene(loader.load());
            loader.<MainWindow>getController().setDuke(DUKE);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}