package mort.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import mort.Mort;

/**
 * A GUI for Mort using FXML.
 */
public class Main extends Application {
    private Mort mort = new Mort();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Mort");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setMort(mort);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
