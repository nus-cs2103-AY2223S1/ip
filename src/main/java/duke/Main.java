package duke;

import java.io.IOException;
import java.util.List;

import javafx.application.Application;
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
        Parameters params = getParameters();
        List<String> args = params.getRaw();
        initDuke(args);
        setStage(stage);
    }

    private void initDuke(List<String> args) {
        if (args.isEmpty()) {
            duke = new Duke();
        } else {
            duke = new Duke(args.get(0));
        }
    }

    private void setStage(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.setTitle("MakiBot");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
