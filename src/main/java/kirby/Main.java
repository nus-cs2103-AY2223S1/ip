package kirby;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import kirby.ui.MainWindow;
import kirby.ui.Ui;

/**
 * A GUI for Kirby using FXML.
 */
public class Main extends Application {

    private Kirby kirby;

    /**
     * Constructor for Main method.
     */
    public Main() throws IOException {
        String fileName = "data/kirby.txt";
        String dirName = "data/";
        this.kirby = new Kirby(fileName, dirName);
    }

    /**
     * {@inheritDoc}
     * Sets the GUI stage to display all the nodes.
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Kirby Deluxe Bot");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setKirby(kirby);
            fxmlLoader.<MainWindow>getController().showWelcome(Ui.showWelcome());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
