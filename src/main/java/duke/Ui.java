package duke;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

import duke.ui.MainWindow;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Ui extends Application {

    private MainWindow mainWindow;

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Ui.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            mainWindow = fxmlLoader.getController();
            Duke duke;
            try {
                duke = new Duke("tasks.txt", (Message message) -> {
                    mainWindow.sendMessage(message);
                    if (message.shouldExit) {
                        new Timer().schedule(
                                new TimerTask() {
                                    @Override
                                    public void run() {
                                        Platform.exit();
                                    }
                                },
                                1500
                        );
                    }
                });
            } catch (DukeException e) {
                throw new RuntimeException(e);
            }
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
