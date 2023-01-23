package duke.command;

import duke.storage.Storage;
import duke.task.TaskList;

/**
 * The ListCommand class represents the list command to view the current task list.
 */
public class ListCommand extends Command {

    /**
     * Initializes an instance of ListCommand.
     */
    public ListCommand() {
        super(false);
    }

    @Override
    public String execute(TaskList taskList, Storage storage) {
        return Command.WRAPPER.getListResponse(taskList.toString());
    }
}
