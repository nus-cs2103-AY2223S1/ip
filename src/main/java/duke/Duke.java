package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.util.TaskList;

/**
 * Main class to run for the Rem bot.
 * @author Huang Yuchen
 * @author hyuchen@u.nus.edu
 */
public class Duke {
    private final Storage storage;
    private TaskList tasklist;

    /**
     * Constructor for the Duke class.
     * @param filePath The name of path to the file used for list storage.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasklist = new TaskList(storage.load());
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            tasklist = new TaskList();
        }
    }

    /**
     * Main run method for the program.
     *
     * @param input The user input.
     * @return String to be printed by the GUI.
     */
    public String getResponse(String input) {
        while (true) {
            try {
                Command cmd = Parser.parse(input);
                return cmd.execute(storage, tasklist);
            } catch (DukeException e) {
                return e.getMessage();
            }
        }
    }
}
