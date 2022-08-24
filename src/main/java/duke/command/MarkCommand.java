package duke.command;

import duke.DukeException;
import duke.task.Task;

/**
 * Command to mark a {@code Task} from a {@code TaskList} as done.
 */
public class MarkCommand extends Command {
    private int taskIndex;

    /**
     * Constructor for {@code MarkCommand}.
     *
     * @param taskIndex Index of {@code Task} to mark.
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }

    /**
     * Marks the task at the specified index from the {@code TaskList} as done, and prints a success message.
     */
    @Override
    public void execute() throws DukeException {
        Task markedTask = Command.taskList.markTask(this.taskIndex);
        Command.ui.printMessages(new String[]{"Nice! I've marked this task as done:", markedTask.toString()});
    }
}