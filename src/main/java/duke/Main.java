package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import static duke.Parser.storage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    /**
     * Instance of duke.
     */
    private Duke duke = new Duke();

    /**
     * Starts the duke chatbot.
     *
     * @param stage The stage on which duke starts.
     */
    @Override
    public void start(Stage stage) {
        try {
            storage.readFromFile();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
