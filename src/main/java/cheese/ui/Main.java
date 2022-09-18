package cheese.ui;

import java.io.IOException;

import cheese.Cheese;
import cheese.ui.controller.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Represents a GUI for Cheese using FXML.
 */
public class Main extends Application {

    /** An instance of Cheese. */
    private Cheese cheese = new Cheese("data/cheese.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Cheese");
            fxmlLoader.<MainWindow>getController().setCheese(cheese);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
