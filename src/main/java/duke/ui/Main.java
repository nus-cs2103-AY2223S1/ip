package duke.ui;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;

import duke.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 * Source: https://se-education.org/guides/tutorials/javaFxPart4.html
 */
public class Main extends Application {
    private static final String FONTSTYLE_PATH = "/stylesheets/fontstyle.css";
    private static final String MAIN_WINDOW_FXML_PATH = "/view/MainWindow.fxml";
    private static final Path STORAGE_PATH = Paths.get(
            System.getProperty("user.home"),
            "duke",
            "tasks.txt"
    );
    private static final Image KNIGHT_IMAGE = new Image(Objects.requireNonNull(MainWindow.class.getResourceAsStream(
            "/images/icon.png")));
    private static final Duke DUKE = new Duke(STORAGE_PATH);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource(MAIN_WINDOW_FXML_PATH));
            Scene scene = new Scene(loader.load());
            String fontstyle = Objects.requireNonNull(getClass().getResource(FONTSTYLE_PATH))
                    .toExternalForm();

            scene.getStylesheets().add(fontstyle);
            loader.<MainWindow>getController().setDuke(DUKE);

            stage.getIcons().add(KNIGHT_IMAGE);
            stage.setTitle("Knight");
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
