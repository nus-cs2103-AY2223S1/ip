package duke;

import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a robot that takes in tasks given by user through CLI, and other requests such as
 * listing all tasks, deleting tasks, adding tasks and marking tasks.
 *
 * @author Elgin
 */
public class Duke {
    /** All Tasks */
    private TaskList tasks;

    /** Storage for tasks. */
    private final Storage storage;

    /** Ui for Duke. */
    private final Ui ui;

    /**
     * Constructor for Duke.
     *
     * @param filePath Path to storage file from root folder (e.g. 'src/data/duke.txt').
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.ui = new Ui();

        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (FileNotFoundException e) {
            this.ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Executes the command given by the user and act accordingly.
     *
     * @param userInput The input that user provides.
     * @throws DukeException if user input is not in a valid format.
     * @throws NumberFormatException if indexes provided for commands that require it cannot be casted into
     *                               integer (e.g. mark, delete).
     * @throws DateTimeParseException if date provided for commands is invalid.
     */
    public void handleUserInput(String userInput) throws DukeException,
            NumberFormatException, DateTimeParseException {

        Command c = Parser.parse(userInput);
        c.execute(this.tasks, this.ui, this.storage);
        this.storage.save(this.tasks);
    }
}
