package drake.gui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


/**
 * A GUI for Drake.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/Window.fxml"));
            AnchorPane anchorPane = fxmlLoader.load();
            Scene scene = new Scene(anchorPane);
            scene.getStylesheets().add("/view/styles.css");
            stage.setScene(scene);
            stage.show();
            stage.setTitle("Drake");
            stage.setMinHeight(650.0);
            stage.setMaxWidth(400.0);
            stage.setMinWidth(400.0);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
