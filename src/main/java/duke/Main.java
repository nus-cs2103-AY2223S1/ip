package duke;

import java.io.IOException;

import duke.fxwindows.Window;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();
//    private MainWindow mainWindow = new MainWindow();
    private Window window= new Window();
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Window.class.getResource("/view/Window.fxml"));
            fxmlLoader.setController(window);
            fxmlLoader.setRoot(window);

            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().add(getClass().getResource("/view/TaskCategoryPane.css").toExternalForm());
            stage.setScene(scene);
            fxmlLoader.<Window>getController().initialise(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initDuke() {
        duke.init();
    }
}