package duke.commands.tasks;

import java.util.Objects;

import duke.commands.CommandResult;
import duke.enums.SortTaskEnum;

/**
 * SortTaskCommand Class
 */
public class SortTasksCommand extends BaseTaskCommand {
    public static final String COMMAND_WORD = "sort";
    public static final String SUBCOMMAND_WORD = "reverse";
    private String successMessage = "Tasks have been successfully sorted!\n";
    private final SortTaskEnum direction;

    /**
     * SortTasksCommand constructor method
     *
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
