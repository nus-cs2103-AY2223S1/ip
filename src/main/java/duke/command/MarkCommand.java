package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Represents a command to mark task as done or not done.
 */
public class MarkCommand extends Command {

    private int ind;
    private boolean toMark;

    /**
     * Constructs a command to mark task.
     *
     * @param ind Index of task to be marked in task list.
     * @param toMark To mark task as done or not done.
     */
    public MarkCommand(int ind, boolean toMark) {
        this.ind = ind;
        this.toMark = toMark;
    }

    /**
     * Marks task as done or not done.
     *
     * @param tasks List of task.
     * @param ui User interface of programme.
     * @param storage Storage of programme.
     * @return duke's response.
     * @throws DukeException If ind > tasks.size() or ind < 0 or error saving file or task is already done / not done.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Task current = tasks.get(ind - 1);
        String response;
        if (toMark) {
            current.doing();
            response = ui.markDone(current);
        } else {
            current.undo();
            response = ui.markUndone(current);
        }
        storage.save(tasks.toString());
        return response;
    }
}
