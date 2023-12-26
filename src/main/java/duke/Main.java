package duke;

import java.io.IOException;

import duke.fxwindows.Window;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.NotificationPane;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();
    private Window window= new Window();
    private Stage stage;
    private NotificationPane np;
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Window.class.getResource("/view/Window.fxml"));
            fxmlLoader.setController(window);
//            fxmlLoader.setRoot(window);

            this.stage = stage;
            AnchorPane ap = fxmlLoader.load();
            fxmlLoader.<Window>getController().initialise(duke);
            NotificationPane np = new NotificationPane(ap);

            Scene scene = new Scene(np);
            scene.getStylesheets().add(getClass().getResource("/view/styles.css").toExternalForm());
            stage.setScene(scene);
            stage.setTitle("Duke Task Manager");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showNotification(String text) {
        np.setText("text");
        np.show();
    }

    public void initDuke() {
        duke.init();
    }

    public void exit() {
        stage.close();
    }
}