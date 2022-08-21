package maria.command;

import maria.Storage;
import maria.Ui;
import maria.task.TaskList;

public abstract class Command {

    /**
     * Executes the command.
     * @param taskList The list of all the tasks
     * @param ui The user interface object
     * @param storage The storage object
     */
    public abstract void execute(TaskList taskList, Ui ui, Storage storage);

}
