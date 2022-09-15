package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.model.TaskList;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * The main class for the chatbot.
 */
public class Duke {

    private Storage storage;
    private TaskList tasklist;
    private Ui ui;

    /**
     * A constructor for the chatbot.
     *
     * @param filePath The file path for storage.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasklist = new TaskList(storage.loadFromFile());
    }

    /**
     * Returns a string representation of a response based on the user's input.
     *
     * @param input The user's input.
     * @return A string representation of the response.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            assert command != null : "Command should not be null";
            return command.execute(tasklist, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
