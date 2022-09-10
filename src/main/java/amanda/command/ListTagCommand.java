package amanda.command;

import amanda.manager.StoreManager;
import amanda.manager.TaskList;
import amanda.task.Task;
import amanda.ui.Ui;

public class ListTagCommand extends Command {

	private final Task task;
	private final int idx;

	public ListTagCommand(Task task, int idx) {
		this.task = task;
		this.idx = idx;
	}

	@Override
	public void execute(TaskList tasks, StoreManager store) {
		Ui.addResponse("Here are the tags associated with " + task + "\n" + task.listTags());
	}
}
