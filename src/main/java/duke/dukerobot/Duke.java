package duke.dukerobot;

import duke.dukeexception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;


/**
 * Represents the duke robot. Contains the main function.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;

    /**
     * Class constructor.
     * @param filepath Filepath of file that stores tasks.
     */
    public Duke(String filepath) {
        try {
            this.storage = new Storage(filepath);
            this.taskList = this.storage.loadTasks();
        } catch (Exception e) {
            this.taskList = null;
        }

    }
    /**
     * Instruct the robot to show user interface and read in command and execute.
     * Catch and dispose exceptions.
     */
    public String getResponse(String input) throws DukeException {
        return Parser.parse(input, this.taskList, this.storage);
    }
}
