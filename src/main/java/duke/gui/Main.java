package duke.gui;

import java.io.IOException;
import java.util.Optional;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().add(getClass().getResource("/style/style.css").toExternalForm());
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke();
            stage.show();
            Platform.setImplicitExit(false);
            EventHandler <WindowEvent> windowCloseHandler = new EventHandler<WindowEvent>() {
                @Override
                public void handle(WindowEvent event) {
                    if (fxmlLoader.<MainWindow>getController().isStillRunning()) {
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                                "Close without saving? Use command 'bye' to save your tasks.",
                                ButtonType.CANCEL, ButtonType.YES);
                        alert.setTitle("Quit application");
                        alert.initOwner(stage.getOwner());
                        Optional<ButtonType> res = alert.showAndWait();

                        if (res.isPresent() && res.get().equals(ButtonType.CANCEL)) {
                            event.consume();
                        }
                    }
                }
            };
            stage.setOnCloseRequest(windowCloseHandler);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
