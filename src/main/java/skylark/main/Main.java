package skylark.main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import skylark.ui.MainWindow;

/**
 * A GUI for Skylark using FXML.
 */
public class Main extends Application {

    private final Skylark skylark = new Skylark();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setSkylark(skylark);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
