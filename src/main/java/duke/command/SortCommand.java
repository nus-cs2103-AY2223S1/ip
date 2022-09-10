package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * The SortCommand class represents the sort command to sort the task list according to the criteria chosen.
 */
public class SortCommand extends Command {
    private boolean isDescending;

    /**
     * Initializes a new instance of SortCommand.
     */
    public SortCommand(boolean isDescending) {
        super(false);
        this.isDescending = isDescending;
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        taskList.sortByDate(isDescending);
        storage.overwriteFile(taskList.toStorageString());
        return Command.WRAPPER.getListResponse(taskList.toString());
    }
}