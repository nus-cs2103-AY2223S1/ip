package commands;

import data.Task;
import data.TaskList;
import storage.Storage;
import ui.Ui;

public class MarkCommand extends Command {
    private final String description;

    public MarkCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.markDone(Integer.parseInt(description) - 1);
        ui.printMultiMsg(new String[] { "Nice! I've marked this task as done:", task.toString() });
        storage.save(tasks);
    }
}
