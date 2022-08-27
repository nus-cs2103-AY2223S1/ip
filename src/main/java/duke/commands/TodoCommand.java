package duke.commands;

import duke.DukeException;
import duke.common.Messages;
import duke.storage.StorageFile;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.TextUi;

/**
 * Adds a new to-do to the task list.
 */
public class TodoCommand extends Command {

    public static final String COMMAND_WORD = "todo";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Creates a new Todo.\n"
            + "\tEx.: " + COMMAND_WORD + " <description>";

    private final String description;

    /**
     * Constructor for TodoCommand.
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
        } catch (DukeException e) {
            return new CommandResult(e.getMessage());
        }
    }
}
