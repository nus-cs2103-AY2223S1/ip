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
    public static final String MESSAGE_USAGE = "Adds a Todo:\n"
            + "    add todo <description>\n"
            + "Add a Deadline:\n"
            + "    add deadline <description> /by <yyyy-mm-dd HH:MM>\n"
            + "Add an Event:\n"
            + "    add event <description> /at <yyyy-mm-dd HH:MM>\n";
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
        String msg = "Okay, I've added the task, but not because you told me to.\nNow you have " + size + " "
                + taskString + " in this list.";
        ListBox lb = ListBox.getListBox(this.task);

        storage.appendTaskToStorage(task);
        return new CommandResult(msg, lb);
    }
}
