package duke;

import java.nio.file.Path;
import java.nio.file.Paths;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * App to store and keep track of tasks.
 */
public class Duke {
    public static final String NAME = "Duke";
    public static final String GREETING = "Hello! I'm Duke.\nWhat can I do for you?";
    public static final Path STORAGE_PATH = Paths.get(System.getProperty("user.dir"), "data", "duke.txt");

    private final Storage storage;
    private final TaskList tasks;
    private boolean isRunning;

    /**
     * Creates an instance of Duke.
     *
     * @param filePath Path to storage file.
     */
    public Duke(Path filePath) {
        storage = new Storage(filePath);
        tasks = new TaskList();
        isRunning = true;
    }

    /**
     * Creates an instance of Duke with the default storage path.
     */
    public Duke() {
        this(STORAGE_PATH);
    }

    /**
     * Loads tasks from storage into the list of tasks.
     *
     * @return A string representing the outcome of the attempt.
     */
    public String loadTasks() {
        String ret;
        try {
            int numTasks = tasks.loadTasksFromStorage(storage);
            if (numTasks > 0) {
                ret = "Loaded " + numTasks + " tasks from storage";
            } else {
                ret = "No tasks in storage";
            }
        } catch (DukeException e) {
            ret = "Error loading tasks from storage: " + e.getMessage();
        }
        return ret;
    }

    /**
     * Processes the input and produces a response.
     *
     * @param input String representing input to Duke.
     * @return A string representing the Duke's response.
     */
    public String getResponse(String input) {
        String out;
        try {
            Command c = Parser.parseCommand(input);
            out = c.execute(tasks, storage);
            if (c.isExit()) {
                isRunning = false;
            }
        } catch (DukeException e) {
            out = "Error: " + e.getMessage();
        }
        return out;
    }

    public boolean getIsRunning() {
        return isRunning;
    }
}
