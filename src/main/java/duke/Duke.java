package duke;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import duke.task.TaskList;
import duke.util.Parser;
import duke.util.StoredTasks;
import duke.util.Ui;

import java.io.File;

/**
 * Duke class to run and execute the program.
 *
 * @author Kavan
 */
public class Duke extends Application {
    private static final String FILE_DIR = "data";
    private static final String FILE_PATH = FILE_DIR + File.separator + "duke.txt";

    private StoredTasks storedTasks;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for Duke class.
     */
    public Duke() {
        this.storedTasks = new StoredTasks(FILE_DIR, FILE_PATH);
        this.ui = new Ui();
        try {
            this.tasks = new TaskList(this.storedTasks.load());
        } catch (DukeException e) {
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs the Duke program.
     */
    public void runDuke() {
        while (true) {
            String command = this.ui.getUserCommand();
            try {
                Parser.handleCommand(command, this.tasks);
                if (command.equals("bye")) {
                    this.storedTasks.save(this.tasks);
                    break;
                }
            } catch (DukeException de) {
                this.storedTasks.save(this.tasks);
                System.out.println(de);
                break;
            }
        }
    }

    /**
     * Main method for the Duke class.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new Duke().runDuke();
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }
}