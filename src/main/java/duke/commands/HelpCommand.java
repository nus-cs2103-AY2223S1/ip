package duke.commands;

import duke.common.Messages;

import duke.storage.StorageFile;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Displays each command and their usages.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows this help page.";

    @Override
    public String execute(TaskList taskList, Ui ui, StorageFile storage) {
        return ui.returnMessages(
                Messages.MESSAGE_HELP_COMMANDS,
                HelpCommand.MESSAGE_USAGE,
                FindCommand.MESSAGE_USAGE,
                SortCommand.MESSAGE_USAGE,
                TodoCommand.MESSAGE_USAGE,
                DeadlineCommand.MESSAGE_USAGE,
                EventCommand.MESSAGE_USAGE,
                ListCommand.MESSAGE_USAGE,
                MarkCommand.MESSAGE_USAGE,
                DeleteCommand.MESSAGE_USAGE,
                ExitCommand.MESSAGE_USAGE
        );
    }
}
