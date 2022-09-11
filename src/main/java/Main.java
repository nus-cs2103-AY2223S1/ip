import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Luna luna = new Luna("./data/luna.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle(" ❈  ✶  ✧︎ ✱✬ ✨  l u n a  ✨ ❇︎ ✫❍   ❈ ✶  ✶ ");
            stage.setResizable(false);
            fxmlLoader.<MainWindow>getController().setLuna(luna);
            fxmlLoader.<MainWindow>getController().welcome();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
