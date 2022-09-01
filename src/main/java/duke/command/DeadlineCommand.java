package duke.command;

import duke.FileStorage;
import duke.Ui;
import duke.task.Task;
import duke.task.TaskList;


/**
 * Command used to add a deadline task into the taskList.
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    private Task task;
    public DeadlineCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds a deadline task to the taskList, saves to file
     * and returns the corresponding message to the GUI.
     *
     * @param list The taskList of Duke.
     * @param storage The fileStorage of Duke.
     * @param ui The ui of Duke.
     * @return The string meant for the GUI.
     */
    @Override
    public String execute(TaskList list, FileStorage storage, Ui ui) {
        list.addTask(task);
        storage.writeToFile(list.getList());
        return ui.getAddedTaskMessage(list, task);
    }
}
