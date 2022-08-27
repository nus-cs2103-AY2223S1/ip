package duke.commands.tasks;

import duke.commands.CommandResult;
import duke.domain.Task;
import duke.exceptions.TaskNotFoundException;

/**
 * DeleteTaskCommand class
 */
public class DeleteTaskCommand extends BaseTaskCommand {
    private String successMessage = "This task has been successfully deleted!\n";
    private String errorMessage = "An error occurred when deleting this task:\n";
    private final int taskIndex;

    /**
     *
     */
    public DeleteTaskCommand(Integer taskIndex) {
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
            return new CommandResult(super.formatOutputString(errorMessage));
        }

        return new CommandResult(super.formatOutputString(successMessage));
    }
}
