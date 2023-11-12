package anthea.gui;

import java.io.IOException;

import anthea.Anthea;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

//@@author clarence-chew-reused
// Reused from this tutorial
// https://se-education.org/guides/tutorials/javaFx.html
// with minor modifications at most

/**
 * A GUI for Anthea using FXML.
 */
public class Main extends Application {
    /**
     * Start the application and Anthea logic.
     *
     * @param stage JavaFX stage.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            // Start application logic in thread
            Thread appLogic = new Thread("appLogic") {
                @Override
                public void run() {
                    Anthea.setUi(new GraphicUi());
                    Anthea.main(new String[]{});
                }
            };
            appLogic.start();
            fxmlLoader.<MainWindow>getController().getOutput();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

//@@author
