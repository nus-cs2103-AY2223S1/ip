package stashy.launcher;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import stashy.Stashy;

/**
 * A GUI for Stashy using FXML.
 */
public class Main extends Application {

    private Stashy stashy = new Stashy();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Stashy v0.5");
            stage.setResizable(false);
            stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/user.png")));
            fxmlLoader.<MainWindow>getController().setStashy(stashy);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
