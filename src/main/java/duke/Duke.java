package duke;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import duke.command.Command;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Parser;
import duke.ui.Ui;

/**
 * Main class for Duke program.
 * Keeps track of tasks for user.
 *
 * @author Ho Jun Hao
 */
public class Duke {

    private TaskList tasks;
    private Ui ui;
    private Storage storage;
    private boolean isEnd;

    /**
     * Creates the Duke class to initialise program.
     * Initialises the TaskList, Ui, and Storage.
     */
    public Duke() {
        this.ui = new Ui();
        try {
            this.storage = new Storage();
            this.tasks = new TaskList(storage.loadFile());
        } catch (DukeException e) {
            this.ui.printException(e);
        } catch (IOException e) {
            this.ui.printException(e);
            this.tasks = new TaskList();
        }
    }

    /**
     * Returns Duke bot response to input.
     *
     * @param input User input into bot.
     * @return Duke bot response
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.run(this.tasks, this.ui, this.storage);
        } catch (DukeException | IOException | DateTimeParseException e) {
            return this.ui.printException(e);
        }
    }
}
