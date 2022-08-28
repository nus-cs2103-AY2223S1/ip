package duke;

import java.io.IOException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke;


    @Override
    public void start(Stage stage) {
        try {
            duke = new Duke();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            scene.getRoot().setStyle("-fx-font-family: 'Arial'");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Duke");

            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
