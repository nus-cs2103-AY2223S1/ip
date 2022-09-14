package pluto;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pluto.controller.MainWindow;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    /** Instance of Pluto */
    private Pluto pluto = new Pluto();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Pluto");
            fxmlLoader.<MainWindow>getController().setPluto(pluto);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
