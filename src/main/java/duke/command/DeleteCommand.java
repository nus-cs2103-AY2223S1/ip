/**
 * Project Duke CS2103
 * Done by Hong Jin.
 */
package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * public class DeleteCommand that handles delete command that deletes task at index.
 */
public class DeleteCommand extends Command{
    private int index;

    /**
     * public constructor for DeleteCommand.
     * @param index for task to delete.
     */
    public DeleteCommand(int index) {
        super();
        this.index = index;
    }

    /**
     * public method execute to execute command.
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String temp = tasks.deleteTask(this.index);
        storage.saveToFile(tasks.saveList());
        return ui.printDeleteTask(tasks.get(this.index), tasks.getSize());
    }
}
