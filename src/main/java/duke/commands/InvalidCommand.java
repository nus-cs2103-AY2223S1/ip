package duke.commands;

import duke.storage.StorageFile;
import duke.task.TaskList;
import duke.ui.Ui;

public class InvalidCommand extends Command {

	private final String errorMessage;

	public InvalidCommand(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public boolean isExit() {
		return false;
	}

	@Override
	public void execute(TaskList taskList, Ui ui, StorageFile storage) {
		ui.showMessages(errorMessage);
	}
}
