package duke.commands;

import duke.storage.StorageFile;
import duke.task.TaskList;
import duke.ui.TextUi;

/**
 * Displays each command and their usages.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows this help page.\n"
            + "\tEx.: " + COMMAND_WORD;

    @Override
    public CommandResult execute(TaskList taskList, TextUi textUi, StorageFile storage) {
        return new CommandResult(
                HelpCommand.MESSAGE_USAGE
                + "\n" + ColourCommand.MESSAGE_USAGE
                + "\n" + TodoCommand.MESSAGE_USAGE
                + "\n" + DeadlineCommand.MESSAGE_USAGE
                + "\n" + EventCommand.MESSAGE_USAGE
                + "\n" + ListCommand.MESSAGE_USAGE
                + "\n" + FindCommand.MESSAGE_USAGE
                + "\n" + MarkCommand.MESSAGE_USAGE
                + "\n" + UnmarkCommand.MESSAGE_USAGE
                + "\n" + DeleteCommand.MESSAGE_USAGE
                + "\n" + ExitCommand.MESSAGE_USAGE
        );
    }
}
