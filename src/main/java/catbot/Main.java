package catbot;

import java.io.IOException;

import catbot.javafx.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for CatBot using FXML.
 */
public class Main extends Application {

    /** The CatBot chatbot to be run. */
    private CatBot catBot = new CatBot("./tasks.txt");

    private Image icon = new Image(this.getClass().getResourceAsStream("/images/icon.jpg"));

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.getIcons().add(icon);
            stage.setTitle("catbot");
            fxmlLoader.<MainWindow>getController().setCatBot(catBot);
            fxmlLoader.<MainWindow>getController().init();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
