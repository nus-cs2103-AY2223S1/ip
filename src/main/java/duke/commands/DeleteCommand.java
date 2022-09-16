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
public class DeleteCommand extends Command {
    /** Command identifier used by Parser **/
    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_USAGE = "Delete a task:\n"
            + "    delete <task index>\n";
    private final int taskIndex;

    /**
     * Initializes a new DeleteCommand instance.
     *
     * @param taskIndex Index of the task to be added
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CommandResult execute(TaskList taskList, Storage storage) {
        // Checks for correct task index can only occur within the execution of the task.
        // Parser can only perform preliminary basic checks for correct input formats.
        if (this.taskIndex > taskList.size() - 1 || this.taskIndex < 0) {
            String msg = "Do you even know what you're doing? There's such no task index!\n";
            return new CommandResult(msg);
        }
        Task task = taskList.getTask(taskIndex);
        taskList.deleteTask(taskIndex);

        int size = taskList.size();
        String taskString = size > 1 ? "tasks" : "task";
        String msg = "Deleting away your poor life choices, I see.\nNow you have "
                + size + " " + taskString + " in this list.";
        ListBox lb = ListBox.getListBox(task);

        storage.writeAllToStorage(taskList);
        return new CommandResult(msg, lb);
    }
}
