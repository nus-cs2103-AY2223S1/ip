package duke;

import java.io.IOException;

import duke.frontend.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private final Duke duke = new Duke();
    private final Image botIcon = new Image(this.getClass().getResourceAsStream("/images/bot-icon.png"));


    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);

            // Window formatting
            //@@author Bubbl3T-reused
            //Reused from https://github.com/Bubbl3T/ip/blob/master/src/main/java/duke/gui/Gui.java
            // with minor modifications
            stage.setTitle("Ratatouille Bot"); // Set the title of the application
            stage.setResizable(false);
            stage.getIcons().add(botIcon);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
