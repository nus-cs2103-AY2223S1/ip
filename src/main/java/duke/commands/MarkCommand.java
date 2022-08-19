package duke.commands;

import duke.DukeException;
import duke.storage.StorageFile;
import duke.task.TaskList;
import duke.ui.Ui;

public class MarkCommand extends Command {

	private final int targetIndex;
	public static final String COMMAND_WORD = "mark";
	public static final String MESSAGE_USAGE = COMMAND_WORD
			+ ": Marks a task as done.\n"
			+ "Example: " + COMMAND_WORD;

	public MarkCommand(int targetIndex) {
		this.targetIndex = targetIndex;
	}

	@Override
	public void execute(TaskList taskList, Ui ui, StorageFile storage) throws DukeException {
		taskList.markTask(targetIndex);
		storage.save(taskList);
	}
}
