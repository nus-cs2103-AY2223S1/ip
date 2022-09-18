package clevernotbot;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for CleverNotBot using FXML.
 */
public class Main extends Application {

    private CleverNotBot cleverNotBot = new CleverNotBot();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.getIcons().add(new Image("/images/CleverNotBotTaskIcon.png"));
            String css = this.getClass().getResource("/css/styles.css").toExternalForm();
            scene.getStylesheets().add(css);
            stage.setScene(scene);
            stage.setTitle("CleverNotBot Chat-Bot");
            fxmlLoader.<MainWindow>getController().setCleverNotBot(cleverNotBot);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
