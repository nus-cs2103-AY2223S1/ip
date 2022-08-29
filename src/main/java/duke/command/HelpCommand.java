package duke.command;

import duke.Ui;
import duke.task.TaskList;

public class HelpCommand implements Command {

    public static final String COMMAND_WORD = "help";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays program usage instructions. Example: "
            + COMMAND_WORD;

    @Override
    public void execute(TaskList taskList, Ui ui) {
        String message = String.format("%s\n\t%s\n\t%s\n\t%s\n\t%s\n\t%s\n\t%s\n\t%s\n\t%s", ListCommand.MESSAGE_USAGE,
                MarkCommand.MESSAGE_USAGE, UnmarkCommand.MESSAGE_USAGE, TodoCommand.MESSAGE_USAGE,
                DeadlineCommand.MESSAGE_USAGE, EventCommand.MESSAGE_USAGE, DeleteCommand.MESSAGE_USAGE,
                FindCommand.MESSAGE_USAGE, HelpCommand.MESSAGE_USAGE);
        ui.printMessage(message);
    }
}
