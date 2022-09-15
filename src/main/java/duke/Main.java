package duke;

import java.io.IOException;

import duke.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Dodie using FXML.
 *
 * @author sikai00
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
            stage.setTitle("Dodie");
            stage.widthProperty().addListener((o, oldValue, newValue) -> {
                if (newValue.intValue() < 400.0) {
                    stage.setResizable(false);
                    stage.setWidth(400);
                    stage.setResizable(true);
                }
            });
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
