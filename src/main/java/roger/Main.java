package roger;

import java.io.IOException;
import java.nio.file.Paths;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import roger.storage.Storage;
import roger.ui.Parser;
import roger.ui.controllers.MainWindow;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Parser parser;
    private Storage storage;
    private Roger roger;

    /**
     * Start the Roger backend, and the JavaFX UI elements.
     * @param stage
     */
    @Override
    public void start(Stage stage) {
        parser = new Parser();
        storage = new Storage(Paths.get("data/database.txt"));
        roger = new Roger(parser, storage);
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