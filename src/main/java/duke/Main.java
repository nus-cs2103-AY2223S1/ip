package duke;

import java.io.IOException;

import duke.gui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * @author Jeffry Lum - reused
 * Adapted from https://se-education.org/guides/tutorials/javaFxPart4.html
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.setTitle("Duke");
            // Waving Duke icon modified from https://en.wikipedia.org/wiki/File:Duke_(Java_mascot)_waving.svg
            // Duke is Copyright Sun Microsystems and the image used under BSD license
            stage.getIcons().add(new Image("/images/DukeMascot.png"));

            // code snippet reused from @sikai00
            stage.widthProperty().addListener((o, oldValue, newValue) -> {
                if (newValue.intValue() < 400.0) {
                    stage.setResizable(false);
                    stage.setWidth(400);
                    stage.setResizable(true);
                }
            });

            stage.show();
            fxmlLoader.<MainWindow>getController().displayStartupMessages();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}