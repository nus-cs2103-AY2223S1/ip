package duke;

import java.io.IOException;

/**
 * Adds a task to the task list.
 */
public class AddCommand extends Command {

    private Task task;

    /**
     * Creates an AddCommand.
     *
     * @param task the task to be added
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds the task to the task list and prints the task added.
     *
     * @param tasks the task list
     * @param ui the user interface
     * @param storage the storage
     * @return the message to be printed
     * @throws DukeException if there is an error writing to the file
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        assert task != null : "Task should not be null";
        tasks.add(task);
        try {
            storage.save(tasks);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return ui.showAddTask(task, tasks.size());
    }

    /**
     * Compares this AddCommand with another object.
     *
     * @param obj the object to be compared with
     * @return true if the other object is an AddCommand with the same task
     */
    @Override
    public boolean equals(Object obj) {
        if (obj instanceof AddCommand) {
            AddCommand other = (AddCommand) obj;
            return this.task.equals(other.task);
        } else {
            return false;
        }
    }
}
