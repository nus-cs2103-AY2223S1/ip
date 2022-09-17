package duke.main;

import java.io.IOException;
import java.util.List;

import duke.gui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Duke");
            stage.setScene(scene);
            List<String> params = getParameters().getRaw();

            if (params.size() > 0) {
                fxmlLoader.<MainWindow>getController().makeDuke(params.get(0));
            } else {
                fxmlLoader.<MainWindow>getController().makeDuke();
            }
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
