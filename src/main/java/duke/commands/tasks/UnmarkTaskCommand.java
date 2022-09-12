package duke.commands.tasks;

import java.util.Objects;

import duke.commands.CommandResult;
import duke.domain.task.Task;
import duke.domain.task.TaskIndex;
import duke.exceptions.TaskNotFoundException;

/**
 * UnmarkTaskCommand class
 */
public class UnmarkTaskCommand extends BaseTaskCommand {
    public static final String COMMAND_WORD = "unmark";
    private String successMessage = "This task has been successfully unmarked!\n";
    private String errorMessage = "An error occurred when unmarking this task:\n";
    private final TaskIndex taskIndex;

    /**
     *
     */
    public UnmarkTaskCommand(TaskIndex taskIndex) {
        assert Objects.nonNull(taskIndex);
        this.taskIndex = taskIndex;
    }

    @Override
    public CommandResult execute() {
        try {
            Task unmarkedTask = taskList.unmarkTask(taskIndex);
            successMessage = String.format(
                    "%s%s", successMessage, unmarkedTask);
        } catch (TaskNotFoundException e) {
            errorMessage = String.format(
                    "%s%s", errorMessage, e.getMessage());
            return new CommandResult(errorMessage);
        }

        return new CommandResult(successMessage);
    }

}
