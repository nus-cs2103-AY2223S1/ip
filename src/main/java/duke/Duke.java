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
    public static final Path STORAGE_PATH = Paths.get(System.getProperty("user.dir"), "data", "duke.txt");

    private final Storage storage;
    private final TaskList tasks;
    private boolean running;

    /**
     * Constructor for Duke.
     * @param filePath Path to storage file.
     */
    public Duke(Path filePath) {
        storage = new Storage(filePath);
        tasks = new TaskList();
        running = true;
    }

    public Duke() {
        this(STORAGE_PATH);
    }

    public String loadTasks() {
        String ret = "Loaded tasks from storage";
        try {
            tasks.loadTasksFromStorage(storage);
        } catch (DukeException e) {
            ret = "Error loading tasks from storage: " + e.getMessage();
        }
        return ret;
    }

    public String getResponse(String input) {
        String out;
        try {
            Command c = Parser.parseCommand(input);
            out = c.execute(tasks, storage);
            if (c.isExit()) {
                running = false;
            }
        } catch (DukeException e) {
            out = "Error: " + e.getMessage();
        }
        return out;
    }

    public String getWelcome() {
        return "Hello! I'm Duke.\nWhat can I do for you?";
    }

    public boolean isRunning() {
        return running;
    }
}
