package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;
import duke.task.Task;

/**
 * Encapsulates a command to delete a Task.
 */
public class DeleteCommand extends Command {
    /** Index of task to delete */
    int taskNo;

    /**
     * Constructor for DeleteCommand.
     * @param taskNo task index
     */
    public DeleteCommand(int taskNo) {
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
            Task tmp = tasks.delete(taskNo);
            storage.write(tasks.toStringWritable());
            return "Noted. I've removed this task:\n" +
                "\t" + tmp.toString();
        } catch (IndexOutOfBoundsException err) {
            throw new DukeException("Invalid task to delete.");
        }
    }
}
