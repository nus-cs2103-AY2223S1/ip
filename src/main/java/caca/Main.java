package caca;

import java.io.IOException;

import caca.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

//@@author carriezhengjr-reused
// Reused from https://se-education.org/guides/tutorials/javaFxPart4.html with modifications.

/**
 * A GUI for CaCa using FXML.
 */
public class Main extends Application {

    private final CaCa caca = new CaCa("data/", "data/caca.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("CaCa chatbot");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setCaCa(caca);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
