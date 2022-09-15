package dukeegg;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ui.MainWindow;

/**
 * A GUI for the chatbot using FXML.
 */
public class Main extends Application {
    private static final String TITLE = "Dukegg Chatbot";
    private final Dukegg dukegg = new Dukegg("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            // Initialise scene with title and FXML file
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle(TITLE);
            // Updates the chatbot instance, which sends a greeting message
            fxmlLoader.<MainWindow>getController().setDukegg(this.dukegg);
            // Add styles to scene
            scene.getStylesheets().add("/styles/styles.css");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
