package main;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ui.MainWindow;
import ui.Ui;

/**
 * <h1>Main class</h1>
 * Loads fxml files and sets the stage of the application.
 */
public class Main extends Application {
    private Ui ui;

    /**
     * Loads the MainWindow's fxml file and sets the stage of the
     * application.
     *
     * @param stage the primary stage for this application, onto which
     *        the application scene can be set.
     */
    @Override
    public void start(Stage stage) {
        try {
            ui = new Ui();
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane mainWindow = fxmlLoader.load();
            Scene scene = new Scene(mainWindow);
            stage.setScene(scene);
            stage.setResizable(false);
            fxmlLoader.<MainWindow>getController().setDuke(ui);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
