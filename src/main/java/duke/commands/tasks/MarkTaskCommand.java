package duke.commands.tasks;

import java.util.Objects;

import duke.commands.CommandResult;
import duke.domain.task.Task;
import duke.domain.task.TaskIndex;
import duke.exceptions.TaskNotFoundException;

/**
 * MarkTaskCommand Class
 */
public class MarkTaskCommand extends BaseTaskCommand {
    public static final String COMMAND_WORD = "mark";
    private String successMessage = "This task has been successfully marked!\n";
    private String errorMessage = "An error occurred when marking this task:\n";
    private final TaskIndex taskIndex;

    /**
     *
     */
    public MarkTaskCommand(TaskIndex taskIndex) {
        assert Objects.nonNull(taskIndex);
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
            return new CommandResult(errorMessage);
        }

        return new CommandResult(successMessage);
    }

}
