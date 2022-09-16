package ip;

import ip.command.DukeCommand;
import ip.exception.DukeException;
import ip.utility.Parser;
import ip.utility.Storage;
import ip.utility.TaskList;


/**
 * Task management program.
 */
public class Duke {
    /** Extracts command from string inputs */
    private static Parser parser;
    /** List of all tasks in Duke */
    private static TaskList taskList;
    /** Loads and saves task information to hard drive */
    private static Storage storage;

    /**
     * Constructor for Duke.
     *
     * @param dataPath Path of directory to load task data from.
     */
    public Duke(String dataPath) {
        storage = new Storage(dataPath);
        parser = new Parser();
        taskList = storage.getLatestTaskList();
    }

    /**
     * Replies to the user's input.
     *
     * @param userInput User's input.
     * @return Duke's reply.
     */
    public String getResponse(String userInput) {
        parser.loadUserInput(userInput);
        try {
            DukeCommand command = parser.getCommand();
            return command.execute(taskList, storage);
        } catch (DukeException e) {
            return e.toString();
        }
    }
}
