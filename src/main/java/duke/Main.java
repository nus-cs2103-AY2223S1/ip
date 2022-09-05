package duke;

import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

import duke.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    private Duke duke;
    private String applicationName;

    @Override
    public void init() {
        List<String> applicationParams = getParameters().getUnnamed();
        assert applicationParams.size()
            == 2 : "Incorrect number of parameters. Expected: 2 (applicationName, cachePath)";

        String applicationName = applicationParams.get(0);
        String cachePath = applicationParams.get(1);
        this.duke = new Duke(Paths.get(cachePath));
        this.applicationName = applicationName;
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle(applicationName);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
