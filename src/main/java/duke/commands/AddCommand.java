package duke.commands;

import duke.tasks.Task;
import duke.ui.ListBox;
import duke.utils.Storage;
import duke.utils.TaskList;

/**
 * Represents an executable command that adds a Task to the specified TaskList.
 *
 * @author sikai00
 */
public class AddCommand extends Command {
    /** Command identifier used by Parser **/
    public static final String COMMAND_WORD = "add";
    private final Task task;

    /**
     * Initializes a new AddCommand instance.
     *
     * @param task Task to be added
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(TaskList taskList, Storage storage) {
        taskList.addTask(this.task);

        int size = taskList.size();
        String taskString = size > 1 ? "tasks" : "task";
        String msg = "Got it. I've added the task.\nNow you have " + size + " " + taskString + " in this list.";
        ListBox lb = ListBox.getListBox(this.task);

        storage.appendTaskToStorage(task);
        return new CommandResult(msg, lb);
    }
}
