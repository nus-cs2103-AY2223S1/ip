package duke.command;

import duke.DukeException;
import duke.task.Task;
import duke.util.Ui;

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
     * Marks the task at the specified index from the {@code TaskList} as done, and returns a success message.
     *
     * @return Message {@code String} from command execution.
     */
    @Override
    public String execute() throws DukeException {
        Task markedTask = Command.taskList.markTask(this.taskIndex);
        return Ui.formatMessages(new String[]{"Nice! I've marked this task as done:", markedTask.toString()});
    }
}
