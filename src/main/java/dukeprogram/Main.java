package dukeprogram;

import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private static ArrayList<Application> applications = new ArrayList<>();

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            ap.setId("backgroundWindow");
            Scene scene = new Scene(ap);
            scene.getStylesheets().add("/css/ElementStyle.css");
            stage.setScene(scene);
            stage.setMinWidth(400);
            stage.setMinHeight(650);
            stage.show();
            applications.add(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static HostServices getPrimaryHostService() {
        assert applications.size() > 0;

        return getApplication(0).getHostServices();
    }

    /**
     * Retrieves a running application by index
     * @param i index of the application, ordered by instantiation order
     * @return the application indexed
     */
    public static Application getApplication(int i) {
        return applications.get(i);
    }
}
