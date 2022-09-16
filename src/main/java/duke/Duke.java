package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;

import java.util.Objects;

/**
 * Represents the main entry point of the Duke application.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;

    /**
     * Initialises a Duke application with storage.
     * @param filePath Path of file to store created tasks.
     */
    public Duke(String filePath) {
        assert !filePath.equals("");
        storage = new Storage(filePath);
    }

    /**
     * Load tasks from storage.
     * @return True if loading/file creation is successful, False if there is an error loading/creating file.
     */
    public boolean loadFile() {
        try {
            tasks = new TaskList(storage.load());
            return true;
        } catch (DukeException e) {
            tasks = new TaskList();
            return false;
        }
    }

    /**
     * Get the response of an input.
     * @param input String input entered by user.
     * @return A Response from executing the input's command.
     */
    public Response getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, storage);
        } catch (DukeException e) {
            return new Response(e.getMessage(), false);
        }
    }
}