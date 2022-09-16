package dan;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Dan dan = new Dan();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            //stage.setTitle("Dan v1.1");
            //stage.setResizable(false);
            fxmlLoader.<MainWindow>getController().setDuke(dan);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}