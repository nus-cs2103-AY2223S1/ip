package sus.commands;

import sus.SusException;
import sus.common.Messages;
import sus.storage.StorageFile;
import sus.task.Task;
import sus.task.TaskList;
import sus.ui.TextUi;

/**
 * Adds a new to-do to the task list.
 */
public class TodoCommand extends Command {

    public static final String COMMAND_WORD = "todo";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Creates a new Todo.\n"
            + "\tEx: " + COMMAND_WORD + " <description>";

    private final String description;

    /**
     * Constructor.
     *
     * @param description description of the to-do to add
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public CommandResult execute(TaskList taskList, TextUi textUi, StorageFile storage) {
        try {
            Task task = taskList.addTodo(description);
            storage.save(taskList);
            return new CommandResult(Messages.MESSAGE_TASK_ADDED + "\n"
                    + task + "\n"
                    + String.format(Messages.MESSAGE_TASK_NUMBER, taskList.getNumberOfTasks()));
        } catch (SusException e) {
            return new CommandResult(e.getMessage());
        }
    }
}
