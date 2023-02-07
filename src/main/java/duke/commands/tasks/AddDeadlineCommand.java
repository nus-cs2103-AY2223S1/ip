package duke.commands.tasks;

import duke.commands.CommandResult;
import duke.domain.Deadline;

/**
 * The AddDeadlineCommand Class
 */
public class AddDeadlineCommand extends BaseTaskCommand {
    public static final String COMMAND_WORD = "deadline";
    public static final String SUBCOMMAND_WORD = "by";
    private final Deadline deadline;
    private String successMessage = "This task has been successfully added!\n";

    /**
     * The AddDeadlineCommand constructor
     *
     */
    public AddDeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }

    @Override
    public CommandResult execute() {
        this.taskList.addTask(deadline);
        successMessage = String.format("%s%s%s", successMessage, "\n", deadline);
        return new CommandResult(successMessage);
    }

}
