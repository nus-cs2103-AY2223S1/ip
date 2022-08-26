package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(this.task);
        ui.reply("Got it. I've added this duke.task:\n " + this.task +
                "\nNow you have " + tasks.length() + " tasks in the list.");
        storage.saveTasks(tasks);
    }
}