package duke;

import duke.commands.Command;
import duke.commands.CommandResult;
import duke.exceptions.DukeException;
import duke.utils.Parser;
import duke.utils.Storage;
import duke.utils.TaskList;

/**
 * Main class of Duke.
 * This class also serves as the main entry point of application.
 */
public class Duke {
    private TaskList taskList;
    private final Parser parser;
    private final Storage storage;

    /**
     * Initializes a new Duke instance.
     */
    public Duke() {
        this.parser = new Parser();
        this.storage = new Storage("data", "duke");
        this.taskList = this.storage.readFromStorage();
    }

    public CommandResult getResponse(String input) {
        Command c = parser.parseCommand(input);
        try {
            return c.execute(this.taskList, this.storage);
        } catch (DukeException e) {
            return new CommandResult(e.getMessage());
        }
    }
}
