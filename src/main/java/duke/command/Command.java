package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * The Command class represents the command entered by user.
 */
public abstract class Command {
    protected static final ResponseWrapper WRAPPER = new ResponseWrapper();
    private final boolean isTerminator;
    /**
     * Initializes an instance of a Command.
     *
     * @param isTerminator Indicates whether the command is a terminator.
     */
    protected Command(Boolean isTerminator) {
        this.isTerminator = isTerminator;
    }

    public boolean getIsTerminator() {
        return isTerminator;
    }


    /**
     * Executes the command with the specified taskList, ui, and storage.
     *
     * @param taskList Specified taskList.
     * @param storage Specified storage.
     * @throws DukeException If there is any exceptions when the command is executed.
     */
    public abstract String execute(TaskList taskList, Storage storage) throws DukeException;

}
