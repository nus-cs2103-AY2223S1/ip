package duke;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Launcher for the GUI.
 */
public class Launcher extends Application {
    private Duke duke = new Duke();

    // @@author nseah21-reused
    // Reused from https://se-education.org/guides/tutorials/javaFx.html.
    // The below code was referenced from one of their JavaFX tutorials with minor modifications.
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Ditto!");
            // Icon taken from
            // https://www.deviantart.com/exoticpoke/art/132-Shiny-Ditto-872570354
            stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/Icon.png")));
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
