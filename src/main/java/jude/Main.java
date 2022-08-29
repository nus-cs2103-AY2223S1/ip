package jude;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

//@@author cheeheng-reused
//Reused from https://se-education.org/guides/tutorials/javaFxPart4.html with minor
//modifications.

/**
 * A GUI for Jude the chatbot using FXML.
 */
public class Main extends Application {
    private final Jude jude = new Jude("data/tasks.txt");

    /**
     * Creates a new GUI instance for Jude the chatbot.
     *
     * @throws IOException When system I/O fails.
     */
    public Main() throws IOException {
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setJude(jude);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
