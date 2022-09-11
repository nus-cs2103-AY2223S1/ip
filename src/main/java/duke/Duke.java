package duke;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main class for running the Duke chatbot.
 */
public class Duke extends Application {
    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;


    public Duke() {
        this("./tasks.txt");
    }

    /**
     * Initialises the Duke chatbot, loads tasks from specified filepath if found.
     * @param filepath where tasks are stored in persistent memory.
     */
    public Duke(String filepath) {
        storage = new Storage(filepath);
        try {
            tasks = storage.load();
        } catch (Exception e) {
            tasks = new TaskList();
        }
        ui = new Ui(storage, tasks);
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource("/view/Ui.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<Ui>getController().setDuke(new Duke());
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}