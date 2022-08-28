package duke;

import duke.command.Command;
import duke.exception.DukeException;

/**
 * The class that encapsulates the Duke bot.
 */
public class Duke {
    /** Stores all the data of tasks */
    private final TaskList data;
    /** Deals with loading tasks and saving tasks */
    private Storage storage;
    /** Deals with making sense of the user command */
    private final Parser parser;

    /**
     * The class constructor.
     */
    public Duke() {
        data = new TaskList();
        parser = new Parser();
        try {
            storage = new Storage();
            storage.loadData(data);
        } catch (DukeException e) {
            System.out.println(e);
        }
    }

    public String getResponse(String input) {
        try {
            Command command = parser.parse(input);
            return command.execute(data, storage);
        } catch (DukeException e) {
            return e.toString();
        }
    }

}
