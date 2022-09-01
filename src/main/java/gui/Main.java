package gui;

import java.io.IOException;

import alan.Alan;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Alan alan = Alan.getInstance();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            System.out.println(alan == null);
            fxmlLoader.<MainWindow>getController().setBot(alan);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}