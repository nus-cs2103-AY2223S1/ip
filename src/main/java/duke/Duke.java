package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;

/**
 * Apollo is a chatbot that keeps tracks of various items, encapsulated
 * as a Duke instance.
 *
 * @author Kartikeya
 */
public class Duke {
    private Storage storage;
    private TaskList itemList;

    /**
     * Constructor for an instance of Apollo.
     * Initialises storage, itemList, ui and parser.
     */
    public Duke() {
        try {
            storage = new Storage();
            itemList = new TaskList(storage.load());
        } catch (DukeException e) {
            itemList = new TaskList();
        }
    }

    /**
     * Executes instructions passed into Apollo.
     * @param input the input string
     * @return the output string
     */
    public String execute(String input) {
        try {
            Command c = Parser.parseUserInput(input);
            return c.execute(itemList, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
