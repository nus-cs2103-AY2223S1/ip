package manmeowth;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import manmeowth.ui.MainWindow;

/**
 * A GUI for ManMeowth using FXML.
 */
public class Main extends Application {

    private ManMeowth manMeowth = new ManMeowth();

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("The Mythical Man-Meowth");
            stage.setResizable(false);
            stage.setScene(scene);
            MainWindow controller = fxmlLoader.<MainWindow>getController();
            controller.setManMeowth(manMeowth);
            controller.showWelcome();
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
