package duke.command;

import duke.Ui;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * Represents an InvalidCommand which extends Command
 */
public class InvalidCommand extends Command {
    public static final String COMMAND_ID = "";

    /**
     * Returns a string of the invalid input task that had just been executed
     * @param taskList
     * @param ui
     * @param storage
     * @return a result of the current invalid input task execution
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
