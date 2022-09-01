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
     * Adds an event task to the taskList, saves to file
     * and returns the corresponding message to the GUI.
     *
     * @param list The taskList of Duke.
     * @param storage The fileStorage of Duke.
     * @param ui The Ui of Duke.
     * @return The message meant for the GUI.
     */
    @Override
    public String execute(TaskList list, FileStorage storage, Ui ui) {
        list.addTask(task);
        storage.writeToFile(list.getList());
        return ui.getAddedTaskMessage(list, task);
    }
}
