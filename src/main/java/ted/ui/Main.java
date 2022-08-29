package ted.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ted.Storage;
import ted.Ted;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        Storage storage = new Storage("./data/tasks.txt");
        UiController uiController = new UiController();
        Ted ted = new Ted(storage);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            MainWindow mainWindow = fxmlLoader.<MainWindow>getController();

            // Ted <-> UiController <-> MainWindow
            ted.setUi(uiController); // Ted -> UiController
            uiController.setMainWindow(mainWindow); // UiController -> MainWindow
            uiController.setTed(ted); // UiController -> Ted
            mainWindow.setUi(uiController); // MainWindow -> UiController

            stage.show();
            ted.startup();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
