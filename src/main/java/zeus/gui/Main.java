package zeus.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import zeus.Zeus;

/**
 * A GUI for Zeus using FXML.
 *
 * @author Derrick Khoo
 */
public class Main extends Application {

    private Zeus zeus = new Zeus("data/zeus.txt");

    /**
     * Sets the start point for the GUI.
     *
     * @param stage the primary stage for this application, onto which
     *      the application scene can be set.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setZeus(zeus);
            fxmlLoader.<MainWindow>getController().sendGreetings();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
