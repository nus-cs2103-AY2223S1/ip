package duke.command;

import duke.FileStorage;
import duke.task.TaskList;
import duke.Ui;

/**
 * Command used to mark a particular task in the taskList as uncompleted.
 */
public class UnmarkCommand extends Command {
    public static final String COMMAND_WORD = "unmark";
    private int index;
    public UnmarkCommand(int index) {
        this.index = index - 1;
    }

    /**
     * Marks the task from the taskList as uncompleted.
     *     and prints out the corresponding message to the user.
     * @param list The taskList of Duke.
     * @param storage The fileStorage of Duke.
     * @param ui The Ui of Duke.
     */
    @Override
    public void execute(TaskList list, FileStorage storage, Ui ui) {
        list.markTaskUndone(index);
        ui.printMarkTaskUndone(list.retrieveTask(index));
    }
}
