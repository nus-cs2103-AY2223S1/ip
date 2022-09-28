package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/**
 * Encapsulates a command to mark a Task as complete.
 */
public class MarkCommand extends Command {
    /** Stores index of Task to mark. */
    int taskNo;

    /**
     * Constructor for MarkCommand.
     * @param taskNo task index
     */
    public MarkCommand(int taskNo) {
        this.taskNo = taskNo;
    }

    /**
     * {@inheritDoc}
     */
    public boolean isExit() {
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getResponse(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            tasks.mark(taskNo);
            storage.write(tasks.toStringWritable());
            return "Nice! I've marked this task as done:\n" +
                "\t" + tasks.getTask(taskNo).toString();
        } catch (IndexOutOfBoundsException err) {
            throw new DukeException("Invalid task index to mark.");
        }
    }
}
