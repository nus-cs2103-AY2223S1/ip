package meower;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

//taken from textbook

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Meower meower = new Meower();

    @Override
    public void start(Stage stage) {
        assert meower != null: "meower bot cannot be null";
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(meower);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
