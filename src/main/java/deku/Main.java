package deku;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import deku.Ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {
    //Solution below adapted and modified from https://stackoverflow.com/questions/24709769/
    private static final Path DIRECTORY_PATH = Paths.get(System.getProperty("user.dir"), "data");
    private static final Path FILE_PATH = DIRECTORY_PATH.resolve("save.txt");
    private Deku deku = new Deku(DIRECTORY_PATH, FILE_PATH);

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Deku");
            stage.setResizable(false);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(deku);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}