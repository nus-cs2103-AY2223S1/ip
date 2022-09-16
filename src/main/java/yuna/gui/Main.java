package yuna.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import yuna.Yuna;

/**
 * A GUI for Yuna using FXML.
 */
public class Main extends Application {

    private Yuna yuna = new Yuna("data/yuna.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Yuna");
            fxmlLoader.<MainWindow>getController().setYuna(yuna);
            fxmlLoader.<MainWindow>getController().sendGreeting();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
