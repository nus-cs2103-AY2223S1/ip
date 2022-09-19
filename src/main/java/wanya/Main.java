package wanya;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import wanya.ui.MainWindow;

//@@author laxus2308-reused
//Reused from https://se-education.org/guides/tutorials/javaFxPart4.html
/**
 * A GUI for Wanya using FXML.
 */
public class Main extends Application {
    private Wanya wanya = new Wanya();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Wanya Task Bot");
            fxmlLoader.<MainWindow>getController().setWanya(wanya);
            fxmlLoader.<MainWindow>getController().showLoading();
            fxmlLoader.<MainWindow>getController().showGreeting();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
//@@author
