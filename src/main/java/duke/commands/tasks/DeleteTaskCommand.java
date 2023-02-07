package duke.commands.tasks;

import java.util.Objects;

import duke.commands.CommandResult;
import duke.domain.task.Task;
import duke.domain.task.TaskIndex;
import duke.exceptions.TaskNotFoundException;

/**
 * DeleteTaskCommand class
 */
public class DeleteTaskCommand extends BaseTaskCommand {
    public static final String COMMAND_WORD = "delete";
    private String successMessage = "This task has been successfully deleted!\n";
    private String errorMessage = "An error occurred when deleting this task:\n";
    private final TaskIndex taskIndex;

    /**
     *
     */
    public DeleteTaskCommand(TaskIndex taskIndex) {
        assert Objects.nonNull(taskIndex);
        this.taskIndex = taskIndex;
    }

    /**
     * (non-Javadoc)
     *
     * @see duke.commands.BaseCommand#execute()
     */
    @Override
    public CommandResult execute() {
        try {
            Task deletedTask = taskList.deleteTask(taskIndex);
            successMessage = String.format(
                    "%s%s", successMessage, deletedTask);
        } catch (TaskNotFoundException e) {
            errorMessage = String.format(
                    "%s%s", errorMessage, e.getMessage());
            return new CommandResult(errorMessage);
        }

        return new CommandResult(successMessage);
    }
}
