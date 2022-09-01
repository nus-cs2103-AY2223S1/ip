package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * The MarkCommand class represents the mark command to mark a task in the task list as done.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Initializes an instance of MarkCommand.
     *
     * @param index The index of the task in the task list to be marked done.
     */
    public MarkCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        taskList.markTaskN(index, true);
        storage.overwriteFile(taskList.toStorageString());
        return Command.WRAPPER.getMarkResponse(taskList.getTaskN(index).toString());
    }
}
