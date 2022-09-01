package ui;

import java.io.IOException;

import entry.Jarvis;
import exceptions.StorageException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Jarvis jarvis;
    private FXMLLoader fxmlLoader;

    @Override
    public void start(Stage stage) {
        try {
            jarvis = new Jarvis();
            fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setJarvis(jarvis);
            fxmlLoader.<MainWindow>getController().setStage(stage);
            stage.show();
            fxmlLoader.<MainWindow>getController().showWelcome();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (StorageException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void stop() throws Exception {
        System.out.println("stop ran");
        fxmlLoader.<MainWindow>getController().save();
    }
}
