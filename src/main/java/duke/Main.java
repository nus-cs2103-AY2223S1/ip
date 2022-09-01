package duke;

import duke.data.MainWindow;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;

import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;

import javafx.scene.layout.AnchorPane;

import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;


import duke.data.Duke;

/**
 * Main file to run the program.
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) {
        Duke duke = new Duke();

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
