package duke.commands;

import duke.DukeException;
import duke.storage.StorageFile;
import duke.task.TaskList;
import duke.ui.Ui;

public class EventCommand extends Command {

	public static final String COMMAND_WORD = "event";
	public static final String MESSAGE_USAGE = COMMAND_WORD
			+ ": Creates a new Event task.\n"
			+ "Example: " + COMMAND_WORD;

	private final String description;
	private final String at;

	public EventCommand(String description, String at) {
		this.description = description;
		this.at = at;
	}

	@Override
	public void execute(TaskList taskList, Ui ui, StorageFile storage) throws DukeException {
		taskList.addEvent(description, at);
		storage.save(taskList);
	}
}
