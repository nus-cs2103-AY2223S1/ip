package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.storage.TaskList;

/**
 * Represents a bot that will carry out different command with user input.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs a new Duke instance,
     * and loads the content of the local file into storage.
     *
     * @param filePath path of the local path.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Returns a string of Duke's response to the given input.
     *
     * @param input User input.
     * @return A string of responses.
     */
    public String getResponse(String input) {
        try {
            Command command = Parser.parse(input);
            return command.execute(tasks, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
