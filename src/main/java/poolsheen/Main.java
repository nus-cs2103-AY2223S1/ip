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

    private Poolsheen poolsheen = new Poolsheen();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setPoolsheen(poolsheen);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
