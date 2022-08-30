package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.utils.Parser;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;
import javafx.application.Platform;

/**
 * Main class of Duke.
 * This class also serves as the main entry point of application.
 */
public class Duke {
    private TaskList taskList;
    private final Ui ui;
    private final Parser parser;
    private final Storage storage;

    /**
     * Initializes a new Duke instance.
     */
    public Duke() {
        this.ui = new Ui();
        this.parser = new Parser();
        this.storage = new Storage("data", "duke");
        this.taskList = this.storage.readFromStorage();
    }

    public String getResponse(String input) {
        Command c = parser.parseCommand(input);
        try {
            String responseMsg = c.execute(this.taskList, this.ui, this.storage);
            if (c.isExit()) {
                Platform.exit();
            }
            return responseMsg;
        } catch (DukeException e) {
            String exceptionMsg = e.getMessage();
            return exceptionMsg;
        }
    }
}
