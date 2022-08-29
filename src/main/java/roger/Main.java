package roger;

import java.io.IOException;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import roger.ui.controllers.MainWindow;
import roger.storage.Storage;
import roger.ui.Parser;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Parser parser = new Parser();
    private Storage storage = new Storage(Paths.get("data/database.txt"));
    private Roger roger = new Roger(parser, storage);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setRogerClass(roger);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}