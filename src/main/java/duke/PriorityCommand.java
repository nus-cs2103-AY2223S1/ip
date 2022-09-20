package duke;

import java.io.IOException;

/**
 * Marks a task as done.
 */
public class PriorityCommand extends Command {
    private int index;
    private String priority;

    /**
     * Creates a new PriorityCommand.
     *
     * @param index    the index of task to mark as done
     * @param priority the priority of the task
     */
    public PriorityCommand(String priority, int index) {
        this.priority = priority;
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
        String msg = tasks.setPriority(priority, index);
        try {
            storage.save(tasks);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return msg;
    }
}
