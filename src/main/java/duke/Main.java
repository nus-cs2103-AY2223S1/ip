package duke;

import java.io.IOException;

import duke.gui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke("data/duke.txt");

    private final Image iconImage = new Image(this.getClass().getResourceAsStream("/images/Icon.png"));

    /**
     * Creates the ui of duke.
     *
     * @param stage the primary stage for this application, onto which
     *              the application scene can be set.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.getIcons().add(iconImage);
            stage.setTitle("Super Duke");
            stage.show();

            Alert welcome = new Alert(Alert.AlertType.INFORMATION);
            welcome.setHeaderText("Super Duke");
            welcome.setContentText("Tell me what to do by entering in the box below!"
                    + System.lineSeparator() + "Press enter to continue!");
            welcome.showAndWait();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
