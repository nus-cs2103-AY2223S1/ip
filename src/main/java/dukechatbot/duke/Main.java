package dukechatbot.duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * This class encapsulates the main class to inject JavaFx into the Duke application.
 *
 * @author A0233290M
 * @version Week4
 */
public class Main extends Application {
    /**
     * The Duke instance to be injected into the program run.
     */
    private Duke dk = new Duke("Storage.txt");

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = loader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            loader.<MainWindow>getController().setDuke(dk);
            stage.show();
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }
    }
}
