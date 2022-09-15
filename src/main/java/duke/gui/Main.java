package duke.gui;

import duke.Duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

//@@author leehuiyulaura-reused
//Reused from https://se-education.org/guides/tutorials/javaFxPart4.html
// with minor modifications
/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private static Duke duke = new Duke("duke.txt");

    /**
     * Returns the instance of the duke class created when Main is run.
     *
     * @return The instance of the duke class created when Main is run.
     */
    public static Duke getDuke() {
        return duke;
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Duke");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(duke);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

