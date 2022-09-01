package duke.main;

import java.io.IOException;
import java.util.Objects;

import duke.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private final Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);

            // Dialog box title and icon
            stage.setTitle("Tutter's Speakeasy");
            stage.getIcons().add(new Image(Objects.requireNonNull(
                    Main.class.getResource("/images/Duke.png")).openStream()));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
