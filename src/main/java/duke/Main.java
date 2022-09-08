package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 *
 * @author bensohh
 * @version CS2103T AY 22/23 Sem 1 (G01)
 */
public class Main extends Application {

    private Duke duke;

    /**
     * Starts the Duke application by bridging the gap in Duke and the UI in MainWindow.
     * @param stage Current stage of the application
     */
    @Override
    public void start(Stage stage) {
        this.duke = new Duke("./data/tasks.txt", stage);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
