package duke;

import java.io.IOException;

/**
 * Deletes a task from the task list.
 */
public class DeleteCommand extends Command {

    private int index;

    /**
     * Creates a DeleteCommand.
     *
     * @param index the index of the task to be deleted
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Deletes the task from the task list and prints the task deleted.
     *
     * @param tasks   the task list
     * @param ui      the user interface
     * @param storage the storage
     * @return the message to be printed
     * @throws DukeException if there is an error writing to the file
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        Task t = tasks.delete(index);
        try {
            storage.save(tasks);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return ui.showDeleteTask(t, tasks.size());
    }
}
