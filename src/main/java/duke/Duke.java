package duke;

import java.nio.file.Path;
import java.nio.file.Paths;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * App to store and keep track of tasks.
 */
public class Duke {
    public static final Path STORAGE_PATH = Paths.get(System.getProperty("user.dir"), "data", "duke.txt");

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    /**
     * Constructor for Duke.
     * @param filePath Path to storage file.
     */
    public Duke(Path filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            this.ui.showLoadingError(e.getMessage());
            this.tasks = new TaskList();
        }
    }

    public Duke() {
        this(STORAGE_PATH);
    }

    /**
     * Function to start the app.
     */
    public void run() {
        this.ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = this.ui.readCommand();
                Command c = Parser.parseCommand(fullCommand);
                c.execute(this.tasks, this.ui, this.storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.emptyLine();
            }
        }
    }

    /**
     * Entry point for the Duke class.
     * @param args Unused parameter.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    protected String getResponse(String input) {
        return "Duke heard: " + input;
    }
}
