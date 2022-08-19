package duke.commands;

import duke.storage.StorageFile;
import duke.task.TaskList;
import duke.ui.Ui;

public class HelpCommand extends Command {

	public static final String COMMAND_WORD = "help";
	public static final String MESSAGE_USAGE = COMMAND_WORD
			+ ": Shows Duke usage instructions.\n"
			+ "Example: " + COMMAND_WORD;

	@Override
	public void execute(TaskList taskList, Ui ui, StorageFile storage) {
		ui.showMessages(
				TodoCommand.MESSAGE_USAGE,
				DeadlineCommand.MESSAGE_USAGE,
				EventCommand.MESSAGE_USAGE,
				MarkCommand.MESSAGE_USAGE,
				UnmarkCommand.MESSAGE_USAGE,
				DeleteCommand.MESSAGE_USAGE,
				ListCommand.MESSAGE_USAGE,
				HelpCommand.MESSAGE_USAGE,
				ByeCommand.MESSAGE_USAGE
		);
	}
}
