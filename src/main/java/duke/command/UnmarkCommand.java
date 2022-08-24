package duke.command;

import duke.DukeException;
import duke.task.Task;

/**
 * Command to unmark a {@code Task} from a {@code TaskList} as done.
 */
public class UnmarkCommand extends Command {
    private int taskIndex;

    /**
     * Constructor for {@code UnmarkCommand}.
     *
     * @param taskIndex Index of {@code Task} to unmark.
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Marks the task at the specified index from the {@code TaskList} as not done, and prints a success message.
     */
    @Override
    public void execute() throws DukeException {
        Task unmarkedTask = Command.taskList.unmarkTask(this.taskIndex);
        Command.ui.printMessages(new String[]{"Ok, I've marked this task as not done yet:", unmarkedTask.toString()});
    }
}
