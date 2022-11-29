package duke;

import duke.commands.Command;
import duke.commands.CommandResult;
import duke.utils.Parser;
import duke.utils.Storage;
import duke.utils.TaskList;

/**
 * Main class of Duke.
 * This class also serves as the main entry point of application.
 *
 * @author sikai00
 */
public class Duke {
    private final TaskList taskList;
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
        return c.execute(this.taskList, this.storage);
    }
}
