package carbon;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import carbon.error.CarbonException;

/**
 * Interacts with user through CLI to keep track of tasks.
 * Requires user to provide input and call task management commands.
 */
public class Carbon extends Application {
    private boolean isRunning;
    private Ui ui;
    private Parser parser;
    private Storage storage;

    /**
     * Creates an instance of Carbon.
     * Initializes with required components: UI, Storage, and Parser.
     *
     * @return Carbon object.
     */
    public Carbon() {
        // init fields
        this.ui = new Ui();
        this.storage = new Storage();
        this.parser = new Parser(this.ui, this.storage);
    }

    /**
     * Runs the Carbon interactive bot.
     */
    private void run() {
        this.isRunning = true;
        while (this.isRunning) {
            try {
                String input = this.ui.printIn();
                String log = this.parser.process(input);
                if (log != "") {
                    this.ui.printOut(log);
                } else {
                    this.ui.exit();
                    this.isRunning = false;
                }
            } catch (CarbonException error) {
                this.ui.printError(error);
            }
        }
    }

    @Override
    public void start(Stage stage) {
        Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.
    }

    /**
     * Initializes and runs an instance of Carbon.
     *
     * @param args Additional arguments.
     */
    public static void main(String[] args) {
        Carbon shell = new Carbon();
        shell.run();
    }
}
