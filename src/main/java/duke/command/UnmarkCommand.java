package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * The UnmarkCommand class represents the unmark command to mark a task in the task list as unfinished.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Initializes an instance of UnmarkCommand.
     *
     * @param index The index of the task in the task list to be marked unfinished.
     */
    public UnmarkCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        taskList.markTaskN(index, false);
        storage.overwriteFile(taskList.toStorageString());
        return Command.WRAPPER.getUnmarkResponse(taskList.getTaskN(index).toString());
    }
}
