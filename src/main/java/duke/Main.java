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
    private Duke duke;

    // @@author jorrdansoh-reused
    // Reused from https://se-education.org/guides/tutorials/javaFx.html
    // with minor modifications
    @Override
    public void start(Stage stage) {
        try {
            this.duke = new Duke();
            Main.stage = stage;
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            Scene scene = new Scene(anchorPane);
            Main.stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(this.duke);
            Main.stage.show();
        } catch (DukeException | IOException e) {
            e.printStackTrace();
        }
    }
    //    @@author

    /**
     * Closes the Stage.
     */
    public static void close() {
        Main.stage.close();
    }
}