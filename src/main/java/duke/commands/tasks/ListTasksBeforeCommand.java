package duke.commands.tasks;

import java.time.LocalDateTime;
import java.util.Objects;

import duke.commands.CommandResult;

/**
 * ListTasksBeforeCommand class
 */
public class ListTasksBeforeCommand extends ListTasksCommand {
    public static final String SUBCOMMAND_WORD = "before";
    private final LocalDateTime dateTime;
    private String successMessage = "Here are your tasks:\n";

    /**
     * ListTasksBeforeCommand constructor method
     *
     * @param dateTime
     */
    public ListTasksBeforeCommand(LocalDateTime dateTime) {
        assert Objects.nonNull(dateTime);
        this.dateTime = dateTime;
    }

    /**
     * The execute function prints out the tasks that are due before a certain date.
     *
     * @return A command result object
     */
    @Override
    public CommandResult execute() {
        successMessage = String.format(
                "%s%s",
                successMessage,
                taskList.outputTasksBeforeString(this.dateTime));
        return new CommandResult(successMessage);
    }
}
