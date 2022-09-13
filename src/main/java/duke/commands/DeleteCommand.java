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
        if (this.taskIndex > taskList.size() - 1 || this.taskIndex < 0) {
            String msg = "There is no such task index... Try 'list' to view all the tasks and their index!";
            return new CommandResult(msg);
        }
        Task task = taskList.getTask(taskIndex);
        taskList.deleteTask(taskIndex);

        int size = taskList.size();
        String taskString = size > 1 ? "tasks" : "task";
        String msg = "Noted. I've removed the task.\nNow you have " + size + " " + taskString + " in this list.";
        ListBox lb = ListBox.getListBox(task);

        storage.writeAllToStorage(taskList);
        return new CommandResult(msg, lb);
    }
}
