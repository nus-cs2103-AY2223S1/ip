package dobby;

import java.io.IOException;

import dobby.UI.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 * <p>
 * Adapted from <a href="https://se-education.org/guides/tutorials/javaFxPart4.html">se-education</a>
 */
public class Main extends Application {
    private Dobby dobby = new Dobby();

    /**
     * Starts the application.
     *
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set.
     *              Applications may create other stages, if needed, but they will not be
     *              primary stages.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDobby(dobby);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}