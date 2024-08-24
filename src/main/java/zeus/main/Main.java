package zeus.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Zeus using FXML.
 */
public class Main extends Application {

    private Zeus zeus = new Zeus();

    /**
     * Starts the stage for this application.
     *
     * @param stage The primary stage for this application, onto which the application scene can be set.
     *              Applications may create other stages, if needed, but they will not be primary stages.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Zeus");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setZeus(zeus);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
