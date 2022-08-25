package duke.commands.tasks;

import duke.commands.CommandResult;

public class ListTasksCommand extends BaseTaskCommand {
    private String successMessage = "Here are your tasks:\n";

    @Override
    public CommandResult execute() {
        successMessage = String.format(
                "%s%s", successMessage, taskList.outputTasksString());
        return new CommandResult(super.formatOutputString(successMessage));
    }
}
