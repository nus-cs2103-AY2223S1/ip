package duke.command;

import duke.FileStorage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;

/**
 * Command used to add an event task into the taskList.
 */
public class EventCommand extends Command {
    public static final String COMMAND_WORD = "event";
    private Task task;
    public EventCommand(Task task) {
        this.task = task;
    }
    /**
     * Adds an event task to the taskList.
     *     and prints out the corresponding message to the user.
     * @param list The taskList of Duke.
     * @param storage The fileStorage of Duke.
     * @param ui The Ui of Duke.
     */
    @Override
    public void execute(TaskList list, FileStorage storage, Ui ui) {
        list.addTask(task);
        ui.printAddedTask(list, task);
    }
}
