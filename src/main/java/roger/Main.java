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
public class Main {

    public static void main(String[] args) {
        Launcher.launch(Application.class, args);
        System.exit(0);
    }
}
