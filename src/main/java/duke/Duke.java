package duke;

import duke.exception.DukeException;
import duke.command.*;

/**
 * Overall Duke Class encapsulating the Duke bot.
 * @author Charles Lim Jun Hao
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor for the Duke bot.
     * @param filePath Path to Duke's save file containing list of tasks
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        parser = new Parser();

        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Parses the input command and returns the command's input.
     * @param text input command
     * @return result of command
     */
    public String getResponse(String text) {
        try {
            Command c = parser.parse(text);
            return c.getResponse(tasks, ui, storage);
        } catch (DukeException err) {
            return err.getMessage();
        }
    }
}
