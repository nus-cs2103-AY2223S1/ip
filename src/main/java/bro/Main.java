package bro;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Bro using FXML.
 */
public class Main extends Application {

    private Bro bro = new Bro();
    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("Bro");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<bro.controllers.MainWindow>getController().setBro(bro);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
