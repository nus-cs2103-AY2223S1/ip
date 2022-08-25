package duke.commands.tasks;

import java.time.LocalDateTime;

import duke.commands.CommandResult;

public class ListTasksAfterCommand extends ListTasksCommand {
    private final LocalDateTime dateTime;
    private String successMessage = "Here are your tasks:\n";

    public ListTasksAfterCommand(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    /**
     * The execute function prints out all the tasks that are due after a certain
     * date.
     * 
     * @return A command result object
     */
    @Override
    public CommandResult execute() {
        successMessage = String.format(
                "%s%s",
                successMessage,
                taskList.outputTasksAfterString(this.dateTime));
        return new CommandResult(super.formatOutputString(successMessage));
    }

}
