package duke.command;

import duke.Storage;
import duke.Ui;
import duke.task.TaskList;

/**
 * Represents a List Command
 */
public class ListCommand extends Command {

    /**
     * Prints out all tasks in tasklist object
     */
    @Override
    public void execute(TaskList taskList, Storage storage) {
        Ui.appendDukeResponse(taskList.listTasks());
    }
}
