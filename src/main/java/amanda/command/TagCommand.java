package amanda.command;

import amanda.manager.StoreManager;
import amanda.manager.TaskList;
import amanda.task.Tag;
import amanda.task.Task;
import amanda.ui.Ui;

public class TagCommand extends Command {

	private final Task task;
	private final Tag tag;
	private final int idx;

	public TagCommand(Task task, Tag tag, int idx) {
		this.task = task;
		this.tag = tag;
		this.idx = idx;
	}

	@Override
	public void execute(TaskList tasks, StoreManager store) {
		Ui.tagResponse(task, tag);
		TaskList.getList().get(idx - 1).addTag(tag); // mark the current task as done.
	}
}