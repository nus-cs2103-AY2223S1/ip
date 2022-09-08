package uwu.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import uwu.uwu.UwuBot;

/**
 * Main file to run the program.
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) {
        UwuBot uwu = new UwuBot();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("UwuBot Chat");
            fxmlLoader.<MainWindow>getController().setUwu(uwu);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
