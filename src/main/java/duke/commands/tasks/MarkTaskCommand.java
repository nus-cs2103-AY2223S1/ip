package duke.commands.tasks;

import duke.commands.CommandResult;
import duke.domain.Task;
import duke.exceptions.TaskNotFoundException;

/**
 * MarkTaskCommand Class
 */
public class MarkTaskCommand extends BaseTaskCommand {
    private String successMessage = "This task has been successfully marked!\n";
    private String errorMessage = "An error occurred when marking this task:\n";
    private final Integer taskIndex;

    /**
     *
     */
    public MarkTaskCommand(Integer taskIndex) {
        this.taskIndex = taskIndex;
    }

    @Override
    public CommandResult execute() {
        try {
            Task markedTask = taskList.markTask(taskIndex);
            successMessage = String.format(
                    "%s%s", successMessage, markedTask);
        } catch (TaskNotFoundException e) {
            errorMessage = String.format(
                    "%s%s", errorMessage, e.getMessage());
            return new CommandResult(super.formatOutputString(errorMessage));
        }

        return new CommandResult(super.formatOutputString(successMessage));
    }

}
