package Duke.gui;

import java.io.IOException;
import Duke.*;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Main extends Application {

    private Duke duke = new Duke();


    public Main() throws IOException, DukeException {
    }


    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            stage.setTitle("Duke");
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
