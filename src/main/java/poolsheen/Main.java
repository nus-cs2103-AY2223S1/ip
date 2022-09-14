package poolsheen;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Poolsheen using FXML.
 */
public class Main extends Application {
    @Override
    public void start(Stage stage) {
        try {
            Poolsheen poolsheen = new Poolsheen();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Poolsheen");
            fxmlLoader.<MainWindow>getController().setPoolsheen(poolsheen);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
