package candice.javafx;

import java.io.IOException;

import candice.Candice;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Candice using FXML.
 */
public class Main extends Application {

    private Candice candice;

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(Stage stage) {
        try {
            this.candice = new Candice();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Candice");
            stage.setResizable(false);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setCandice(this.candice);
            fxmlLoader.<MainWindow>getController().sendStartMessage();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}