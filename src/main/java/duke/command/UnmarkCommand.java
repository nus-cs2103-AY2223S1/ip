package duke.command;

import duke.DukeException;
import duke.task.Task;
import duke.util.Ui;

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
     * Marks the task at the specified index from the {@code TaskList} as not done, and returns a success message.
     *
     * @return Message {@code String} from command execution.
     */
    @Override
    public String execute() throws DukeException {
        Task unmarkedTask = Command.taskList.unmarkTask(this.taskIndex);
        return Ui.formatMessages(new String[]{"Ok, I've marked this task as not done yet:", unmarkedTask.toString()});
    }
}
