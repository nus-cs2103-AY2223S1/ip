package duke.command;

import duke.FileStorage;
import duke.Ui;
import duke.task.TaskList;


/**
 * Command used to mark a particular task in the taskList as completed.
 */
public class MarkCommand extends Command {
    public static final String COMMAND_WORD = "mark";
    private int index;
    public MarkCommand(int index) {
        this.index = index - 1;
    }

    /**
     * Marks the task from the taskList as completed.
     *     and prints out the corresponding message to the user.
     * @param list The taskList of Duke.
     * @param storage The fileStorage of Duke.
     * @param ui The Ui of Duke.
     */
    @Override
    public void execute(TaskList list, FileStorage storage, Ui ui) {
        list.markTaskDone(index);
        ui.printMarkTaskDone(list.retrieveTask(index));
    }
}
