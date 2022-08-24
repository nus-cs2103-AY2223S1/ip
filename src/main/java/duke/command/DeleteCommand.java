package duke.command;

import duke.DukeException;
import duke.task.Task;

/**
 * Command to delete a {@code Task} from a {@code TaskList}.
 */
public class DeleteCommand extends Command {
    private int taskIndex;

    /**
     * Constructor for {@code DeleteCommand}.
     *
     * @param taskIndex Index of {@code Task} to delete.
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Deletes the task at the specified index from the {@code TaskList}, and prints a success message.
     */
    @Override
    public void execute() throws DukeException {
        Task deletedTask = Command.taskList.deleteTask(this.taskIndex);
        Command.ui.printTaskListChange("Noted. I've removed this task:", deletedTask, Command.taskList);
    }
}
