package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;

/**
 * Artemis is a chatbot that keeps tracks of various items, encapsulated
 * as a Duke instance.
 *
 * @author Kartikeya
 */
public class Duke {
    private Storage storage;
    private TaskList itemList;

    /**
     * Constructor for an instance of Artemis.
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
     * Executes instructions passed into Artemis.
     *
     * @param input the input string
     * @return the output string
     */
    public String execute(String input) {
        try {
            Command c = Parser.parseUserInput(input);
            assert itemList != null : "itemList should always be initiated before "
                    + "execution commences.";
            return c.execute(itemList, storage);
        } catch (DukeException e) {
            assert e.getMessage().startsWith("Hang on!")
                    : "Exception messages should always start with \"Hang on!\"";
            return e.getMessage();
        }
    }
}
