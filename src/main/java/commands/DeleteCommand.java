package commands;

import data.Task;
import data.TaskList;
import storage.Storage;
import ui.Ui;

public class DeleteCommand extends Command {
    private final String description;

    public DeleteCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        Task task = tasks.delete(Integer.parseInt(description) - 1);
        ui.printMultiMsg(new String[]{
                "Noted. I've removed this task:",
                "  " + task,
                "Now you have " + tasks.size() + " task" + (tasks.size() == 1 ? "" : "s") + " in the list."
        });
        storage.save(tasks);
    }
}
