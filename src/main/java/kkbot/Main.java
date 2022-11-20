package kkbot;

import java.io.IOException;

import kkbot.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for KKBot using FXML.
 */
public class Main extends Application {

    private KKBot kkbot = new KKBot();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("KKBot");
            fxmlLoader.<MainWindow>getController().setKKBot(kkbot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}