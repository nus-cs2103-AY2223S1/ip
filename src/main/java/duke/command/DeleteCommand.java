package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

/**
 * Executes the commands to delete tasks from the list.
 * @author Lim Ai Lin
 */
public class DeleteCommand extends Command {

    private final String[] str;

    /**
     * Creates a new DeleteCommand object.
     * @param str The array String containing the index of the task to be deleted from the list.
     */
    public DeleteCommand(String[] str) {
        this.str = str;
    }

    /**
     * Executes the delete command the user inputs.
     * @param tasks The task list in which the task is to be deleted.
     * @param ui The ui to deal with user interactions.
     * @param storage The storage to be updated with the newly deleted task.
     * @throws DukeException
     *          Thrown when the index is not given.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int index = 0;
        try {
            index = Integer.parseInt(str[1]) - 1;
        } catch (Exception e) {
            ui.emptyDescription();
        }

        if (index > tasks.size() || index < 0) {
            ui.invalidTask();
        }

        Task myTask = tasks.get(index);
        tasks.remove(index);
        storage.writeFile(tasks);
        ui.remove(tasks, myTask);
    }
}
