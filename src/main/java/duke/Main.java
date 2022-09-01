package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @author Jeffry Lum
 * https://se-education.org/guides/tutorials/javaFxPart4.html
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Dukebot dukebot = new Dukebot();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(dukebot);
            stage.setTitle("Duke");
            stage.show();
            fxmlLoader.<MainWindow>getController().displayStartupMessages();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}