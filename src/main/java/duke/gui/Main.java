package duke.gui;

import java.io.IOException;
import java.util.Objects;

import duke.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private final Duke duke = new Duke();
    private final Image dukeIcon = new Image(Objects.requireNonNull(
            this.getClass().getResourceAsStream("/images/DaDuke.png")));

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            GridPane gp = fxmlLoader.load();
            Scene scene = new Scene(gp);
            stage.setScene(scene);
            stage.setTitle("Duke");
            stage.getIcons().add(dukeIcon);
            stage.setMinHeight(258);
            stage.setMinWidth(515);
            MainWindow controller = fxmlLoader.getController();
            controller.setDuke(duke);
            controller.showWelcome();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
