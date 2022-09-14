package gui;

import java.io.IOException;

import monke.Monke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Monke monke = Monke.getInstance();

    @Override
    public void start(Stage stage) {
        try {
            stage.setTitle("MONKE");
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().add(getClass().getResource("/style/main.css").toExternalForm());
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setBot(monke);
            fxmlLoader.<MainWindow>getController().sendIntro();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}