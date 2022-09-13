package duke.commands.tasks;

import java.util.Objects;

import duke.commands.CommandResult;
import duke.enums.SortTaskEnum;

/**
 * SortTaskCommand Class
 */
public class SortTasksCommand extends BaseTaskCommand {
    public static final String COMMAND_WORD = "sort";
    private String successMessage = "This task has been successfully added!\n";
    private final SortTaskEnum direction;

    /**
     * SortTasksCommand constructor method
     *
     * @param direction
     */
    public SortTasksCommand(SortTaskEnum direction) {
        assert Objects.nonNull(direction);
        this.direction = direction;
    }

    @Override
    public CommandResult execute() {
        taskList.sortTaskList(direction);
        successMessage = String.format(
                "%s%s", successMessage, taskList.outputTasksString());
        return new CommandResult(successMessage);
    }

}
