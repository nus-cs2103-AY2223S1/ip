package duke.commands.tasks;

import duke.commands.CommandResult;
import duke.domain.Event;

public class AddEventCommand extends BaseTaskCommand {
    public static final String COMMAND_WORD = "event";
    public static final String SUBCOMMAND_WORD = " at ";
    private final Event event;
    private String successMessage = "This task has been successfully added!\n";

    public AddEventCommand(Event event) {
        this.event = event;
    }

    @Override
    public CommandResult execute() {
        this.taskList.addTask(event);
        successMessage = String.format("%s%s%s", successMessage, "\n", event);
        return new CommandResult(super.formatOutputString(successMessage));
    }

}
