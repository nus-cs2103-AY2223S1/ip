package violet.javafx;

import java.io.IOException;

import violet.Violet;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Violet using FXML.
 */
public class Main extends Application {

    private Violet violet = new Violet("data/violet.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            stage.setTitle("Violet");
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setViolet(violet);
            fxmlLoader.<MainWindow>getController().greeting();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}