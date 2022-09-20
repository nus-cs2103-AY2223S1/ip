package duke;

import java.io.IOException;

/**
 * Marks a task as done.
 */
public class DoneCommand extends Command {

    private int index;

    /**
     * Creates a DoneCommand.
     *
     * @param index the index of the task to be marked as done
     */
    public DoneCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the task as done and prints the task marked as done.
     *
     * @param tasks   the task list
     * @param ui      the user interface
     * @param storage the storage
     * @return the message to be printed
     * @throws DukeException if there is an error writing to the file
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String msg = tasks.mark(index);
        try {
            storage.save(tasks);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return msg;
    }
}
