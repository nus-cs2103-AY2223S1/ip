package duke.command;

import duke.task.TaskList;
import duke.Ui;
import duke.Storage;

/**
 * Represents a List Command
 */
public class ListCommand extends Command {

    /**
     * Prints out all tasks in tasklist
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.printTasks();
    }
}
