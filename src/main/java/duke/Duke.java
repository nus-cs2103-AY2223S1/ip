package duke;

import duke.gui.MainWindow;
import duke.logic.Parser;
import duke.logic.Storage;
import duke.logic.TaskList;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Duke is a Personal Assistant Chatbot that helps a person to keep track of various things.
 *
 * @author totsukatomofumi
 */
public class Duke extends Application {
    /** Storage object for writing task history to a file. */
    private Storage storage;

    /** Task list for Duke to remember. */
    private TaskList taskList;

    /** Parser to parse user responses. */
    private Parser parser;

    private static String filePath = "./data/history.txt";

    /**
     * Constructor for Duke.
     *
     */
    public Duke() {
        this.storage = new Storage(Duke.filePath);
        this.taskList = new TaskList(this.storage);
        this.parser = new Parser(this.taskList);
    }

//    /**
//     * Starts Duke up.
//     */
//    private void run() {
//        ui.start(parser);
//    }

//    public static void main(String[] args) {
//        new Duke("./data/history.txt").run();
//    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(duke.Duke.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setParser(this.parser);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


