package duke;

import java.io.IOException;

import duke.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    private static Stage stage;
    private Duke duke = new Duke();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            Scene scene = new Scene(anchorPane);
            Main.stage = stage;
            Main.stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            Main.stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes the stage.
     */
    public static void closeStage() {
        Main.stage.close();
    }
}
