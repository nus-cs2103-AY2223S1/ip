package duke.gui;

import java.io.IOException;

import duke.Duke;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

//@@author L1Y1jun-reused
//Adapted from: https://se-education.org/guides/tutorials/javaFxPart4.html#javafx-tutorial-part-4-using-fxml
/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Duke duke = new Duke();
    private Image appIcon = new Image(this.getClass().getResourceAsStream("/images/ApplicationIcon.png"));

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("AlphaBot");
            stage.getIcons().add(appIcon);
            fxmlLoader.<MainWindow>getController().setGui(duke);
            fxmlLoader.<MainWindow>getController().sayGreetings();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
//@@author
