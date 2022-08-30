package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command to delete task.
 */
public class DeleteCommand extends Command {

    private int ind;

    /**
     * Constructs a delete command.
     *
     * @param ind Index of task to be deleted in task list.
     */
    public DeleteCommand(int ind) {
        this.ind = ind;
    }

    /**
     * Deletes task from task list.
     *
     * @param tasks List of task.
     * @param ui User interface of programme.
     * @param storage Storage of programme.
     * @return duke's response.
     * @throws DukeException If ind > tasks.size() or ind < 0 or error saving file.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String response = ui.deleteTaskMessage(tasks.delete(ind - 1), tasks);
        storage.save(tasks.toString());
        return response;
    }
}
