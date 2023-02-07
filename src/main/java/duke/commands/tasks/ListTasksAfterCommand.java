package duke.commands.tasks;

import java.time.LocalDateTime;
import java.util.Objects;

import duke.commands.CommandResult;

/**
 * ListTasksAfterCommand class
 */
public class ListTasksAfterCommand extends ListTasksCommand {
    public static final String SUBCOMMAND_WORD = "after";
    private final LocalDateTime dateTime;
    private String successMessage = "Here are your tasks:\n";

    /**
     * ListTaskAfterCommand constructor
     *
     */
    public ListTasksAfterCommand(LocalDateTime dateTime) {
        assert Objects.nonNull(dateTime);
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
        return new CommandResult(successMessage);
    }

}
