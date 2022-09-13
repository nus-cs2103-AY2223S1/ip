package dobby;

import java.io.IOException;

import dobby.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 *
 * <p> Adapted from <a href="https://se-education.org/guides/tutorials/javaFxPart4.html">se-education</a> </p>
 */
public class Main extends Application {
    private Dobby dobby = new Dobby();
    private Image applicationLogo = new Image(this.getClass().getResourceAsStream("/images/dobbyLogo.png"));
    private String applicationTitle = "Dobby the Chat-bot";

    /**
     * Starts the application.
     * Applications may create other stages, if needed, but they will not be primary stages
     *
     * @param stage the primary stage for this application, onto which the application scene can be set
     */
    @Override
    public void start(Stage stage) {

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.getIcons().add(applicationLogo);
            stage.setTitle(applicationTitle);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDobby(dobby);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
