package ip;

import java.io.IOException;

import ip.command.DukeCommand;
import ip.exception.DukeException;
import ip.utility.Parser;
import ip.utility.Storage;
import ip.utility.TaskList;


/**
 * <h1>Task management program</h1>
 * Record to-do's, deadlines, and events.
 * Mark them as done or not.
 * Delete them when no longer needed!
 *
 * @author Jonathan Lam
 */
public class Duke {
    /** Extract commands given to the ui */
    private static Parser parser;
    /** Encapsulation of tasks */
    private static TaskList taskList;
    /** File that task data is read from and written to */
    private static Storage storage;

    /**
     * Constructor for Duke.
     *
     * @param path Path to load the task data to.
     */
    public Duke(String path) {
        storage = new Storage(path);
        parser = new Parser();
        try {
            taskList = storage.load();
        } catch (IOException e) {
            System.out.println("Error in loading file from specified path.");
            System.out.println("Duke will not save any task data this run.");
            taskList = new TaskList();
        }
    }

    /**
     * Returns Duke's response to specified string.
     *
     * @param input String that Duke will respond to.
     * @return Response to given string.
     */
    public String getResponse(String input) {
        parser.load(input);
        try {
            DukeCommand command = parser.getCommand();
            String executionReply = command.execute(taskList);
            storage.write(taskList);
            return executionReply;
        } catch (DukeException e) {
            return e.toString();
        }
    }
}
