package duke.commands.tasks;

import duke.commands.CommandResult;

/**
 * ListTasksCommand Class
 */
public class ListTasksCommand extends BaseTaskCommand {
    private String successMessage = "Here are your tasks:\n";

    @Override
    public CommandResult execute() {
        successMessage = String.format(
                "%s%s", successMessage, taskList.outputTasksString());
        return new CommandResult(super.formatOutputString(successMessage));
    }
}
