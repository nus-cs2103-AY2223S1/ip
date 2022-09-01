package duke.command;

import duke.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

/**
 * The DeleteCommand class represents the command of deleting a task in Duke's task list.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Initializes an instance of DeleteCommand.
     *
     * @param index The index of the task in the task list to be deleted.
     */
    public DeleteCommand(int index) {
        super(false);
        this.index = index;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) throws DukeException {
        String deletedTaskDesc = taskList.getTaskN(index).toString();
        taskList.deleteTaskN(index);
        storage.overwriteFile(taskList.toStorageString());
        return Command.WRAPPER.getDeleteResponse(deletedTaskDesc, taskList.getNumOfTask());
    }
}
