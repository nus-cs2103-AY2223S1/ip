package duke;

import java.io.IOException;

/**
 * Marks a task as not done.
 */
public class UndoneCommand extends Command {

    private int index;

    /**
     * Creates a new UndoneCommand.
     *
     * @param index the index of task to mark as not done
     */
    public UndoneCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the task as not done and prints the task marked as not done.
     *
     * @param tasks   the task list
     * @param ui      the user interface
     * @param storage the storage
     * @return the message to be printed
     * @throws DukeException if there is an error writing to the file
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String msg = tasks.unmark(index);
        try {
            storage.save(tasks);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return msg;
    }
}
