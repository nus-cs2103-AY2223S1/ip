package duke.command;

import duke.task.Task;
import duke.util.Ui;

/**
 * Command to add a {@code Task} to a {@code TaskList}.
 */
public class AddCommand extends Command {
    private Task task;

    /**
     * Constructor for {@code AddCommand}.
     *
     * @param task {@code Task} to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the given task to the {@code TaskList}, and prints a success message.
     */
    @Override
    public String execute() {
        Command.taskList.addTask(this.task);
        return Ui.formatTaskListChangeMessage("Got it. I've added this task:", this.task, Command.taskList);
    }
}
