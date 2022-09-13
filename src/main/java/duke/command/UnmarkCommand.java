/**
 * Project Duke CS2103
 * Done by Hong Jin.
 */
package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * public class UnmarkCommand to mark the task as undone.
 */
public class UnmarkCommand extends Command {
    private int index;

    /**
     * public constructor for UnarkCommand.
     * @param index to mark command in TaskList.
     */
    public UnmarkCommand(int index) {
        super();
        this.index = index;
        assert index >= 0 : "index should be not negative";
    }

    /**
     * public method execute to execute command.
     * @param tasks
     * @param ui
     * @param storage
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (index > tasks.getSize() || index <= 0) {
            return ui.printMessage("Index is out of range!");
        }
        storage.saveToFile(tasks.saveList());
        return ui.printUnmark(tasks.get(index));
    }
}
