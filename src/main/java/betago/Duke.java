package betago;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * Duke class that contains the main program and initialises the various objects used such as
 * TaskList, Parser and Storage.
 */
public class Duke extends Application {
    /** Storage variable to load and save tasks from data file */
    private final Storage storage;

    /** TaskList variable to store tasks */
    private final TaskList tasks;

    /** Parser variable to read commands from the user */
    private final Parser commander;

    /**
     * Constructor for Duke.
     * Initialises Storage, TaskList and Parser variable.
     */
    public Duke() {
        this.tasks = new TaskList();
        this.storage = new Storage(this.tasks);
        this.commander = new Parser(this.tasks, this.storage);
    }

    /**
     * Runs the program by printing welcome message (by calling the Ui greet method),
     * loading date file (by calling Storage loadFile method),
     * get input from users and executing the commands (by calling Parser readCommands method),
     * and terminate by printing goodbye message (by calling the Ui goodbye method).
     */
    public void run() {
        Ui.greet();
        this.storage.loadFile();
        this.commander.readCommands();
        Ui.goodbye();
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
