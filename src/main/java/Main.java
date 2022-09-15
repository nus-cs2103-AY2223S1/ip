package anya;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Represents A GUI for Duke using FXML.
 */
public class Main extends Application {

    private static final String FILE_LOCATION = "./data/duke.txt";

    private Anya anya;

    public Main() throws Exception {
        this.anya = new Anya(FILE_LOCATION);
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setAnya(anya);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

