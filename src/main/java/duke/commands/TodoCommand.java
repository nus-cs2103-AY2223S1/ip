package duke.commands;

import duke.DukeException;
import duke.storage.StorageFile;
import duke.task.TaskList;
import duke.ui.Ui;

public class TodoCommand extends Command {

	public static final String COMMAND_WORD = "todo";
	public static final String MESSAGE_USAGE = COMMAND_WORD
			+ ": Creates a new Todo task.\n"
			+ "Example: " + COMMAND_WORD;

	private final String description;

	public TodoCommand(String description) {
		this.description = description;
	}

	@Override
	public void execute(TaskList taskList, Ui ui, StorageFile storage) throws DukeException {
		taskList.addTodo(description);
		storage.save(taskList);
	}
}
