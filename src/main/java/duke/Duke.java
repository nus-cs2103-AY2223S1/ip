package duke;

import java.io.FileNotFoundException;

import duke.command.Command;

/**
 * Duke is a bot which helps to record things which people want to remember.
 *
 * @author Lee Ian Ee
 * @version CS2103T AY22/23 Sem 1
 */

public class Duke {
    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor for Duke.
     */
    public Duke() {
        this.storage = new Storage("./data/duke.txt");
        try {
            this.tasks = new TaskList(this.storage.load());
        } catch (FileNotFoundException e) {
            this.tasks = new TaskList();
        }
    }

    /**
     *  Parses the user input and returns the string output from the Parser to be shown to the user.
     * @param input
     * @return string output to be printed.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parse(input);
            return c.execute(this.tasks, this.storage);
        } catch (DukeException e) {
            return e.getDescription();
        }
    }
}
