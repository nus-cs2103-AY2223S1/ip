package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents a command to add a task to the list.
 */
public class AddCommand extends Command {
    /** The task to be added. */
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        tasks.addTask(this.task);
        ui.botReply("Got it. I've added this duke.task:\n " + this.task +
                "\nNow you have " + tasks.length() + " tasks in the list.");
        storage.saveTasks(tasks);
    }
}