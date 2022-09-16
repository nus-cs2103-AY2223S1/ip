package duke;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
// Reused from https://se-education.org/guides/tutorials/javaFx.html.
// with minor modifications
/**
 * Duke GUI with FXML.
 */
public class Main extends Application {
    private final Duke DUKE = new Duke();

    /**
     * {@inheritDoc}
     */
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));

            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            primaryStage.setScene(scene);
            primaryStage.setTitle("Duke the Task Manager");

            fxmlLoader.<MainWindow>getController().setDuke(DUKE);
            primaryStage.show();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
