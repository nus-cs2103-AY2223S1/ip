package duke.command;

import duke.storage.Storage;
import duke.exceptions.DukeInvalidParameterException;
import duke.task.TaskList;

/**
 * Represents the command for the deleting tasks from Duke's TaskList.
 */
public class DeleteCommand implements Command{

    private final int TO_DELETE;

    /**
     * Constructs a DeleteCommand.
     *
     * @param TO_DELETE Index of the task to be deleted in Duke's TaskList.
     */
    public DeleteCommand(int TO_DELETE) {
        this.TO_DELETE = TO_DELETE;
    }

    /**
     * Executes the delete command and prints the results of this delete command.
     *
     * @param tasks TaskList which contains all the tasks Duke currently has
     * @param storage Storage created when starting Duke.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeInvalidParameterException {
        String res = tasks.delete(TO_DELETE);
        storage.refresh(tasks);
        return res;
    }
}
