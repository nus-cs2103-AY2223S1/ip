package duke;

import duke.commands.Command;
import duke.exceptions.DukeException;

/**
 * Chatbot to read user inputs and manage a tasks list.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs a <code>Duke</code> instance and attempt to retrieve the saved tasks list stored locally.
     * If the retrieval succeeds, initializes a <code>TaskList</code> with the saved tasks in it.
     * If the retrieval fails, initializes a <code>TaskList</code>.
     */

    public Duke() {
        storage = new Storage("data/TaskList.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    /**
     * Returns a <code>String</code> as a response to the user's input.
     *
     * @param input User's input.
     * @return A <code>String</code> response.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, storage);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
