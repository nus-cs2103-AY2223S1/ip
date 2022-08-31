package dukeegg;

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
    private static final String TITLE = "Dukegg Chatbot";
    private final Dukegg dukegg = new Dukegg("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            // Initialise scene
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle(TITLE);
            fxmlLoader.<MainWindow>getController().setDuke(this.dukegg);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
