package rick;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import rick.Rick;
import rick.gui.DialogBox;
import rick.gui.MainWindow;

import java.io.IOException;

/**
 * A GUI for Rick using FXML.
 */
public class Main extends Application {

    private final Rick rick = new Rick();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setRick(rick);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}