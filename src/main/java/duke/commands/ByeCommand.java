package duke.commands;

import duke.storage.StorageFile;
import duke.task.TaskList;
import duke.ui.Ui;

public class ByeCommand extends Command {

	public static final String COMMAND_WORD = "bye";
	public static final String MESSAGE_USAGE = COMMAND_WORD
			+ ": Exits Duke.\n"
			+ "Example: " + COMMAND_WORD;
	public static final String MESSAGE_EXIT_ACKNOWLEDGEMENT = "Exiting Duke as requested...";

	@Override
	public void execute(TaskList taskList, Ui ui, StorageFile storage) {
		ui.showWithColour(MESSAGE_EXIT_ACKNOWLEDGEMENT);
	}
}
