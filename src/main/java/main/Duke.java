package main;

import Exception.DukeException;
import Parser.Parser;
import Storage.Storage;
import TaskList.TaskList;
import UI.Ui;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;


/**
 * The launcher for the Duke application.
 */
public class Duke extends Application {
//    private ScrollPane scrollPane;
//    private VBox dialogContainer;
//    private TextField userInput;
//    private Button sendButton;
//    private Scene scene;

    private TaskList taskList;
    private final Storage storage;
    private final Ui ui;
    private final Parser parser;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.taskList = new TaskList(storage.loadFile());
        } catch (DukeException de) {
            ui.printException(de);
            this.taskList = new TaskList();
        }
        this.parser = new Parser(this.ui, this.taskList);
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    private void run() {
        try {
            this.ui.printWelcome();
            while (this.parser.hasNext()) {
                this.parser.handleNext();
            }
            this.storage.storeToFile(this.taskList);
        } catch (DukeException de) {
            this.ui.printException(de);
        }
        this.ui.printGoodbye();
    }

    public static void main(String[] args) {
        new Duke("Duke.txt").run();
    }
}
