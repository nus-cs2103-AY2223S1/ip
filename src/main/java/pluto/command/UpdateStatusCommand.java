package pluto.command;

import java.io.IOException;

import pluto.PlutoException;
import pluto.Storage;
import pluto.TaskList;
import pluto.Ui;
import pluto.task.Task;

/**
 * Command to update status of a task.
 */
public class UpdateStatusCommand extends Command {

    /** Index of the task whose status needs to be updated */
    private int idx;
    /** Status of the updated task */
    private boolean isDone;

    /**
     * Initializes global variables.
     * @param idx Index of task to update status.
     * @param isDone Status of the task.
     */
    public UpdateStatusCommand(int idx, boolean isDone) {
        this.idx = idx;
        this.isDone = isDone;
    }

    /**
     * {@inheritDoc}
     *
     * Changes the status of a task, updates the local file,
     * and displays an appropriate message.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws PlutoException {
        tasks.markTask(idx, isDone);
        try {
            storage.rewriteFile(tasks);
            Task t = tasks.getTask(idx);
            return ui.updateStatusUi(t, isDone);
        } catch (IOException e) {
            tasks.markTask(idx, !isDone);
            throw new PlutoException("OOPS!!! Couldn't update task status. Try again!");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof UpdateStatusCommand) {
            UpdateStatusCommand other = (UpdateStatusCommand) o;
            return this.idx == other.idx && this.isDone == other.isDone;
        }
        return false;
    }

}
